(defproject org.soulspace.clj/cmp.net "0.3.0"
  :description "The cmp.net library provides a clojure wrapper for Apache Commons Net."
  :url "https://github.com/lsolbach/CljComponents"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [commons-net/commons-net "3.4"]
                 [org.soulspace.clj/clj.java "0.8.0"]]
  :test-paths ["unittest"])
