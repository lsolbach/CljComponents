[
 :module "CljXsltLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.1.0"
 :description "Clojure XSLT library"
 :plugins ["global" "sdeps" "clojure" "package"]
 :dependencies [[["org.clojure" "clojure" "1.5.1"]]
                [["xalan" "xalan" "2.7.1"]] ; TODO check version
                [["xalan" "serializer" "2.7.1"]] ; TODO check version, check transitive
                [["xerces" "xercesImpl" "2.9.1"]] ; transitive 
                [["xml-apis" "xml-apis" "1.3.04"]] ; transitive 
                [["xml-apis" "xml-apis-ext" "1.3.04"]] ; transitive 
                ]
 ]