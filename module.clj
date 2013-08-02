[
 :module "CljFopLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.1.0"
 :description "Clojure wrapper library for Apache FOP."
 :plugins ["global" "sdeps" "depsdot" "clojure" "package"]
 :dependencies [[["org.clojure" "clojure" "1.5.1"]]
                [["org.soulspace.clj" "CljJavaLibrary" "0.2.0"]]
                [["org.apache.xmlgraphics" "fop" "1.0"]]
                ]
 ]