(defproject org.soulspace.clj/cmp.jfreechart "0.4.0"
  :description "The cmp.jfreechart component is a Clojure wrapper for JFreechart."
  :url "https://github.com/lsolbach/CljComponents"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.soulspace.clj/clj.java "0.8.0"]
                 [org.jfree/jfreechart "1.5.0"]
                 [org.jfree/jfreechart "1.0.22"]]
  :test-paths ["test"]
  :deploy-repositories [["clojars" {:sign-releases false :url "https://clojars.org/repo"}]])
