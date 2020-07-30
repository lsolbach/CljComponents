;;
;;   Copyright (c) Ludger Solbach. All rights reserved.
;;   The use and distribution terms for this software are covered by the
;;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;;   which can be found in the file license.txt at the root of this distribution.
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any other, from this software.
;;

(ns org.soulspace.cmp.jfreechart.dataset
  "Contains functions for the creation of datasets."
  (:import
    [org.jfree.data DefaultKeyedValue DefaultKeyedValues]
    [org.jfree.data.general DefaultPieDataset DefaultValueDataset]
    [org.jfree.data.category DefaultCategoryDataset SlidingCategoryDataset]
    [org.jfree.data.gantt Task TaskSeries TaskSeriesCollection]
    [org.jfree.data.statistics
     DefaultBoxAndWhiskerCategoryDataset DefaultBoxAndWhiskerXYDataset
     DefaultMultiValueCategoryDataset DefaultStatisticalCategoryDataset]
    [org.jfree.data.xy XYSeries XYSeriesCollection]))

;;
;; Datasets
;;

(defn keyed-value
  "Creates a key value object and adds the given 'key' and 'value'."
  [key value]
  (DefaultKeyedValue. key value))

(defn keyed-values
  "Creates a keyed values object and adds the given 'items'."
  ([items]
   (let [d (DefaultKeyedValues.)]
     (if-not (nil? items)
       (doseq [item items]
         (if (vector? item)
           (let [[key value] item]
             (.addValue d key value)))))
     d)))

(defn value-dataset
  "Creates a value dataset and adds the 'value', if given."
  ([]
   (DefaultValueDataset.))
  ([value]
   (DefaultValueDataset. value)))

(defn pie-dataset
  "Creates a pie dataset and adds the 'items', if given."
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

(defn multi-value-category-dataset
  ([]
   (DefaultMultiValueCategoryDataset.)))

(defn category-dataset
  "Creates a category dataset and adds the 'items', if given."
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
  "Creates a statistical category dataset and adds the 'items',
  if given."
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

(defn sliding-category-dataset
  "Creates a sliding category dataset from the dataset 'ds'
   and the 'first-column' and 'max-columns' parameters."
  [ds first-column max-columns]
  (SlidingCategoryDataset. ds first-column max-columns))

(defn task
  "Creates a task with the given 'description'.
  The time period can be given as 'duration' or as 'start' and 'end' dates."
  ([description duration]
    (Task. description duration))
  ([description start end]
    (Task. description start end)))

(defn task-series
  "Creates a task series with the given 'id' and adds the 'items' to it."
  [id items]
  (let [ts (TaskSeries. id)]
    (if-not (nil? items)
      (doseq [item items]
        (.add ts item)))
    ts))

(defn task-series-collection
  "Creates a task series collection and adds the task series 'items', if given."
  ([]
   (TaskSeriesCollection.))
  ([items]
   (let [task-series-coll (TaskSeriesCollection.)]
     (if-not (nil? items)
       (doseq [item items]
         (.add task-series item)))
     task-series)))

(defn xy-series
  "Creates a task series with the given 'id' and adds the 'items' to it."
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
  "Creates a xy series collection and adds the task series 'items', if given."
  ([]
   (XYSeriesCollection.))
  ([items]
   (let [xy-coll (XYSeriesCollection.)]
     (if-not (nil? items)
       (doseq [item items]
         (.addSeries xy-coll item)))
     xy-coll)))
