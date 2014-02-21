;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.clj.poi.test.excel-test
  (:use [clojure.test]
        [clojure.java.io]
        [org.soulspace.clj.poi.excel]))

(def wb (create-workbook (input-stream "data/test/TestSheet1.xlsx") {}))

(deftest excel-create
  )

(deftest excel-save
  )

(deftest excel-load
  )
