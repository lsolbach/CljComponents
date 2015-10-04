[
 :module "CljXsltLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.1.1"
 :description "Clojure XSLT library"
 :plugins ["global"
           ["org.soulspace.baumeister/DependencyPlugin"]
           ["org.soulspace.baumeister/ClojurePlugin"]
           ["org.soulspace.baumeister/ClojureTestPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.clojure/clojure, 1.7.0"]
                ["xalan/xalan, 2.7.1"] ; TODO check version
                ["xalan/serializer, 2.7.1"] ; TODO check version, check transitive
                ]
 ]