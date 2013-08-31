CljMarkdownLibrary
==================
The CljMarkdownLibrary contains functions to generate markdown documents with clojure.

Example
-------

Executing this clojure namespace 

```
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
```

results in

```
Markdown Example
================
This is an example of the programmatic generation of markdown files with the CljMarkdown library.

Markdown Formats
----------------
The CljMarkdownLibrary supports the following markdown formats:
1. [Markdown](http://daringfireball.net/projects/markdown/)
2. [Github flavoured markdown](https://help.github.com/articles/github-flavored-markdown)
```

Author/Project Lead
-------------------
Ludger Solbach

Copyright
---------
© 2012-2013 Ludger Solbach

License
-------
[Eclipse Public License 1.0] (http://www.eclipse.org/legal/epl-v10.html "EPL 1.0")

Code Repository
---------------
[https://github.com/lsolbach/CljMarkdownLibrary] (https://github.com/lsolbach/CljMarkdownLibrary)

History
-------

Version 0.1.0 (--.--.201-)
--------------------------
* initial git import
