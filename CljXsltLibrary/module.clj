[
 :module "CljXsltLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.2.0"
 :description "Clojure XSLT library"
 :scm-url "https://github.com/lsolbach/CljComponents"
 :plugins [["org.soulspace.baumeister/ClojurePlugin"]
           ["org.soulspace.baumeister/ClojureTestPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.clojure/clojure, 1.8.0"]
                ["xalan/xalan, 2.7.1"] ; TODO check version
                ["xalan/serializer, 2.7.1"]]] ; TODO check version, check transitive
