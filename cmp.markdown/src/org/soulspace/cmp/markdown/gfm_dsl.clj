;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.cmp.markdown.gfm-dsl)

; github flavoured markdown with additional formatting

(defn strike-through
  "Strike through text."
  [s]
  (str "~~" s "~~"))

(defn fenced-code
  "Fenced code."
  ([s]
   (str "```\n" s "```\n"))
  ([s lang]
   (str "```" lang "\n" s "```\n")))
