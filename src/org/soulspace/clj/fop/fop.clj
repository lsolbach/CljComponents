(ns org.soulspace.clj.fop.fop
  (:use [clojure.java.io]
        [org.soulspace.clj.java beans])
  (:import [javax.xml.parsers SAXParserFactory SAXParser]
           [org.apache.fop.apps FOUserAgent Fop FopFactory MimeConstants]))

(def fop-factory (FopFactory/newInstance))

(defn get-fop-factory [& opts]
  (let [ff (FopFactory/newInstance)]
    (if opts
      (set-properties! ff opts))))

(defn fo-to-pdf [fo pdf-file]
  "convert a fo file to a pdf file"
  ;configure foUserAgent as desired
  (with-open [out (output-stream pdf-file)]
    (let [foUserAgent (.newFOUserAgent fop-factory)
          fop (.newFop fop-factory MimeConstants/MIME_PDF out)
          sax-factory (SAXParserFactory/newInstance)]
      (.setNamespaceAware sax-factory true)
      (let [parser (.newSAXParser sax-factory)
            dh (.getDefaultHandler fop)]
        (.parse parser fo dh)))))
