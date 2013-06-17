(ns org.soulspace.clj.poi.ss
  (:use [org.soulspace.clj.java beans])
  (:import [org.apache.poi.poifs Filesystem]
           [org.apache.poi.ss.util CellRangeAddress]
           [org.apache.poi.ss.usermodel Cell CellStyle DataFormat Font Row Sheet Workbook WorkbookFactory]
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

(defn create-workbook
  ([]
    HSSFWorkbook.)
  ([input]
  (WorkbookFactory/create input)))

(defn read-workbook [wb input]
  (.read wb input))

(defn write-workbook [wb output]
  (.write wb output))

(defn create-cell-style [wb args]
  (init-model (.createCellStyle wb) args))

(defn create-font [wb args]
  (init-model (.createFont wb) args))

(defn create-data-format [wb args]
  (init-model (.createDataFormat wb) args))

(defn create-sheet [wb sheet-no args]
  (init-model (.createSheet wb sheet-no) args))

(defn get-sheet [wb sheet-no]
  (.getSheetAt wb sheet-no))

(defn create-row [sheet row-no args]
  (init-model (.createRow sheet row-no) args))

(defn get-row [sheet row-no]
  (.getRow sheet row-no))

(defn physical-number-of-rows [sheet]
  (.getPhysicalNumberOfRows sheet))

(defn create-cell [row cell-no args]
  (init-model (.createCell row cell-no) args))

(defn get-cell [row cell-no]
  (.getCell row cell-no))

(defn physical-number-of-cells [row]
  (.getPhysicalNumberOfCell row))

(defn set-cell-value [cell]
  )

(defn get-cell-value [cell]
  )

(defn get-cell-type [cell]
  )

(defn set-cell-type [cell type]
  (.setCellType cell type))
