[
 :module "cmp.net"
 :project "org.soulspace.clj"
 :type :library
 :version "0.3.0"
 :description "The cmp.net library provides a clojure wrapper for Apache Commons Net."
 :project-lead "Ludger Solbach"
 :provider "soulspace.org"
 :license ["Eclipse Public License 1.0" "http://www.eclipse.org/legal/epl-v10.html"]
 :scm-url "https://github.com/lsolbach/CljComponents"
 :plugins [["org.soulspace.baumeister/ClojurePlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]
           ["org.soulspace.baumeister/DistributionPlugin"]]
 :dependencies [["org.clojure/clojure, 1.10.1"]
                ["commons-net/commons-net, 3.4"]
                ["org.soulspace.clj/clj.java, 0.8.0"]]]
