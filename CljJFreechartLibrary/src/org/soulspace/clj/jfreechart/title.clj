;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.clj.jfreechart.title
  (:import [org.jfree.chart.title CompositeTitle DateTitle ImageTitle LegendTitle ShortTextTitle TextTitle])
  (:use [org.soulspace.clj.java beans]))

(defn composite-title
  "Creates a composite title."
  [props]
  (set-properties! (CompositeTitle.) props))

(defn date-title
  "Creates a date title."
  [props]
  (set-properties! (DateTitle.) props))

(defn image-title
  "Creates an image title."
  [image props]
  (set-properties! (ImageTitle. image) props))

(defn short-text-title
  "Creates a short text title."
  [text props]
  (set-properties! (ShortTextTitle. text) props))

(defn text-title
  "Creates a text title."
  [props]
  (set-properties! (TextTitle.) props))
