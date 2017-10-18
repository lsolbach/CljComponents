[
 :module "CljJFreechartLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.3.0"
 :description "The CljJFreechartLibrary is a library for the integration of JFreechart in Clojure"
 :scm-url "https://github.com/lsolbach/CljComponents"
 :plugins [["org.soulspace.baumeister/ClojurePlugin"]
           ["org.soulspace.baumeister/ClojureTestPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.clojure/clojure, 1.8.0"]
                ["org.soulspace.clj/CljJavaLibrary, 0.7.0"]
                ["org.jfree/jfreechart, 1.0.19"]]
 ]