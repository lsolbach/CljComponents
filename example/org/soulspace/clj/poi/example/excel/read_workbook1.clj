(ns org.soulspace.clj.poi.example.excel.read-workbook1
  (:use [clojure.java.io]
        [org.soulspace.clj.poi excel]))

(def wb (workbook (file "data/test/TestSheet1.xlsx")
                  {:missingCellPolicy (missing-cell-policy :create-null-as-blank)}
;                  {:missingCellPolicy (missing-cell-policy :return-null-and-blank)}
;                  {:missingCellPolicy (missing-cell-policy :return-blank-as-null)}
                  ))
(def sheet0 (get-sheet wb 0))

(def row0 (get-row sheet0 0))
(def row1 (get-row sheet0 1))
(def row2 (get-row sheet0 2))

(println "(get-sheets wb):" (get-sheets wb))
(println "(get-rows sheet0):" (get-rows sheet0))
(println "(get-cells row2):" (get-cells row2))

(println "(get-cell-values row0):" (get-cell-values row0))
(println "(get-cell-values row2):" (get-cell-values row2))

(println "(get-row-values sheet0):" (get-row-values sheet0))
(println "(get-sheet-values wb):" (get-sheet-values wb))


