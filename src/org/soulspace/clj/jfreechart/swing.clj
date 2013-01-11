(ns org.soulspace.clj.jfreechart.swing
  (:use [org.soulspace.clj.jfreechart.dataset]
        [org.soulspace.clj.jfreechart.chart])
  (:import [org.jfree.chart ChartPanel]))

; TODO use init-swing from CljSwingLibrary for initialization (additional dependency)?
(defn chart-panel [chart]
  (let [c (ChartPanel. chart)]
    c))