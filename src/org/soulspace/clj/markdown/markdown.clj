;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.clj.markdown.markdown
  (:require [clojure.string :as str]))

(defn h1
  "First level heading."
  [s]
  (str s "\n" (apply str (repeat (count s) "=")) "\n"))

(defn h2
  "Second level heading."
  [s]
  (str s "\n" (apply str (repeat (count s) "-")) "\n"))

(defn h3
  "Third level heading."
  [s]
  (str "### " s))

(defn h4
  "Fourth level heading."
  [s]
  (str "#### " s))

(defn p
  "Paragraph."
  [s]
  (str s "\n\n"))

(defn br
  "Line break."
  []
  "  ")

(defn ul
  "Unordered list"
  [coll]
  )

(defn ol
  "Ordered list."
  [coll]
  )

(defn em
  "Emphasis."
  [s]
  (str "*" s "*"))

(defn strong
  "Strong emphasis."
  [s]
  (str "**" s "**"))

(defn very-strong
  "Very strong emphasis."
  [s]
  (str "***" s "***"))

(defn code-inline
  "Code."
  [s]
  (str "`" s "`"))

(defn code
  "Code."
  [s]
  (apply str (map #(str "    " % "\n") (str/split #"(\r\n|\n|\r)" s))))

(defn block-quote
  "Block quote."
  [s]
  (str "> " s))

(defn link
  "Link."
  [coll]
  (str "[" (first coll) "]" "(" (second coll) ")"))

(defn link-id
  "Link."
  [coll]
  (str "[" (first coll) "]" "[" (second coll) "]"))

(defn link-ref
  "Link reference."
  [coll]
  (str "[" (first coll) "]: " (second coll) (when (seq (nnext coll)) (str " \"" (nnext coll) "\""))))

(defn image
  "Image."
  [coll]
  (str "![" (first coll) "]" "(" (second coll) ")"))

(defn image-id
  "Image."
  [coll]
  (str "![" (first coll) "]" "[" (second coll) "]"))

(defn image-ref
  "Image reference."
  [coll]
  (str "![" (first coll) "]: " (second coll) (when (seq (nnext coll)) (str " \"" (nnext coll) "\""))))

(defn hr
  "Horizontal ruler."
  []
  "------------------------------------------------------------------------")
