(defproject org.soulspace.clj/cmp.batik "0.2.0"
  :description "The cmp.batik library provides a Clojure wrapper for the Apache Batik SVG component."
  :url "https://github.com/lsolbach/CljXML"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.apache.xmlgraphics/batik-swing "1.9.1"]
                 [org.apache.xmlgraphics/batik-svggen "1.9.1"]
                 [org.apache.xmlgraphics/batik-svg-dom "1.9.1"]
                 [org.soulspace.clj/xml.core "0.5.0"]
                 [org.soulspace.clj/clj.base "0.8.0"]]
  :test-paths ["unittest"])
