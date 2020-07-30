;;
;;   Copyright (c) Ludger Solbach. All rights reserved.
;;   The use and distribution terms for this software are covered by the
;;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;;   which can be found in the file license.txt at the root of this distribution.
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any other, from this software.
;;

(ns org.soulspace.cmp.jfreechart.export
  (:import [org.jfree.chart ChartUtils]))

(defn save-chart-as-png
  "Save the 'chart' in PNG format with the specified 'width' and 'height' to the given 'file'."
  [file chart width height]
  (ChartUtils/saveChartAsPNG file chart width height))

(defn save-chart-as-jpg
  "Save the 'chart' in JPEG format with the specified 'width' and 'height' to the given 'file'."
  [file chart width height]
  (ChartUtils/saveChartAsJPEG file chart width height))

(defn draw-chart-with-graphics2d
  "Draws the 'chart' with the dimensions of 'rectangle2d' in the given 'graphics2d' context and returns the context."
  ([chart rectangle2d graphics2d]
   (.draw chart graphics2d rectangle2d)
   graphics2d))
