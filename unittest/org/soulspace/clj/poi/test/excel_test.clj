(ns org.soulspace.clj.poi.test.excel-test
  (:use [clojure.test]
        [clojure.java.io]
        [org.soulspace.clj.poi.excel]))

(def wb (workbook (input-stream "data/test/TestSheet1.xlsx") {}))

(deftest excel-create
  )

(deftest excel-save
  )

(deftest excel-load
  )