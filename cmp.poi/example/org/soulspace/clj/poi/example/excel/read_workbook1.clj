;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.cmp.poi.example.excel.read-workbook1
  (:use [clojure.java.io]
        [org.soulspace.cmp.poi excel]))

(def wb (create-workbook (file "data/test/TestSheet1.xlsx")
                  {:missingCellPolicy (missing-cell-policy :create-null-as-blank)}))
;                  {:missingCellPolicy (missing-cell-policy :return-null-and-blank)}
;                  {:missingCellPolicy (missing-cell-policy :return-blank-as-null)}

(def sheet0 (get-sheet wb 0))

(def row0 (get-row sheet0 0))
(def row1 (get-row sheet0 1))
(def row2 (get-row sheet0 2))

;(all-sheet-values wb 4)

(defn all-row-cells
  "Returns a sequence of values of the sheets in the workbook."
  ([]
   (all-row-values *sheet* 0))
  ([sheet]
   (all-row-values sheet 0))
  ([sheet min-cells]
   (map #(get-all-cells % min-cells) (filter seq (get-all-rows sheet)))))

(defn get-all-sheet-cells
  [min-cells]
  (map #(all-row-cells %  min-cells) (get-sheets wb)))

;(println "(get-sheets wb):" (get-sheets wb))
;(println "(get-rows sheet0):" (get-rows sheet0))
;(println "(get-cells row2):" (get-cells row2))

;(println "(cell-values row0):" (cell-values row0))
;(println "(cell-values row2):" (cell-values row2))

;(println "(row-values sheet0):" (row-values sheet0))
;(println "(sheet-values wb):" (sheet-values wb))