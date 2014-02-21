(ns org.soulspace.clj.poi.example.excel.create-with-macros
  (:use [org.soulspace.clj.poi excel]))


; 
; this is the way I like to create excel workbooks from clojure
;
(defn create-test []
  (write-workbook 
    "TestMACRO.xlsx"
    (workbook {}
              (sheet {}
                     (row {}
                          (cell {} "N0001")
                          (cell {} "V13")
                          (cell {} 1.5))
                     (row {}
                          (cell {} "N0002")
                          (cell {} "V14")
                          (cell {} 2.3))
                     (row {}
                          (cell {} "N0003")
                          (cell {} "V15")
                          (cell {} 4.5))))))
