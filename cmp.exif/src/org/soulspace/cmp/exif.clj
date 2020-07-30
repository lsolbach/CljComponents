(ns org.soulspace.cmp.exif
  (:require [clojure.java.io :as io])
  (:import [com.drew.imaging ImageMetadataReader]
           [com.drew.metadata.exif ExifSubIFDDirectory]))

(defn directories
  "Returns the metadata directories."
  [metadata]
  (.getDirectories metadata))

(defn first-directory-of-type
  "Returns the first metadata directory of the given type."
  [type metadata]
  (.getFirstDirectoryOfType metadata type))

(defn dir-name
  "Returns the name of the directory."
  [dir]
  (.getName dir))

(defn tags
  "Returns the metadata tags in a directory."
  [directory]
  (.getTags directory))

(defn tag-name
  "Returns the name of the tag."
  [tag]
  (.getTagName tag))

(defn tag-description
  "Returns the description of the tag."
  [tag]
  (.getDescription tag))

(defn errors?
  "Returns true, if the directory has errors."
  [directory]
  (.hasErrors directory))

(defn errors
  "Returns the metadata errors in a directory."
  [directory]
  (.getErrors directory))

(defn parse-tag
  "Parse a tag"
  [tag]
  {(tag-name tag) (tag-description tag)})

(defn parse-directory
  "Parses a directory."
  [dir]
  (when (errors? dir)
    (println "ERRORS on directory" (dir-name dir))
    (doseq [error (errors dir)]
      (println error)))
  {(dir-name dir) (reduce merge {} (map parse-tag (tags dir)))})

(defn parse-metadata
  "Parses the metadata."
  [metadata]
  (reduce merge {} (map parse-directory (directories metadata))))

(defn read-metadata
  "Reads a file and returns the metadata."
  [file]
  (ImageMetadataReader/readMetadata (io/as-file file)))

(defn print-metadata
  "Prints the metadata of the file"
  [file]
  (let [metadata (read-metadata file)]
    (doseq [dir (directories metadata)]
      (doseq [tag (tags dir)]
        (println "[" (dir-name dir) "]." (tag-name tag) "=" (tag-description tag))))))

(defn save-metadata
  "Saves the metadata to a text file."
  [in-file out-file]
  (with-open [out (io/writer out-file)]
    (binding [*out* out]
              (print-metadata in-file))))
