;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.clj.poi.example.excel.create-with-fns
  (:use [clojure.java.io]
        [org.soulspace.clj.poi excel]))

;
; currently implemented way of creating excel workbooks
;
(defn create-test []
  (let [wb (create-workbook {})]
    ;(println wb)
    (let [sht (create-sheet wb {})]
      (println sht)
      (let [rw (create-row sht {})]
        (create-cell rw {} "N0001")
        (create-cell rw {} "V13")
        (create-cell rw {} 1.5))
      (let [rw (create-row sht {})]
        (create-cell rw {} "N0002")
        (create-cell rw {} "V14")
        (create-cell rw {} 2.3))
      (let [rw (create-row sht {})]
        (create-cell rw {} "N0003")
        (create-cell rw {} "V15")
        (create-cell rw {} 4.5)))
    (with-open [out (output-stream "TestFN.xlsx")]
      (write-workbook wb out))))
