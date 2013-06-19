(ns org.soulspace.clj.poi.example.excel.create-workbook1
  (:use [org.soulspace.clj.poi excel]))

(def wb (workbook {} []))


(sheet
  0 {}
  [(row
     0 {}
     [(cell 0 {:value "NAW-0001"}) (cell 1 {:value "V13.1"}) (cell 2 {:value 1.5})])
   (row
     1 {}
     [(cell 0 {:value "NAW-0002"}) (cell 1 {:value "V13.2"}) (cell 2 {:value 2.3})])])

