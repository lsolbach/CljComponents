[
 :name "CljXsltLibrary"
 :project "org.soulspace.clj"
 :type "library"
 :version "0.1.0"
 :description "Clojure XSLT library"
 :plugins ["global" "deps" "clojure" "package"]
 ; FIXME remove dependency to clojure contrib
 :dependencies [["org.clojure" "clojure" "1.3.0"]
                ["xalan" "xalan" "2.7.1"] ; TODO check version
                ["xalan" "serializer" "2.7.1"] ; TODO check version, check transitive
                ["xerces" "xercesImpl" "2.9.1"] ; transitive 
                ["xml-apis" "xml-apis" "1.3.04"] ; transitive 
                ["xml-apis" "xml-apis-ext" "1.3.04"] ; transitive 
                ]
 ]