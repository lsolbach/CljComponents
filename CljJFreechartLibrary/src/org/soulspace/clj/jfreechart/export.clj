;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
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
