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
  (:use [org.soulspace.clj.markdown markdown]))

(def md
  (markdown
    (h1 "Markdown Example")
    (p "This is an example of the programmatic generation of markdown files with the CljMarkdown library.")
    (h2 "Markdown Formats")
    (p "The CljMarkdownLibrary supports the following markdown formats:\n"
       (ol (link "Markdown" "http://daringfireball.net/projects/markdown/")
           (link "Github flavoured markdown" "https://help.github.com/articles/github-flavored-markdown")))))

(println md)