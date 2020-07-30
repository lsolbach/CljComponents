;;
;;   Copyright (c) Ludger Solbach. All rights reserved.
;;   The use and distribution terms for this software are covered by the
;;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;;   which can be found in the file license.txt at the root of this distribution.
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any other, from this software.
;;

(ns org.soulspace.cmp.jfreechart.chart
  "Contains functions for the creation of charts."
  (:require [org.soulspace.cmp.jfreechart.dataset :as ds]
            [org.soulspace.clj.java.beans :as bean])
  (:import  [org.jfree.chart.util TableOrder]
            [org.jfree.chart ChartFactory]
            [org.jfree.chart.plot PlotOrientation]
            [org.jfree.data.xy XYSeries XYSeriesCollection]))

;; Constants
(def plot-orientation {:horizontal PlotOrientation/HORIZONTAL
                       :vertical PlotOrientation/VERTICAL})

(def table-order {:by-row TableOrder/BY_ROW
                  :by-column TableOrder/BY_COLUMN})

; Plot and renderer functions
(defn plot
  "Returns the plot of the chart."
  [chart]
  (.getPlot chart))

(defn renderer
  "Returns the renderer of the plot."
  [plot]
  (.getRenderer plot))

(defn set-plot-properties
  "Sets the properties of the plot of the chart."
  [chart props]
  (bean/set-properties! (plot chart) props))

(defn set-renderer-properties
  "Sets the properties of the renderer of the chart."
  [chart props]
  (bean/set-properties! (renderer (plot chart)) props))

; Charts
(defn area-chart
  "Creates an area chart.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title x-label y-label data orientation]
   (ChartFactory/createAreaChart title x-label y-label data (plot-orientation orientation) true false false))
  ([title x-label y-label data orientation legend]
   (ChartFactory/createAreaChart title x-label y-label data (plot-orientation orientation) legend false false))
  ([title x-label y-label data orientation legend tooltips]
   (ChartFactory/createAreaChart title x-label y-label data (plot-orientation orientation) legend tooltips false))
  ([title x-label y-label data orientation legend tooltips urls]
   (ChartFactory/createAreaChart title x-label y-label data (plot-orientation orientation) legend tooltips urls)))

(defn bar-chart
  "Creates a bar chart.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title x-label y-label data orientation]
   (ChartFactory/createBarChart title x-label y-label data (plot-orientation orientation) true false false))
  ([title x-label y-label data orientation legend]
   (ChartFactory/createBarChart title x-label y-label data (plot-orientation orientation) legend false false))
  ([title x-label y-label data orientation legend tooltips]
   (ChartFactory/createBarChart title x-label y-label data (plot-orientation orientation) legend tooltips false))
  ([title x-label y-label data orientation legend tooltips urls]
   (ChartFactory/createBarChart title x-label y-label data (plot-orientation orientation) legend tooltips urls)))

(comment
(defn bar-chart-3d
  "Creates a 3D bar chart.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title x-label y-label data orientation]
   (ChartFactory/createBarChart3D title x-label y-label data (plot-orientation orientation) true false false))
  ([title x-label y-label data orientation legend]
   (ChartFactory/createBarChart3D title x-label y-label data (plot-orientation orientation) legend false false))
  ([title x-label y-label data orientation legend tooltips]
   (ChartFactory/createBarChart3D title x-label y-label data (plot-orientation orientation) legend tooltips false))
  ([title x-label y-label data orientation legend tooltips urls]
   (ChartFactory/createBarChart3D title x-label y-label data (plot-orientation orientation) legend tooltips urls)))
)

(defn box-and-whisker-chart
  "Creates a box and wisker chart.
  The optional parameter 'legend' is a boolean flag."
  ([title x-label y-label data]
   (ChartFactory/createBoxAndWhiskerChart title x-label y-label data true))
  ([title x-label y-label data legend]
   (ChartFactory/createBoxAndWhiskerChart title x-label y-label data legend)))

(defn bubble-chart
  "Creates a bubble chart.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title x-label y-label data orientation]
   (ChartFactory/createBubbleChart title x-label y-label data (plot-orientation orientation) true false false))
  ([title x-label y-label data orientation legend]
   (ChartFactory/createBubbleChart title x-label y-label data (plot-orientation orientation) legend false false))
  ([title x-label y-label data orientation legend tooltips]
   (ChartFactory/createBubbleChart title x-label y-label data (plot-orientation orientation) legend tooltips false))
  ([title x-label y-label data orientation legend tooltips urls]
   (ChartFactory/createBubbleChart title x-label y-label data (plot-orientation orientation) legend tooltips urls)))

(defn candlestick-chart
  "Creates a candle stick chart.
  The optional parameter 'legend' is a boolean flag."
  ([title x-label y-label data]
   (ChartFactory/createCandlestickChart title x-label y-label data true))
  ([title x-label y-label data legend]
   (ChartFactory/createCandlestickChart title x-label y-label data legend)))

(defn gantt-chart
  "Creates a gantt chart.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title x-label y-label data]
   (ChartFactory/createGanttChart title x-label y-label data true false false))
  ([title x-label y-label data legend]
   (ChartFactory/createGanttChart title x-label y-label data legend false false))
  ([title x-label y-label data legend tooltips]
   (ChartFactory/createGanttChart title x-label y-label data legend tooltips false))
  ([title x-label y-label data legend tooltips urls]
   (ChartFactory/createGanttChart title x-label y-label data legend tooltips urls)))

(defn high-low-chart
  "Creates a high low chart.
  The optional parameter 'legend' is a boolean flag."
  ([title x-label y-label data]
   (ChartFactory/createHighLowChart title x-label y-label data true))
  ([title x-label y-label data legend]
   (ChartFactory/createHighLowChart title x-label y-label data legend)))

(defn histogram
  "Creates a histogram.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title x-label y-label data orientation]
   (ChartFactory/createHistogram title x-label y-label data (plot-orientation orientation) true false false))
  ([title x-label y-label data orientation legend]
   (ChartFactory/createHistogram title x-label y-label data (plot-orientation orientation) legend false false))
  ([title x-label y-label data orientation legend tooltips]
   (ChartFactory/createHistogram title x-label y-label data (plot-orientation orientation) legend tooltips false))
  ([title x-label y-label data orientation legend tooltips urls]
   (ChartFactory/createHistogram title x-label y-label data (plot-orientation orientation) legend tooltips urls)))

(defn line-chart
  "Creates a line chart.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title x-label y-label data orientation]
   (ChartFactory/createLineChart title x-label y-label data (plot-orientation orientation) true false false))
  ([title x-label y-label data orientation legend]
   (ChartFactory/createLineChart title x-label y-label data (plot-orientation orientation) legend false false))
  ([title x-label y-label data orientation legend tooltips]
   (ChartFactory/createLineChart title x-label y-label data (plot-orientation orientation) legend tooltips false))
  ([title x-label y-label data orientation legend tooltips urls]
   (ChartFactory/createLineChart title x-label y-label data (plot-orientation orientation) legend tooltips urls)))

(comment
(defn line-chart-3d
  "Creates a 3D line chart.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title x-label y-label data orientation]
   (ChartFactory/createLineChart3D title x-label y-label data (plot-orientation orientation) true false false))
  ([title x-label y-label data orientation legend]
   (ChartFactory/createLineChart3D title x-label y-label data (plot-orientation orientation) legend false false))
  ([title x-label y-label data orientation legend tooltips]
   (ChartFactory/createLineChart3D title x-label y-label data (plot-orientation orientation) legend tooltips false))
  ([title x-label y-label data orientation legend tooltips urls]
   (ChartFactory/createLineChart3D title x-label y-label data (plot-orientation orientation) legend tooltips urls)))
)

(defn multiple-pie-chart
  "Creates a multiple pie chart.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title data order]
   (ChartFactory/createMultiplePieChart title data (table-order order) true false false))
  ([title data order legend]
   (ChartFactory/createMultiplePieChart title data (table-order order) legend false false))
  ([title data order legend tooltips]
   (ChartFactory/createMultiplePieChart title data (table-order order) legend tooltips false))
  ([title data order legend tooltips urls]
   (ChartFactory/createMultiplePieChart title data (table-order order) legend tooltips urls)))

(defn multiple-pie-chart-3d
  "Creates a 3D multiple pie chart.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title data order]
   (ChartFactory/createMultiplePieChart3D title data (table-order order) true false false))
  ([title data order legend]
   (ChartFactory/createMultiplePieChart3D title data (table-order order) legend false false))
  ([title data order legend tooltips]
   (ChartFactory/createMultiplePieChart3D title data (table-order order) legend tooltips false))
  ([title data order legend tooltips urls]
   (ChartFactory/createMultiplePieChart3D title data (table-order order) legend tooltips urls)))

(defn pie-chart
  "Creates a pie chart.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title data]
   (ChartFactory/createPieChart title data true false false))
  ([title data legend]
   (ChartFactory/createPieChart title data legend false false))
  ([title data legend tooltips]
   (ChartFactory/createPieChart title data legend tooltips false))
  ([title data legend tooltips urls]
   (ChartFactory/createPieChart title data legend tooltips urls)))

(defn pie-chart-3d
  "Creates a pie chart.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title data]
   (ChartFactory/createPieChart3D title data true false false))
  ([title data legend]
   (ChartFactory/createPieChart3D title data legend false false))
  ([title data legend tooltips]
   (ChartFactory/createPieChart3D title data legend tooltips false))
  ([title data legend tooltips urls]
   (ChartFactory/createPieChart3D title data legend tooltips urls)))

(defn polar-chart
  "Creates a polar chart.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title data]
   (ChartFactory/createPolarChart title data true false false))
  ([title data legend]
   (ChartFactory/createPolarChart title data legend false false))
  ([title data legend tooltips]
   (ChartFactory/createPolarChart title data legend tooltips false))
  ([title data legend tooltips urls]
   (ChartFactory/createPolarChart title data legend tooltips urls)))

(defn ring-chart
  "Creates a ring chart.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title data]
   (ChartFactory/createRingChart title data true false false))
  ([title data legend]
   (ChartFactory/createRingChart title data legend false false))
  ([title data legend tooltips]
   (ChartFactory/createRingChart title data legend tooltips false))
  ([title data legend tooltips urls]
   (ChartFactory/createRingChart title data legend tooltips urls)))

(defn scatter-plot
  "Creates a scatter plot.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title x-label y-label data orientation]
   (ChartFactory/createScatterPlot title x-label y-label data (plot-orientation orientation) true false false))
  ([title x-label y-label data orientation legend]
   (ChartFactory/createScatterPlot title x-label y-label data (plot-orientation orientation) legend false false))
  ([title x-label y-label data orientation legend tooltips]
   (ChartFactory/createScatterPlot title x-label y-label data (plot-orientation orientation) legend tooltips false))
  ([title x-label y-label data orientation legend tooltips urls]
   (ChartFactory/createScatterPlot title x-label y-label data (plot-orientation orientation) legend tooltips urls)))

(defn stacked-bar-chart
  "Creates a stacked bar chart.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title x-label y-label data orientation]
   (ChartFactory/createStackedBarChart title x-label y-label data (plot-orientation orientation) true false false))
  ([title x-label y-label data orientation legend]
   (ChartFactory/createStackedBarChart title x-label y-label data (plot-orientation orientation) legend false false))
  ([title x-label y-label data orientation legend tooltips]
   (ChartFactory/createStackedBarChart title x-label y-label data (plot-orientation orientation) legend tooltips false))
  ([title x-label y-label data orientation legend tooltips urls]
   (ChartFactory/createStackedBarChart title x-label y-label data (plot-orientation orientation) legend tooltips urls)))

(comment
(defn stacked-bar-chart-3d
  "Creates a 3D stacked bar chart.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title x-label y-label data orientation]
   (ChartFactory/createStackedBarChart3D title x-label y-label data (plot-orientation orientation) true false false))
  ([title x-label y-label data orientation legend]
   (ChartFactory/createStackedBarChart3D title x-label y-label data (plot-orientation orientation) legend false false))
  ([title x-label y-label data orientation legend tooltips]
   (ChartFactory/createStackedBarChart3D title x-label y-label data (plot-orientation orientation) legend tooltips false))
  ([title x-label y-label data orientation legend tooltips urls]
   (ChartFactory/createStackedBarChart3D title x-label y-label data (plot-orientation orientation) legend tooltips urls)))
)

(defn stacked-xy-area-chart
  "Creates a stacked xy area chart.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title x-label y-label data orientation]
   (ChartFactory/createStackedXYAreaChart title x-label y-label data (plot-orientation orientation) true false false))
  ([title x-label y-label data orientation legend]
   (ChartFactory/createStackedXYAreaChart title x-label y-label data (plot-orientation orientation) legend false false))
  ([title x-label y-label data orientation legend tooltips]
   (ChartFactory/createStackedXYAreaChart title x-label y-label data (plot-orientation orientation) legend tooltips false))
  ([title x-label y-label data orientation legend tooltips urls]
   (ChartFactory/createStackedXYAreaChart title x-label y-label data (plot-orientation orientation) legend tooltips urls)))

(defn time-series-chart
  "Creates a time series chart.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title x-label y-label data]
   (ChartFactory/createTimeSeriesChart title x-label y-label data true false false))
  ([title x-label y-label data legend]
   (ChartFactory/createTimeSeriesChart title x-label y-label data legend false false))
  ([title x-label y-label data legend tooltips]
   (ChartFactory/createTimeSeriesChart title x-label y-label data legend tooltips false))
  ([title x-label y-label data legend tooltips urls]
   (ChartFactory/createTimeSeriesChart title x-label y-label data legend tooltips urls)))

(defn wafer-map-chart
  "Creates a wafer map chart.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title data orientation]
   (ChartFactory/createWaferMapChart title data (plot-orientation orientation) true false false))
  ([title data orientation legend]
   (ChartFactory/createWaferMapChart title data (plot-orientation orientation) legend false false))
  ([title data orientation legend tooltips]
   (ChartFactory/createWaferMapChart title data (plot-orientation orientation) legend tooltips false))
  ([title data orientation legend tooltips urls]
   (ChartFactory/createWaferMapChart title data (plot-orientation orientation) legend tooltips urls)))

(defn waterfall-chart
  "Creates a waterfall chart.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title x-label y-label data orientation]
   (ChartFactory/createWaterfallChart title x-label y-label data (plot-orientation orientation) true false false))
  ([title x-label y-label data orientation legend]
   (ChartFactory/createWaterfallChart title x-label y-label data (plot-orientation orientation) legend false false))
  ([title x-label y-label data orientation legend tooltips]
   (ChartFactory/createWaterfallChart title x-label y-label data (plot-orientation orientation) legend tooltips false))
  ([title x-label y-label data orientation legend tooltips urls]
   (ChartFactory/createWaterfallChart title x-label y-label data (plot-orientation orientation) legend tooltips urls)))

(defn wind-plot
  "Creates a wind plot.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title x-label y-label data]
   (ChartFactory/createWindPlot title x-label y-label data true false false))
  ([title x-label y-label data legend]
   (ChartFactory/createWindPlot title x-label y-label data legend false false))
  ([title x-label y-label data legend tooltips]
   (ChartFactory/createWindPlot title x-label y-label data legend tooltips false))
  ([title x-label y-label data legend tooltips urls]
   (ChartFactory/createWindPlot title x-label y-label data legend tooltips urls)))

(defn xy-area-chart
  "Creates a xy area chart.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title x-label y-label data orientation]
   (ChartFactory/createXYAreaChart title x-label y-label data (plot-orientation orientation) true false false))
  ([title x-label y-label data orientation legend]
   (ChartFactory/createXYAreaChart title x-label y-label data (plot-orientation orientation) legend false false))
  ([title x-label y-label data orientation legend tooltips]
   (ChartFactory/createXYAreaChart title x-label y-label data (plot-orientation orientation) legend tooltips false))
  ([title x-label y-label data orientation legend tooltips urls]
   (ChartFactory/createXYAreaChart title x-label y-label data (plot-orientation orientation) legend tooltips urls)))

(defn xy-bar-chart
  "Creates a xy bar chart.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title x-label date-axis y-label data orientation]
   (ChartFactory/createXYBarChart title x-label date-axis y-label data (plot-orientation orientation) true false false))
  ([title x-label date-axis y-label data orientation legend]
   (ChartFactory/createXYBarChart title x-label date-axis y-label data (plot-orientation orientation) legend false false))
  ([title x-label date-axis y-label data orientation legend tooltips]
   (ChartFactory/createXYBarChart title x-label date-axis y-label data (plot-orientation orientation) legend tooltips false))
  ([title x-label date-axis y-label data orientation legend tooltips urls]
   (ChartFactory/createXYBarChart title x-label date-axis y-label data (plot-orientation orientation) legend tooltips urls)))

(defn xy-line-chart
  "Creates a xy line chart.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title x-label y-label data orientation]
   (ChartFactory/createXYLineChart title x-label y-label data (plot-orientation orientation) true false false))
  ([title x-label y-label data orientation legend]
   (ChartFactory/createXYLineChart title x-label y-label data (plot-orientation orientation) legend false false))
  ([title x-label y-label data orientation legend tooltips]
   (ChartFactory/createXYLineChart title x-label y-label data (plot-orientation orientation) legend tooltips false))
  ([title x-label y-label data orientation legend tooltips urls]
   (ChartFactory/createXYLineChart title x-label y-label data (plot-orientation orientation) legend tooltips urls)))

(defn xy-step-area-chart
  "Creates a xy step area chart.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title x-label y-label data orientation]
   (ChartFactory/createXYStepAreaChart title x-label y-label data (plot-orientation orientation) true false false))
  ([title x-label y-label data orientation legend]
   (ChartFactory/createXYStepAreaChart title x-label y-label data (plot-orientation orientation) legend false false))
  ([title x-label y-label data orientation legend tooltips]
   (ChartFactory/createXYStepAreaChart title x-label y-label data (plot-orientation orientation) legend tooltips false))
  ([title x-label y-label data orientation legend tooltips urls]
   (ChartFactory/createXYStepAreaChart title x-label y-label data (plot-orientation orientation) legend tooltips urls)))

(defn xy-step-chart
  "Creates a xy step chart.
  The optional parameters 'legend', 'tooltips', 'urls' are boolean flags."
  ([title x-label y-label data orientation]
   (ChartFactory/createXYStepChart title x-label y-label data (plot-orientation orientation) true false false))
  ([title x-label y-label data orientation legend]
   (ChartFactory/createXYStepChart title x-label y-label data (plot-orientation orientation) legend false false))
  ([title x-label y-label data orientation legend tooltips]
   (ChartFactory/createXYStepChart title x-label y-label data (plot-orientation orientation) legend tooltips false))
  ([title x-label y-label data orientation legend tooltips urls]
   (ChartFactory/createXYStepChart title x-label y-label data (plot-orientation orientation) legend tooltips urls)))
