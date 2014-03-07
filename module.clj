[
 :module "CljJFreechartLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.2.0"
 :description "A library for the integration of JFreechart in clojure"
 :plugins ["global"
           ["org.soulspace.baumeister/DependencyPlugin"]
           ["org.soulspace.baumeister/ClojurePlugin"]
           ["org.soulspace.baumeister/ClojureTestPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.clojure/clojure, 1.5.1"]
                ["org.soulspace.clj/CljJavaLibrary, 0.5.0"]
                ["jfree/jfreechart, 1.0.13"]]
 ]