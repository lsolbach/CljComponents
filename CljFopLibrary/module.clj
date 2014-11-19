[
 :module "CljFopLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.1.0"
 :description "Clojure wrapper library for Apache FOP."
 :plugins ["global"
           ["org.soulspace.baumeister/DependencyPlugin"]
           ["org.soulspace.baumeister/ClojurePlugin"]
           ["org.soulspace.baumeister/ClojureTestPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.clojure/clojure, 1.5.1"]
                ["org.soulspace.clj/CljJavaLibrary, 0.5.0"]
                ["org.apache.xmlgraphics/fop, 1.0"]]
 ]