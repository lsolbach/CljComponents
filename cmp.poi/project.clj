(defproject org.soulspace.clj/cmp.poi "0.6.0"
  :description "The cmp.poi library is an Apache POI library wrapper in Clojure."
  :url "https://github.com/lsolbach/CljComponents"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.soulspace.clj/clj.java "0.8.0"]
                 [org.apache.poi/poi-ooxml "3.14"]]
  :test-paths ["unittest"])
