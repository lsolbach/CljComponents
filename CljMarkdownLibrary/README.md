CljMarkdownLibrary
==================
The CljMarkdownLibrary contains functions to generate markdown documents with clojure.

Example
-------

Executing this clojure namespace 

```
(ns md
  (:require [org.soulspace.clj.markdown.markdown :as md]))

(def md
  (md/markdown
    (md/h1 "Markdown Example")
    (md/p "This is an example of the programmatic generation of markdown files with the CljMarkdown library.")
    (md/h2 "Markdown Formats")
    (md/p "The CljMarkdownLibrary supports the following markdown formats:\n"
       (md/ol (md/link "Markdown" "http://daringfireball.net/projects/markdown/")
           (md/link "Github flavoured markdown" "https://help.github.com/articles/github-flavored-markdown")))))

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
© 2012-2016 Ludger Solbach

License
-------
[Eclipse Public License 1.0] (http://www.eclipse.org/legal/epl-v10.html "EPL 1.0")

Code Repository
---------------
[https://github.com/lsolbach/CljMarkdownLibrary] (https://github.com/lsolbach/CljComponents)

