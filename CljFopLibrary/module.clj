[
 :module "CljFopLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.1.1"
 :description "Clojure wrapper library for Apache FOP."
 :plugins ["global"
           ["org.soulspace.baumeister/DependencyPlugin"]
           ["org.soulspace.baumeister/ClojurePlugin"]
           ["org.soulspace.baumeister/ClojureTestPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.clojure/clojure, 1.7.0"]
                ["org.soulspace.clj/CljJavaLibrary, 0.6.1"]
                ["org.apache.xmlgraphics/fop, 1.0"]]
 ]