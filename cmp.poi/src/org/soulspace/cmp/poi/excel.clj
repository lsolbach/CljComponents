;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.cmp.poi.excel
  (:require [clojure.java.io :as io]
            [clojure.set :as set]
            [org.soulspace.clj.java.beans :as b]
            [org.soulspace.clj.type-conversion :as tc])
  (:import [org.apache.poi.poifs.filesystem POIFSFileSystem]
           [org.apache.poi.ss.util CellRangeAddress CellReference]
           [org.apache.poi.ss.usermodel Cell CellStyle DataFormat DateUtil Font IndexedColors Row Sheet Workbook WorkbookFactory]
           [org.apache.poi.hssf.usermodel HSSFWorkbook DVConstraint]
           [org.apache.poi.xssf.usermodel XSSFWorkbook XSSFDataValidationConstraint XSSFDataValidationHelper XSSFColor]))

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
   :string   Cell/CELL_TYPE_STRING
   :formula  Cell/CELL_TYPE_FORMULA
   :blank    Cell/CELL_TYPE_BLANK
   :boolean  Cell/CELL_TYPE_BOOLEAN
   :error    Cell/CELL_TYPE_ERROR
   :null    nil})

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

(def cell-fill-style
  {:no-fill             CellStyle/NO_FILL
   :solid-foreground    CellStyle/SOLID_FOREGROUND
   :fine-dots           CellStyle/FINE_DOTS
   :alt-bars            CellStyle/ALT_BARS
   :sparse-dots         CellStyle/SPARSE_DOTS
   :thick-horz-bands    CellStyle/THICK_HORZ_BANDS
   :thick-vert-bands    CellStyle/THICK_VERT_BANDS
   :thick-backward-diag CellStyle/THICK_BACKWARD_DIAG
   :thick-forward-diag  CellStyle/THICK_FORWARD_DIAG
   :thin-horz-bands     CellStyle/THIN_HORZ_BANDS
   :thin-vert-bands     CellStyle/THIN_VERT_BANDS
   :thin-backward-diag  CellStyle/THIN_BACKWARD_DIAG
   :thin-forward-diag   CellStyle/THIN_FORWARD_DIAG
   :big-spots           CellStyle/BIG_SPOTS
   :bricks              CellStyle/BRICKS
   :squares             CellStyle/SQUARES
   :diamonds            CellStyle/DIAMONDS
   :less-dots           CellStyle/LESS_DOTS
   :least-dots          CellStyle/LEAST_DOTS})

(def picture-type-key (set/map-invert picture-type))
(def sheet-state-key (set/map-invert sheet-state))
(def missing-cell-policy-key (set/map-invert missing-cell-policy))
(def cell-type-key (set/map-invert cell-type))
(def cell-align-key (set/map-invert cell-align))
(def cell-align-vertical-key (set/map-invert cell-align-vertical))
(def cell-border-key (set/map-invert cell-border))
(def cell-fill-style-key (set/map-invert cell-fill-style))

; defines the current values, which will be bound thread locally by the new and select macros
(def ^{:dynamic true} *workbook*)
(def ^{:dynamic true} *sheet*)
(def ^{:dynamic true} *row*)
(def ^{:dynamic true} *cell*)

(defn xssf-workbook?
  "Checks if the given workbook is a xssf workbook (a.k.a. an '.xlsx' file)."
  [wb])

(defn to-int
  "Coerces a (numeric) value to an integer."
  [value]
  (tc/coerce java.lang.Integer/TYPE value))

(defn color
  "Creates an 'extended' color."
  ([rgb]
   (XSSFColor. (java.awt.Color. rgb)))
  ([r g b]
   (XSSFColor. (java.awt.Color. r g b))))

(defn color-index
  "Returns the index of the color."
  [color]
  (.getIndex color))

(defn get-sheets
  "Returns a sequence of the sheets in the workbook."
  [wb]
  ; TODO HSSF workbooks are not iterable, branch for special implementation
  (seq wb))

(defn get-sheet
  "Returns the sheet at the given index."
  [wb sheet-no]
  (.getSheetAt wb sheet-no))

(defn physical-number-of-rows
  "Returns the physical number of rows in the sheet."
  [sheet]
  (.getPhysicalNumberOfRows sheet))

(defn first-row-num
  "Returns the index of the first row in the sheet."
  [sheet]
  (.getFirstRowNum sheet))

(defn last-row-num
  "Returns the index of the last row in the sheet."
  [sheet]
  (.getLastRowNum sheet))

(defn get-rows
  "Returns a sequence of the physically defined rows of the sheet."
  [sheet]
  (seq sheet))

(defn get-row
  "Returns the row with the index row-no of the sheet."
  [sheet row-no]
  (.getRow sheet row-no))

(defn get-all-rows
  "Returns a sequence of all the defined rows in the sheet."
  [sheet]
  (map #(get-row sheet %) (range (first-row-num sheet) (last-row-num sheet))))

(defn physical-number-of-cells
  "Returns the physical number of cells in the row."
  ([]
   (physical-number-of-cells *row*))
  ([row]
   (.getPhysicalNumberOfCell row)))

(defn first-cell-num
  "Returns the index of the first cell in the row."
  ([]
   (first-cell-num *row*))
  ([row]
   (.getFirstCellNum row)))

(defn last-cell-num
  "Returns the index of the last cell in the row."
  ([]
   (last-cell-num *row*))
  ([row]
   (.getLastCellNum row)))

(defn get-column-index
  "Returns the column index of the cell."
  ([]
   (get-column-index *cell*))
  ([cell]
   (.getColumnIndex cell)))

(defn get-row-index
  "Returns the row index of the cell."
  ([]
   (get-row-index *cell*))
  ([cell]
   (.getRowIndex cell)))

(defn get-cell-type
  "Returns the type of the cell."
  ([]
   (get-cell-type *cell*))
  ([cell]
   (if (nil? cell)
     :null
     (cell-type-key (.getCellType cell)))))

(defn set-cell-type
  "Sets the type of the cell to type."
  ([type]
   (set-cell-type *cell* type))
  ([cell type]
   (.setCellType cell (cell-type type))))

(defn get-cell
  "Returns the cell with the index cell-no of the row."
  ([cell-no]
   (get-cell *row* cell-no))
  ([row cell-no]
   (if (nil? row)
     nil
     (.getCell row cell-no))))

(defn get-cells
  "Returns a sequence with the cells of the row."
  [row]
  (seq row))

(defn get-all-cells
  "Returns a sequence of all the defined cells in the row."
  ([row]
   (get-all-cells 0))
  ([row min-cells]
   (if (nil? row)
     nil
     (map #(get-cell row %) (range (min (first-cell-num row) 0) (max (last-cell-num row) min-cells))))))

(defn get-cell-formula
  "Returns the formula of the cell if the cell is of type :formula."
  ([]
   (get-cell-formula *cell*))
  ([cell]
   (when (= (get-cell-type cell) :formula)
     (.getCellFormula cell))))

(defn get-cell-hyperlink
  "Returns the hyperlink of the cell (if any)."
  ([]
   (get-cell-hyperlink *cell*))
  ([cell]
   (.getHyperlink cell)))

(defn cell-result
  "Returns the result of the formula evaluation if the cell is of type :formula."
  ([cell]
   (when (= (get-cell-type cell) :formula)
     (.getCellValue cell))))

(defn set-cell-value
  "Sets the value of the cell."
  [cell value]
  (.setCellValue cell value))

(defmulti cell-value
  "Returns the value of the cell (based on the type of the cell)."
  get-cell-type)

(defmethod cell-value :numeric
  [cell]
  (if (DateUtil/isCellDateFormatted cell)
    (.getDateCellValue cell)
    (.getNumericCellValue cell)))

(defmethod cell-value :string
  [cell]
  (.getString (.getRichStringCellValue cell)))

; TODO check if this is desired or the calculated value should be returned
(defmethod cell-value :formula
  [cell]
  (.getCellFormula cell))

(defmethod cell-value :boolean
  [cell]
  (.getBooleanCellValue cell))

(defmethod cell-value :blank
  [cell]
  "")

(defmethod cell-value :error
  [cell]
  (.getErrorCellValue cell))

(defmethod cell-value :null
  [cell]
  nil)

(defn cell-values
  "Returns a sequence of the cell values of the row."
  ([]
   (cell-values *row*))
  ([row]
   (map cell-value (get-cells row))))

(defn all-cell-values
  "Returns a sequence of the cell values of the row."
  ([]
   (all-cell-values *row* 0))
  ([row]
   (all-cell-values row 0))
  ([row min-cells]
   (map cell-value (get-all-cells row min-cells))))

(defn row-values
  "Returns a sequence of values of the sheets in the workbook."
  [sheet]
  (map cell-values (get-rows sheet)))

(defn all-row-values
  "Returns a sequence of values of the sheets in the workbook."
  ([]
   (all-row-values *sheet* 0))
  ([sheet]
   (all-row-values sheet 0))
  ([sheet min-cells]
   (map #(all-cell-values % min-cells) (filter seq (get-all-rows sheet)))))

(defn all-row-cells
  "Returns a sequence of values of the sheets in the workbook."
  ([]
   (all-row-values *sheet* 0))
  ([sheet]
   (all-row-values sheet 0))
  ([sheet min-cells]
   (map #(get-all-cells % min-cells) (filter seq (get-all-rows sheet)))))

; sheet functions
(defn sheet-values
  "Returns a sequence of phyically defined values of the sheets in the workbook."
  ([]
   (sheet-values *workbook*))
  ([workbook]
   (map row-values (get-sheets workbook))))

(defn all-sheet-values
  "Returns a sequence of all the values of the sheets in the workbook. Returns at least 'min-cells' values per row."
  ([]
   (all-sheet-values *workbook* 0 0))
  ([workbook]
   (all-sheet-values workbook 0))
  ([workbook min-cells]
   (map #(all-row-values % min-cells) (get-sheets workbook))))

(defn all-sheet-cells
  "Returns a sequence of all the cells of the sheets in the workbook. Returns at least 'min-cells' cells per row."
  ([]
   (all-sheet-cells *workbook* 0))
  ([workbook]
   (all-sheet-cells workbook 0))
  ([workbook min-cells]
   (map #(all-row-cells %  min-cells) (get-sheets workbook))))

(defn row-insert-index
  "Returns the index of next 'free' row in the sheet."
  [sheet]
  (let [last-index (last-row-num sheet)
        physical-rows (physical-number-of-rows sheet)]
    (if (== 0 physical-rows)
      0
      (+ last-index 1))))

(defn cell-insert-index
  "Returns the index of next 'free' cell in the row."
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
 ([opts]
  (b/set-properties! (XSSFWorkbook.) opts))
 ([file opts]
  (with-open [input (input-stream file)]
    (b/set-properties! (WorkbookFactory/create input) opts))))

(defn create-hssf-workbook
  "Creates a new HSSF workbook."
  [opts]
  (b/set-properties! (HSSFWorkbook.) opts))

(defn create-sheet
  "Creates a new sheet."
  ([wb opts]
   (b/set-properties! (.createSheet wb) opts))
  ([wb sheet-no opts]
   (b/set-properties! (.createSheet wb sheet-no) opts)))

(defn create-row
  "Creates a new row."
  ([sheet opts]
   (create-row sheet (to-int (row-insert-index sheet)) opts))
  ([sheet row-no opts]
   (b/set-properties! (.createRow sheet row-no) opts)))

(defn create-cell
  "Creates a new cell."
  ([row opts value]
   (create-cell row (to-int (cell-insert-index row)) opts value))
  ([row cell-no opts value]
   (set-cell-value (set-properties! (.createCell row cell-no) opts) value)))

(defn create-cell-style
  "Creates a new cell style."
  [wb opts]
  (b/set-properties! (.createCellStyle wb) opts))

(defn create-font
  "Creates a new font."
  [wb opts]
  (b/set-properties! (.createFont wb) opts))

(defn create-data-format
  "Creates a new data format."
  [wb opts]
  (b/set-properties! (.createDataFormat wb) opts))

(defn create-cell-range-address
  "Creates a cell range address."
  ([v]
   (CellRangeAddress/valueOf v))
  ([start-row end-row start-column end-column]
   (CellRangeAddress. start-row end-row start-column end-column)))

;
;
;
(defn add-merged-region
  "Defines merged cells in a sheet."
  ([cell-range-address]
   (.addMergedRegion *sheet* cell-range-address))
  ([sheet cell-range-address]
   (.addMergedRegion sheet cell-range-address))
  ([start-row end-row start-column end-column]
   (.addMergedRegion *sheet* (create-cell-range-address start-row end-row start-column end-column)))
  ([sheet start-row end-row start-column end-column]
   (.addMergedRegion sheet (create-cell-range-address start-row end-row start-column end-column))))

;
; IO
;
(defn read-workbook
  "Reads a workbook from file."
  ([file]
   (with-open [input (io/input-stream file)]
     (.read *workbook* input)))
  ([wb file]
   (with-open [input (io/input-stream file)]
     (.read wb input))))

(defn write-workbook
  "Writes a workbook to file."
  ([file]
   (with-open [out (io/output-stream file)]
     (.write *workbook* out)))
  ([file wb]
   (with-open [out (io/output-stream file)]
     (.write wb out))))

;
; Conveniance functions
;
(defn new-cell-style
  "Creates a new cell style in the current workbook."
  [opts]
  (create-cell-style *workbook* opts))

(defn new-font
  "Creates a new font in the current workbook."
  [opts]
  (create-font *workbook* opts))

(defn new-data-format
  "Creates a new data format in the current workbook."
  [opts]
  (create-data-format *workbook* opts))

; Macros
(defmacro with-workbook
  "Reads the workbook from file, executes the body with the workbook and writes the workbook back to file."
  [file & body]
  `(binding [*workbook* (create-workbook ~file {})]
     ~@body
     (write-workbook ~file)
     *workbook*))

(defmacro new-workbook
  "Creates a new workbook."
  [opts & body]
  `(let [opts# ~opts]
     (binding [*workbook* (create-workbook opts#)]
       ~@body
       *workbook*)))

(defmacro new-sheet
  "Creates a new sheet in the current workbook."
  [opts & body]
  `(let [opts# ~opts]
     (binding [*sheet* (create-sheet *workbook* opts#)]
       ~@body
       *sheet*)))

(defmacro select-sheet
  "Updates the sheet in the current workbook."
  [sheet-no & body]
  `(binding [*sheet* (get-sheet *workbook* ~sheet-no)]
     ~@body
     *sheet*))

(defmacro new-row
  "Creates a new row in the current sheet."
  [opts & body]
  `(let [opts# ~opts]
     (binding [*row* (create-row *sheet* opts#)]
       ~@body
       *row*)))

(defmacro select-row
  "Updates the row in the current sheet."
  [row-no & body]
  `(binding [*row* (get-row *sheet* ~row-no)]
     ~@body
     *row*))

(defmacro new-cell
  "Creates a new cell in the current row."
  [opts value & body]
  `(let [opts# ~opts]
     (binding [*cell* (create-cell *row* opts# ~value)]
       ~@body
       *cell*)))

(defmacro select-cell
  "Updates the cell in the current row."
  [cell-no & body]
  `(binding [*cell* (get-cell *row* ~cell-no)]
     ~@body
     *cell*))
