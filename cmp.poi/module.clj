[
 :module "cmp.poi"
 :project "org.soulspace.clj"
 :type :library
 :version "0.6.0"
 :description "The cmp.poi library is an Apache POI library wrapper in Clojure."
 :scm-url "https://github.com/lsolbach/CljComponents"
 :plugins [["org.soulspace.baumeister/ClojurePlugin"]
           ["org.soulspace.baumeister/ClojureTestPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.clojure/clojure, 1.10.1"]
                ["org.soulspace.clj/cmp.java, 0.8.0"]
                ["org.apache.poi/poi-ooxml, 3.14"]]]
