[
 :module "CljPoiLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.4.0"
 :description "An Apache POI library wrapper in clojure"
 :plugins ["global"
           ["org.soulspace.baumeister/DependencyPlugin"]
           ["org.soulspace.baumeister/ClojurePlugin"]
           ["org.soulspace.baumeister/ClojureTestPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.clojure/clojure, 1.5.1"]
                ["org.soulspace.clj/CljJavaLibrary, 0.5.0"]
                ["org.apache.poi/poi-ooxml, 3.9"]]
 ]
