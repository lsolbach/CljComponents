;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.clj.poi.excel
  (:use [clojure.java.io]
        [org.soulspace.clj.java beans type-conversion])
  (:import [org.apache.poi.poifs.filesystem POIFSFileSystem]
           [org.apache.poi.ss.util CellRangeAddress CellReference]
           [org.apache.poi.ss.usermodel Cell CellStyle DataFormat DateUtil Font Row Sheet Workbook WorkbookFactory]
           [org.apache.poi.hssf.usermodel HSSFWorkbook]
           [org.apache.poi.xssf.usermodel XSSFWorkbook]))

; constants
(def picture-type
  {:dib  Workbook/PICTURE_TYPE_DIB
   :emf  Workbook/PICTURE_TYPE_EMF
   :jpeg Workbook/PICTURE_TYPE_JPEG
   :pict Workbook/PICTURE_TYPE_PICT
   :png  Workbook/PICTURE_TYPE_PNG
   :wmf  Workbook/PICTURE_TYPE_WMF})

(def sheet-state
  {:hidden      Workbook/SHEET_STATE_HIDDEN
   :very-hidden Workbook/SHEET_STATE_VERY_HIDDEN
   :visible     Workbook/SHEET_STATE_VISIBLE})

(def missing-cell-policy
  {:create-null-as-blank  Row/CREATE_NULL_AS_BLANK
   :return-blank-as-null  Row/RETURN_BLANK_AS_NULL
   :return-null-and-blank Row/RETURN_NULL_AND_BLANK})

(def cell-type
  {:numeric  Cell/CELL_TYPE_NUMERIC
    :string  Cell/CELL_TYPE_STRING
    :formula Cell/CELL_TYPE_FORMULA
    :blank   Cell/CELL_TYPE_BLANK
    :boolean Cell/CELL_TYPE_BOOLEAN
    :error   Cell/CELL_TYPE_ERROR})

(def cell-align
  {:center           CellStyle/ALIGN_CENTER
   :center-selection CellStyle/ALIGN_CENTER_SELECTION
   :fill             CellStyle/ALIGN_FILL
   :general          CellStyle/ALIGN_GENERAL
   :justify          CellStyle/ALIGN_JUSTIFY
   :left             CellStyle/ALIGN_LEFT
   :right            CellStyle/ALIGN_RIGHT})

(def cell-align-vertical
  {:bottom  CellStyle/VERTICAL_BOTTOM
   :center  CellStyle/VERTICAL_TOP
   :justify CellStyle/VERTICAL_JUSTIFY
   :top     CellStyle/VERTICAL_TOP})

(def cell-border
  {:dash-dot            CellStyle/BORDER_DASH_DOT
   :dash-dot-dot        CellStyle/BORDER_DASH_DOT_DOT
   :dashed              CellStyle/BORDER_DASHED
   :dotted              CellStyle/BORDER_DOTTED
   :double              CellStyle/BORDER_DOUBLE
   :hair                CellStyle/BORDER_HAIR
   :medium              CellStyle/BORDER_MEDIUM
   :medium-dash-dot     CellStyle/BORDER_MEDIUM_DASH_DOT
   :medium-dash-dot-dot CellStyle/BORDER_MEDIUM_DASH_DOT_DOT
   :medium-dashed       CellStyle/BORDER_MEDIUM_DASHED
   :none                CellStyle/BORDER_NONE
   :slanted-dash-dot    CellStyle/BORDER_SLANTED_DASH_DOT
   :thick               CellStyle/BORDER_THICK
   :thin                CellStyle/BORDER_THIN})

(def ^{:dynamic true} *workbook*)
(def ^{:dynamic true} *sheet*)
(def ^{:dynamic true} *row*)
(def ^{:dynamic true} *cell*)

; init model
(defn init-model [c args]
  (set-properties! c args)
  c)

(defn to-int
  [value]
  (coerce java.lang.Integer/TYPE value))

; cell functions
(defn get-cells
  "Returns a sequence with the cells of the row."
  [row]
  (seq row))

(defn get-cell
  [row cell-no]
  (.getCell row cell-no))

(defn physical-number-of-cells
  [row]
  (.getPhysicalNumberOfCell row))

(defn first-cell-num
  [row]
  (.getFirstCellNum row))

(defn last-cell-num
  [row]
  (.getLastCellNum row))

(defn get-cell-type
  [cell]
  (.getCellType cell))

(defn set-cell-type
  [cell type]
  (.setCellType cell type))

(defn get-cell-formula
  [cell]
  (when (= (get-cell-type cell) (cell-type :formula))
    (.getCellFormula cell)))

(defn get-cell-hyperlink
  [cell]
  (.getHyperlink cell))

(defn get-cell-result
  [cell]
  (when (= (get-cell-type cell) (cell-type :formula))
    (.getCellValue cell)))

(defn set-cell-value
  [cell value]
  (.setCellValue cell value))

(defmulti get-cell-value get-cell-type)

(defmethod get-cell-value (cell-type :numeric)
  [cell]
  (if (DateUtil/isCellDateFormatted cell)
    (.getDateCellValue cell)
    (.getNumericCellValue cell)))

(defmethod get-cell-value (cell-type :string)
  [cell]
  (.getString (.getRichStringCellValue cell)))

; TODO check if this is desired or the calculated value should be returned
(defmethod get-cell-value (cell-type :formula)
  [cell]
  (.getCellFormula cell))

(defmethod get-cell-value (cell-type :boolean)
  [cell]
    (.getBooleanCellValue cell))

(defmethod get-cell-value (cell-type :blank)
  [cell]
  "")

(defmethod get-cell-value (cell-type :error)
  [cell]
  (.getErrorCellValue cell))

(defn get-cell-values [row]
  (map get-cell-value (get-cells row)))


; row functions
(defn get-rows [sheet]
  (seq sheet))

(defn get-row [sheet row-no]
  (.getRow sheet row-no))

(defn physical-number-of-rows [sheet]
  (.getPhysicalNumberOfRows sheet))

(defn get-row-values [sheet]
  (map get-cell-values (get-rows sheet)))

; sheet functions
(defn get-sheets [wb]
  ; TODO HSSF workbooks are not iterable, branch for special implementation
  (seq wb))

(defn first-row-num [sheet]
  (.getFirstRowNum sheet))

(defn last-row-num [sheet]
  (.getLastRowNum sheet))

(defn get-sheet [wb sheet-no]
  (.getSheetAt wb sheet-no))

(defn get-sheet-values [workbook]
  (map get-row-values (get-sheets workbook)))

(defn row-insert-index
  [sheet]
  (let [last-index (last-row-num sheet)
        physical-rows (physical-number-of-rows sheet)]
    (if (== 0 physical-rows)
      0
      (+ last-index 1))))

(defn cell-insert-index
  [row]
  (let [last-index (last-cell-num row)]
    (if (== -1 last-index)
      0
      last-index)))

;
; constructors/factory functions
;
(defn create-workbook
 "Creates a new workbook."
 ([args]
   (set-properties! (XSSFWorkbook.) args))
 ([input args]
   (set-properties! (WorkbookFactory/create input) args)))

(defn create-hssf-workbook
  "Creates a new HSSF workbook."
  [args]
  (set-properties! (HSSFWorkbook.) args))

(defn create-sheet
  "Creates a new sheet."
  ([wb args]
    (set-properties! (.createSheet wb) args))
  ([wb sheet-no args]
    (set-properties! (.createSheet wb sheet-no) args)))

(defn create-row
  "Creates a new row."
  ([sheet args]
    (create-row sheet (to-int (row-insert-index sheet)) args))
  ([sheet row-no args]
    (set-properties! (.createRow sheet row-no) args)))

(defn create-cell
  "Creates a new cell."
  ([row args value]
    (create-cell row (to-int (cell-insert-index row)) args value))
  ([row cell-no args value]
    (set-cell-value (set-properties! (.createCell row cell-no) args) value)))

(defn create-cell-style
  "Creates a new cell style."
  [wb args]
  (set-properties! (.createCellStyle wb) args))

(defn create-font
  "Creates a new font."
  [wb args]
  (set-properties! (.createFont wb) args))

(defn create-data-format
  "Creates a new data format."
  [wb args]
  (set-properties! (.createDataFormat wb) args))

; IO
(defn read-workbook
  [wb input]
  (.read wb input))

(defn write-workbook
  ([file]
    (with-open [out (output-stream file)]
      (.write *workbook* out)))
  ([file wb]
    (with-open [out (output-stream file)]
      (.write wb out))))

; Macros
(defmacro workbook
  "Creates a new workbook."
  [opts & body]
  `(let [opts# ~opts]
     (binding [*workbook* (create-workbook opts#)]
       ~@body
       *workbook*)))

(defmacro sheet
  "Creates a new sheet in the current workbook."
  [opts & body]
  `(let [opts# ~opts]
     (binding [*sheet* (create-sheet *workbook* opts#)]
       ~@body
       *sheet*)))

(defmacro row
  "Creates a new row in the current sheet."
  [opts & body]
  `(let [opts# ~opts]
     (binding [*row* (create-row *sheet* opts#)]
       ~@body
       *row*)))

(defn cell
  "Creates a new cell in the current row."
  [opts value]
  (create-cell *row* opts value))

(defn cell-style
  "Creates a new cell style."
  [opts]
  (create-cell-style *workbook* opts))

(defn font
  "Creates a new font."
  [opts]
  (create-font *workbook* opts))

(defn data-format
  "Creates a new data format."
  [opts]
  (create-data-format *workbook* opts))

