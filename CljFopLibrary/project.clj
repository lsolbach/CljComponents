(defproject org.soulspace.clj/cmp.fop "0.3.0"
  :description "The cmp.fop component is a Clojure wrapper for Apache FOP"
  :url "https://github.com/lsolbach/CljComponents"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.soulspace.clj/clj.java "0.8.0"]
                 [org.apache.xmlgraphics/fop "2.2"]]
  :test-paths ["unittest"])
