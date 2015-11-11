[
 :module "CljPoiLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.4.1"
 :description "An Apache POI library wrapper in clojure"
 :plugins ["global"
           ["org.soulspace.baumeister/ClojurePlugin"]
           ["org.soulspace.baumeister/ClojureTestPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.clojure/clojure, 1.7.0"]
                ["org.soulspace.clj/CljJavaLibrary, 0.6.1"]
                ["org.apache.poi/poi-ooxml, 3.9"]]
 ]
