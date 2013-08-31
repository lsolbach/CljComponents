(ns md
  (:use [org.soulspace.clj.markdown markdown]))

(def md
  (str
    (h1 "Markdown Example")
    (p "This is an example of the programmatic generation of markdown files with the CljMarkdown library.")
    (h2 "Markdown Formats")
    (p (str
         "The CljMarkdownLibrary supports the following markdown formats:"
         ()
         ))
    ))