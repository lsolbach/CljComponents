[
 :module "cmp.xalan"
 :project "org.soulspace.clj"
 :type :library
 :version "0.3.0"
 :description "Clojure Xalan component for performing XSL transformations."
 :scm-url "https://github.com/lsolbach/CljComponents"
 :plugins [["org.soulspace.baumeister/ClojurePlugin"]
           ["org.soulspace.baumeister/ClojureTestPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.clojure/clojure, 1.10.1"]
                ["xalan/xalan, 2.7.1"] ; TODO check version
                ["xalan/serializer, 2.7.1"]]] ; TODO check version, check transitive
