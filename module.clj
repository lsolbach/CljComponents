[
 :name "CljFopLibrary"
 :project "org.soulspace.clj"
 :type "library"
 :version "0.1.0"
 :description "Clojure wrapper library for Apache FOP."
 :plugins ["global" "deps" "clojure" "package"]
 :dependencies [["org.clojure" "clojure" "1.5.1"]
                ["org.soulspace.clj" "CljLibrary" "0.3.0"]
                ["org.soulspace.clj" "CljJavaLibrary" "0.2.0"]
                ["org.apache.xmlgraphics" "fop" "1.0"]
                ["org.apache.xmlgraphics" "batik-dom" "1.7"] ; transitive fop
                ["org.apache.xmlgraphics" "batik-svg-dom" "1.7"] ; transitive fop
                ["org.apache.xmlgraphics" "batik-svggen" "1.7"] ; transitive fop
                ["org.apache.xmlgraphics" "batik-ext" "1.7"] ; transitive fop
                ["org.apache.xmlgraphics" "batik-xml" "1.7"] ; transitive fop
                ["org.apache.xmlgraphics" "batik-css" "1.7"] ; transitive fop
                ["org.apache.xmlgraphics" "batik-parser" "1.7"] ; transitive fop
                ["org.apache.xmlgraphics" "batik-anim" "1.7"] ; transitive fop
                ["org.apache.xmlgraphics" "batik-gvt" "1.7"] ; transitive fop
                ["org.apache.xmlgraphics" "batik-bridge" "1.7"] ; transitive fop
                ["org.apache.xmlgraphics" "batik-script" "1.7"] ; transitive fop
                ["org.apache.xmlgraphics" "batik-util" "1.7"] ; transitive fop
                ["org.apache.xmlgraphics" "batik-awt-util" "1.7"] ; transitive fop
                ["org.apache.xmlgraphics" "xmlgraphics-commons" "1.4"] ; transitive fop
                ["commons-logging" "commons-logging" "1.1.1"] ; transitive fop
                ["commons-io" "commons-io" "1.3.1"] ; transitive fop
                ["xalan" "xalan" "2.7.1"] ; transitive fop
                ["xalan" "serializer" "2.7.1"] ; transitive fop
                ["xerces" "xercesImpl" "2.9.0"] ; transitive fop
                ["xml-apis" "xml-apis" "1.3.04"] ; transitive fop
                ["xml-apis" "xml-apis-ext" "1.3.04"] ; transitive fop
                ["org.apache.avalon.framework" "avalon-framework-api" "4.3.1"] ; transitive fop
                ["org.apache.avalon.framework" "avalon-framework-impl" "4.3.1"] ; transitive fop
                ]
 ]