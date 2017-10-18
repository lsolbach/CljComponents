(defproject org.soulspace.clj/CljXsltLibrary "0.2.0"
  :description "The CljXsltLibrary is a Clojure XSLT library wrapping xalan"
  :url "https://github.com/lsolbach/CljComponents/CljXsltLibrary"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [xalan/xalan "2.7.1"] ; TODO check version
                 [xalan/serializer "2.7.1"]] ; TODO check version, check transitive]
  :test-paths ["unittest"])
