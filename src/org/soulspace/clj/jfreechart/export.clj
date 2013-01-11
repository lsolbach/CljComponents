(ns org.soulspace.clj.jfreechart.export
  (:import [org.jfree.chart ChartUtilities]))

(defn save-chart-as-png [file chart width height]
  (ChartUtilities/saveChartAsPNG file chart width height))

(defn save-chart-as-jpg [file chart width height]
  (ChartUtilities/saveChartAsJPEG file chart width height))

(defn draw-chart-with-graphics2d
  ([chart rectangle2d graphics2d]
    (.draw chart graphics2d rectangle2d)
    graphics2d))
