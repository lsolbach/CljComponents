;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.cmp.net.ftp.ftp-file
  (:import [org.apache.commons.net.ftp FTPFile]))

(def ftp-file-type
  {:directory     FTPFile/DIRECTORY_TYPE
   :file          FTPFile/FILE_TYPE
   :symbolic-link FTPFile/SYMBOLIC_LINK_TYPE
   :unknown       FTPFile/UNKNOWN_TYPE})

(def ftp-file-permission
  {:read    FTPFile/READ_PERMISSION
   :write   FTPFile/WRITE_PERMISSION
   :execute FTPFile/EXECUTE_PERMISSION})

(def ftp-file-access
  {:user  FTPFile/USER_ACCESS
   :group FTPFile/GROUP_ACCESS
   :world FTPFile/WORLD_ACCESS})

(defn directory?
  "Returns true, if the ftp file is a directory."
  [ftp-file]
  (.isDirectory ftp-file))

(defn file?
  "Returns true, if the ftp file is a file."
  [ftp-file]
  (.isFile ftp-file))

(defn symbolic-link?
  "Returns true, if the ftp file is a symbolic link."
  [ftp-file]
  (.isSymbolicLink ftp-file))

(defn unknown?
  "Returns true, if the ftp file type is unknown."
  [ftp-file]
  (.isUnknown ftp-file))

