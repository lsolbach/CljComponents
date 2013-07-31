[
 :module "CljXsltLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.1.0"
 :description "Clojure XSLT library"
 :plugins ["global" "sdeps" "depsdot" "clojure" "package"]
 :dependencies [[["org.clojure" "clojure" "1.5.1"]]
                [["xalan" "xalan" "2.7.1"]] ; TODO check version
                [["xalan" "serializer" "2.7.1"]] ; TODO check version, check transitive
                ]
 ]