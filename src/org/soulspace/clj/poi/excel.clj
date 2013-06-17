(ns org.soulspace.clj.poi.ss
  (:use [org.soulspace.clj.java beans])
  (:import [org.apache.poi.poifs Filesystem]
           [org.apache.poi.ss.util CellRangeAddress]
           [org.apache.poi.ss.usermodel Cell CellReference CellStyle DataFormat Font Row Sheet Workbook WorkbookFactory]
           [org.apache.poi.hssf.usermodel HSSFWorkbook]
           [org.apache.poi.xssf.usermodel XSSFWorkbook]))

(def cell-types {:numeric Cell/CELL_TYPE_NUMERIC
                 :string  Cell/CELL_TYPE_STRING
                 :formula Cell/CELL_TYPE_FORMULA
                 :blank   Cell/CELL_TYPE_BLANK
                 :boolean Cell/CELL_TYPE_BOOLEAN
                 :error   Cell/CELL_TYPE_ERROR})

; init model
(defn init-model [c args]
  (set-properties! c args)
  c)

(defn workbook
  ([args]
    (init-model HSSFWorkbook. args))
  ([input args]
    (init-model (WorkbookFactory/create input) args)))

(defn read-workbook [wb input]
  (.read wb input))

(defn write-workbook [wb output]
  (.write wb output))

(defn cell-style [wb args]
  (init-model (.createCellStyle wb) args))

(defn font [wb args]
  (init-model (.createFont wb) args))

(defn data-format [wb args]
  (init-model (.createDataFormat wb) args))

(defn sheet [wb sheet-no args]
  (init-model (.createSheet wb sheet-no) args))

(defn get-sheet [wb sheet-no]
  (.getSheetAt wb sheet-no))

(defn row [sheet row-no args]
  (init-model (.createRow sheet row-no) args))

(defn get-row [sheet row-no]
  (.getRow sheet row-no))

(defn physical-number-of-rows [sheet]
  (.getPhysicalNumberOfRows sheet))

(defn cell [row cell-no args]
  (init-model (.createCell row cell-no) args))

(defn get-cell [row cell-no]
  (.getCell row cell-no))

(defn physical-number-of-cells [row]
  (.getPhysicalNumberOfCell row))

(defn set-cell-value [cell value]
  (.setCellValue cell value))

(defmulti get-cell-value get-cell-type)

(defmethod get-cell-value (cell-type :numeric)
  [cell]
  (if (.isCellDateFormatted cell)
    (.getDateCellValue cell)
    (.getNumericCellValue cell)))

(defmethod get-cell-value (cell-type :string)
  [cell]
  (.getString (.getRichStringCellValue cell)))

(defmethod get-cell-value (cell-type :formula)
  [cell]
  (.getCellFormula cell))

(defmethod get-cell-value (cell-type :boolean)
  [cell]
    (.getBooleanCellValue cell))

(defmethod get-cell-value (cell-type :blank)
  [cell])

(defmethod get-cell-value (cell-type :error)
  [cell])

(defn get-cell-type [cell]
  (.getCellType cell))

(defn set-cell-type [cell type]
  (.setCellType cell type))
