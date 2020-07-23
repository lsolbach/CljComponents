[
 :module "cmp.fop"
 :project "org.soulspace.clj"
 :type :library
 :version "0.3.0"
 :description "The cmp.fop component is a Clojure wrapper for Apache FOP"
 :scm-url "https://github.com/lsolbach/CljComponents"
 :plugins [["org.soulspace.baumeister/ClojurePlugin"]
           ["org.soulspace.baumeister/ClojureTestPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.clojure/clojure, 1.10.1"]
                ["org.soulspace.clj/clj.java, 0.8.0"]
                ["org.apache.xmlgraphics/fop, 2.2"]]]
