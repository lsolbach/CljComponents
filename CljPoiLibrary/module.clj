[
 :module "CljPoiLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.5.0"
 :description "An Apache POI library wrapper in clojure"
 :scm-url "https://github.com/lsolbach/CljComponents"
 :plugins [["org.soulspace.baumeister/ClojurePlugin"]
           ["org.soulspace.baumeister/ClojureTestPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.clojure/clojure, 1.8.0"]
                ["org.soulspace.clj/CljJavaLibrary, 0.7.0"]
                ["org.apache.poi/poi-ooxml, 3.14"]]
 ]
