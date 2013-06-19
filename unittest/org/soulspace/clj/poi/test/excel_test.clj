(ns org.soulspace.clj.poi.test.excel-test
  (:use [clojure.test]
        [clojure.java.io]
        [org.soulspace.clj.poi.excel]))

(deftest excel-load1
  )

(def wb (wb (input-stream "data/test/TestSheet1.xlsx")))