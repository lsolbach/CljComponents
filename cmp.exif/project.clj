(defproject org.soulspace.clj/cmp.exif "0.1.1-SNAPSHOT"
  :description "The cmp.exif is a library for reading EXIF tags on images"
  :url "https://github.com/lsolbach/CljComponents"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  ; use deps.edn dependencies
  :plugins [[lein-tools-deps "0.4.5"]]
  :middleware [lein-tools-deps.plugin/resolve-dependencies-with-deps-edn]
  :lein-tools-deps/config {:config-files [:install :user :project]}
  ;  :dependencies [[org.clojure/clojure "1.10.1"]
  ;                 [com.drewnoakes/metadata-extractor "2.14.0"]]
  :deploy-repositories [["clojars" {:sign-releases false :url "https://clojars.org/repo"}]])
