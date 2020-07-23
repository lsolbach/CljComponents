;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.cmp.xslt.xalan
  (:import [javax.xml.transform TransformerFactory Transformer]
           [javax.xml.transform.stream StreamSource StreamResult]
           [java.io FileOutputStream]))

;TODO transform from xml strings directly
;TODO convert to multimethod?
(defn transform [xsl xml out]
  (let [xsl-source (StreamSource. xsl)
        xml-source (StreamSource. xml)
        out-result (StreamResult. (FileOutputStream. out))
        tf (TransformerFactory/newInstance)
        t (.newTransformer tf xsl-source)]
    (.transform t xml-source out-result)))
