(defproject org.soulspace.clj/CljNetLibrary "0.2.0"
  :description "The CljNetLibrary provides a clojure wrapper for Apache Commons Net"
  :url "https://github.com/lsolbach/CljComponents"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [commons-net/commons-net "3.4"]
                 [org.soulspace.clj/CljJavaLibrary "0.7.0"]]
  :test-paths ["unittest"])
