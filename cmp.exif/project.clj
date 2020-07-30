(defproject org.soulspace.clj/cmp.exif "0.1.0"
  :description "The cmp.exif is a library for reading EXIF tags on images"
  :url "https://github.com/lsolbach/CljComponents"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [com.drewnoakes/metadata-extractor "2.14.0"]]
  :deploy-repositories [["clojars" {:sign-releases false :url "https://clojars.org/repo"}]])
