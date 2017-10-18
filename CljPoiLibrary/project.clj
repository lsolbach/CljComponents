(defproject org.soulspace.clj/CljPoiLibrary "0.5.0"
  :description "The CljPoiLibrary is an Apache POI library wrapper in Clojure"
  :url "https://github.com/lsolbach/CljComponents"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.soulspace.clj/CljJavaLibrary "0.7.0"]
                 [org.apache.poi/poi-ooxml "3.14"]]
  :test-paths ["unittest"])
