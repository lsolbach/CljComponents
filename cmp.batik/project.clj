(defproject org.soulspace.clj/cmp.batik "0.2.0"
  :description "The cmp.batik library provides a Clojure wrapper for Apache Batik SVG components."
  :url "https://github.com/lsolbach/CljXML"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.apache.xmlgraphics/batik-swing "1.9.1"]
                 [org.apache.xmlgraphics/batik-svggen "1.9.1"]
                 [org.soulspace.clj/clj.java "0.8.0"]]
  :test-paths ["test"]
  :deploy-repositories [["clojars" {:sign-releases false :url "https://clojars.org/repo"}]])
