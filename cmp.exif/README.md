cmp.exif
========
The cmp.exif is a library for reading EXIF tags on images. It is a Clojure wrapper for [metadata-extractor](https://github.com/drewnoakes/metadata-extractor).
In contrast to [exif-processor](https://github.com/joshuamiller/exif-processor), cmp.exif returns the directories with the tags and does not filter the entries.


Copyright
---------
© 2017-2020 Ludger Solbach

Usage
-----
### Leiningen

```
[org.soulspace.clj/cmp.exif "0.1.0"]
```

### Example code

```
(ns exif-example
  (:require [org.soulspace.cmp.exif :as exif]))
  
  (def image-file "example.jpg") ; NOTE: use a path to your image

  ;; parse the metadata into a map of maps of the shape {dir-name {tag-name tag-value}}
  (parse-metadata (read-metadata image-file))

  ;; just print the meta-data
  (print-metadata image-file)
```

License
-------
[Eclipse Public License 1.0](http://www.eclipse.org/legal/epl-v10.html)

Code Repository
---------------
[CljComponents on GitHub](https://github.com/lsolbach/CljComponents)
