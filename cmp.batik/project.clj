(defproject org.soulspace.clj/cmp.batik "0.2.1-SNAPSHOT"
  :description "The cmp.batik library provides a Clojure wrapper for Apache Batik SVG components."
  :url "https://github.com/lsolbach/CljXML"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  ; use deps.edn dependencies
  :plugins [[lein-tools-deps "0.4.5"]]
  :middleware [lein-tools-deps.plugin/resolve-dependencies-with-deps-edn]
  :lein-tools-deps/config {:config-files [:install :user :project]}

  :test-paths ["test"]
  :deploy-repositories [["clojars" {:sign-releases false :url "https://clojars.org/repo"}]])
