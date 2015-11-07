;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.clj.net.command-listener
  (:use [org.soulspace.clj.java beans])
  (:import [java.io PrintWriter]
           [org.apache.commons.net PrintCommandListener]))

(defn print-command-listener
  ([]
    (PrintCommandListener. (PrintWriter. System/out) true))
  ([print-stream]
    (PrintCommandListener. (PrintWriter. print-stream) true))
  ([print-stream suppress-login]
    (PrintCommandListener. (PrintWriter. print-stream) suppress-login)))
