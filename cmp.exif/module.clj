[
 :module "CljExifLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.1.0"
 :description "Clojure EXIF library"
 :plugins [["org.soulspace.baumeister/ClojurePlugin"]
           ["org.soulspace.baumeister/ClojureTestPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.clojure/clojure, 1.8.0"]
                ["com.drewnoakes/metadata-extractor, 2.10.1"]
                ]
 ]