;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.clj.jfreechart.dataset
  (:import
    [org.jfree.data DefaultKeyedValue DefaultKeyedValues]
    [org.jfree.data.general DefaultPieDataset ]
    [org.jfree.data.category DefaultCategoryDataset]
    [org.jfree.data.gantt Task TaskSeries TaskSeriesCollection]
    [org.jfree.data.statistics
     DefaultBoxAndWhiskerCategoryDataset DefaultBoxAndWhiskerXYDataset
     DefaultStatisticalCategoryDataset]
    [org.jfree.data.xy XYSeries XYSeriesCollection]
    ))

;
; Datasets
;

(defn keyed-value [key value]
  (DefaultKeyedValue. key value))
  
(defn keyed-values 
  ([items]
    (let [d (DefaultKeyedValues.)]
      (if-not (nil? items)
        (doseq [item items]
          (if (vector? item)
            (let [[key value] item]
              (.addValue d key value)))))    
      d)))

(defn pie-dataset 
  ([]
    (DefaultPieDataset.))
  ([items]
    (let [d (DefaultPieDataset.)]
      (if-not (nil? items)
        (doseq [item items]
          (if (vector? item)
            (let [[key value] item]
              (.setValue d key value)))))
      d)))
  
(defn category-dataset
  ([]
    (DefaultCategoryDataset.))
  ([items]
    (let [d (DefaultCategoryDataset.)]
      (if-not (nil? items)
        (doseq [item items]
          (if (vector? item)
            (let [[value row-key column-key] item]
              (.addValue d value row-key column-key)))))
      d)))

(defn statistical-category-dataset
  ([]
    (DefaultStatisticalCategoryDataset.))
  ([items]
    (let [d (DefaultStatisticalCategoryDataset.)]
      (if-not (nil? items)
        (doseq [item items]
          (if (vector? item)
            (let [[mean std-deviation row-key column-key] item]
              (.add d mean std-deviation row-key column-key)))))
      d)))

(defn task [desc start end]
  (Task. desc start end))

(defn task-series [id items]
  (let [ts (TaskSeries. id)]
    (if-not (nil? items)
      (doseq [item items]
        (.add ts item)))
    ts))

(defn task-series-collection
  ([]
    (TaskSeriesCollection.))
  ([items]
    (let [task-series-coll (TaskSeriesCollection.)]
      (if-not (nil? items)
        (doseq [item items]
          (.add task-series item)))
      task-series)))

(defn xy-series 
  ([id items]
    (let [xy (XYSeries. id)]
      (if-not (nil? items)
        (doseq [item items]
          (if (vector? item)
            (let [[x y] item]
              (.add xy x y))
            (.add xy item))))
      xy)))

(defn xy-series-collection 
  ([]
    (XYSeriesCollection.))
  ([items]
    (let [xy-coll (XYSeriesCollection.)]
      (if-not (nil? items)
        (doseq [item items]
          (.addSeries xy-coll item)))
      xy-coll)))
