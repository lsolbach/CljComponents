(defproject org.soulspace.clj/cmp.jfreechart "0.4.0"
  :description "The cmp.jfreechart component is a Clojure wrapper for JFreechart."
  :url "https://github.com/lsolbach/CljComponents"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  ; use deps.edn dependencies
  :plugins [[lein-tools-deps "0.4.5"]]
  :middleware [lein-tools-deps.plugin/resolve-dependencies-with-deps-edn]
  :lein-tools-deps/config {:config-files [:install :user :project]}

;  :dependencies [[org.clojure/clojure "1.10.1"]
;                 [org.soulspace.clj/clj.java "0.8.0"]
;                 [org.jfree/jfreechart "1.5.0"]]

:test-paths ["test"]
  :deploy-repositories [["clojars" {:sign-releases false :url "https://clojars.org/repo"}]])
