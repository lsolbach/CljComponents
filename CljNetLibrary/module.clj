[
 :module "CljNetLibrary"
 :project "org.soulspace.clj"
 :type :library
 :version "0.1.0"
 :description "The CljNetLibrary provides a clojure wrapper for Apache Commons Net."
 :project-lead "Ludger Solbach"
 :provider "soulspace.org"
 :license ["Eclipse Public License 1.0" "http://www.eclipse.org/legal/epl-v10.html"]
 :plugins ["global"
           ["org.soulspace.baumeister/DependencyPlugin"]
           ["org.soulspace.baumeister/ClojurePlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]
           ["org.soulspace.baumeister/DistributionPlugin"]]
 :dependencies [["org.clojure/clojure, 1.5.1"]
                ["commons-net/commons-net, 3.3"]
                ["org.soulspace.clj/CljJavaLibrary, 0.4.0"]]
 ]
