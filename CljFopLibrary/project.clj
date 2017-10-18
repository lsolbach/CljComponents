(defproject org.soulspace.clj/CljFopLibrary "0.2.0"
  :description "The CljFopLibrary is a Clojure wrapper library for Apache FOP"
  :url "https://github.com/lsolbach/CljComponents"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.soulspace.clj/CljJavaLibrary "0.7.0"]
                 [org.apache.xmlgraphics/fop "2.1"]]
  :test-paths ["unittest"])
