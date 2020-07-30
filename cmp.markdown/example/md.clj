;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns md
  (:require [org.soulspace.cmp.md.markdown-dsl :as md]))

(def md
  (md/markdown
    (md/h1 "Markdown Example")
    (md/p "This is an example of the programmatic generation of markdown files with the CljMarkdown library.")
    (md/h2 "Markdown Formats")
    (md/p "The CljMarkdownLibrary supports the following markdown formats:\n"
       (md/ol (md/link "Markdown" "http://daringfireball.net/projects/markdown/")
           (md/link "Github flavoured markdown" "https://help.github.com/articles/github-flavored-markdown")))))

(println md)
