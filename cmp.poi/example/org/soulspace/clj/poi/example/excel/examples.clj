;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.cmp.poi.example.excel.examples
  (:use [org.soulspace.cmp.poi excel])
  (:import [org.apache.poi.ss.usermodel IndexedColors]))

; 
; this is the way I like to create excel workbooks from clojure
;
(defn create-test
  "Create new workbook, fill in some data and write it to disk."
  []
  (write-workbook 
    "TestMACRO.xlsx"
    (new-workbook {}
              (let [style1 (new-cell-style {:fillForegroundColor (color-index IndexedColors/AQUA)
                                            :fillPattern (cell-fill-style :solid-foreground)
                                            :alignment (cell-align :center)})]
                (new-sheet {}
                           (new-row {}
                                (new-cell {:cellStyle style1} "N0001")
                                (new-cell {} "V13")
                                (new-cell {} 1.5))
                           (new-row {}
                                    (new-cell {:cellStyle style1} "N0002")
                                    (new-cell {} "V14")
                                    (new-cell {} 2.3))
                           (new-row {}
                                    (new-cell {:cellStyle style1} "N0003")
                                    (new-cell {} "V15")
                                    (new-cell {} 4.5)))))))

(defn append-row
  "Update an existing workbook with a new row of data in the first sheet."
  []
  (with-workbook "TestMACRO.xlsx"
    (update-sheet 0 {}
           (new-row {}
                (new-cell {} 1.5)
                (new-cell {} 2.5)
                (new-cell {} 3.5)))))
