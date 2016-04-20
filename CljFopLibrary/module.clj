[
 :module "CljFopLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.2.0"
 :description "Clojure wrapper library for Apache FOP."
 :plugins ["global"
           ["org.soulspace.baumeister/ClojurePlugin"]
           ["org.soulspace.baumeister/ClojureTestPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.clojure/clojure, 1.8.0"]
                ["org.soulspace.clj/CljJavaLibrary, 0.7.0"]
                ["org.apache.xmlgraphics/fop, 2.1"]]
 ]