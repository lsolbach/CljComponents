[
 :module "CljPoiLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.1.0"
 :description "An Apache POI library wrapper in clojure"
 :plugins ["global" "sdeps" "depsdot" "clojure" "package"]
 :dependencies [[["org.clojure" "clojure" "1.5.1"]]
                [["org.soulspace.clj" "CljJavaLibrary" "0.2.0"]]
                [["org.apache.poi" "poi-ooxml" "3.9"]]
                ]
 ]
