;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.clj.net.ftp.ftp-client
  (:import [java.io PrintWriter]
           [org.apache.commons.net PrintCommandListener]
           [org.apache.commons.net.ftp FTP FTPClient FTPClientConfig FTPReply])
  (:use [clojure.java.io :only [input-stream]]
        [org.soulspace.clj.java beans]
        [org.soulspace.clj.net command-listener]
        [org.soulspace.clj.net.ftp ftp-file ftp-reply]))

; used to bind the ftp client thread locally in with-ftp-connection
(def
  ^{:dynamic true :doc "The client to use for operations within with-ftp-connection."}
  *ftp-client*)

(def file-type
  {:ascii  FTP/ASCII_FILE_TYPE
   :binary FTP/BINARY_FILE_TYPE
   :local  FTP/LOCAL_FILE_TYPE
   :ebcdic FTP/EBCDIC_FILE_TYPE})

(def transfer-mode
  {:block      FTP/BLOCK_TRANSFER_MODE
   :compressed FTP/COMPRESSED_TRANSFER_MODE
   :stream     FTP/STREAM_TRANSFER_MODE})

(defn ftp-client-config
  "Create an ftp client configuration."
  ([]
   (FTPClientConfig.))
  ([opts]
   (let [c (FTPClientConfig.)]
     (set-properties! c opts)
     c)))

; "Configure the FTP client with the given configuration."
(defmulti configure type)

(defmethod configure FTPClientConfig
  [cfg]
  (configure *ftp-client* cfg))

(defmethod configure clojure.lang.IPersistentMap
  [cfg]
  (configure *ftp-client* (ftp-client-config cfg)))

(defn ftp-client
  "Creates an ftp client. Sets opts as properties of the client, apart from an optional map under :configuration,
   which is used to create an FTPClientConfiguration."
  ([]
   (FTPClient.))
  ([opts]
   (let [c (FTPClient.)]
     (set-properties! (dissoc opts :configuration))
     (when-let [cfg (:configuration opts)]
       (.configure c (ftp-client-config cfg)))
     c)))

(defn connected?
  "Check wether the ftp client is connected."
  ([]
   (connected? *ftp-client*))
  ([ftp]
   (.isConnected ftp)))

(defn get-reply-code
  "Returns the reply code of the last command."
  ([]
   (get-reply-code *ftp-client*))
  ([ftp]
   (.getReplyCode ftp)))

(defn success?
  "Check wether the ftp command was successful."
  ([]
   (success? *ftp-client*))
  ([ftp]
   (positive-completion? (get-reply-code ftp))))

(defn intermediate-success?
  ""
  ([]
   (intermediate-success? *ftp-client*))
  ([ftp]
   (positive-intermediate? (get-reply-code ftp))))

(defn login
  "Log into the ftp server."
  ([user pw]
   (login *ftp-client* user pw))
  ([ftp user pw]
   (.login ftp user pw)))

(defn logout
  "Log out of the ftp server."
  ([]
   (logout *ftp-client*))
  ([ftp]
   (.logout ftp)))

(defn disconnect
  "Disconnect from the ftp server."
  [ftp]
  (when (connected? ftp)
    (logout ftp)
    (.disconnect ftp)))

(defn connect
  "Connect to the ftp server."
  ([ftp host]
   (connect ftp host 21))
  ([ftp host port]
   (.connect ftp host port)
   (let [success (success? ftp)]
     (if-not success
       (disconnect ftp))
     success)))

(defn status
  "Issues an FTP STAT command."
  ([]
   (.getStatus *ftp-client*))
  ([pathname]
   (.getStatus *ftp-client* pathname)))

(defn abort
  "Aborts a transfer in progress."
  []
  (.abort *ftp-client*))

(defn complete-pending-command
  "Aborts a transfer in progress."
  []
  (.completePendingCommand *ftp-client*))

(defn make-directory
  "Make a directory on the ftp server."
  [pathname]
  (.makeDirectory *ftp-client* pathname))

(defn print-working-directory
  "Print the working directory on the ftp server."
  []
  (.printWorkingDirectory *ftp-client*))

(defn change-working-directory
  "Change the working directory on the ftp server."
  [pathname]
  (.changeWorkingDirectory *ftp-client* pathname))

(defn change-to-parent-directory
  "Change to parent directory on the ftp server."
  [pathname]
  (.changeToParentDirectory *ftp-client*))

(defn list-directories
  "List the directories on the ftp server."
  ([]
   (.listDirectories *ftp-client*))
  ([dir]
   (.listDirectories *ftp-client* dir)))

(defn list-files
  "List the files on the ftp server."
  ([]
   (.listFiles *ftp-client*))
  ([dir]
   (.listFiles *ftp-client* dir)))

(defn append-file
  "Append the input stream a file on the ftp server."
  [pathname input-stream]
  (.appendFile *ftp-client* pathname input-stream))

(defn delete-file
  "Delete a file on the ftp server."
  [pathname]
  (.deleteFile *ftp-client* pathname))

(defn rename
  "Rename a file on the ftp server."
  [from to]
  (.rename *ftp-client* from to))

(defn retrieve-file
  "Retrieves a file from the ftp server. Uses the output stream to write the file."
  [pathname output-stream]
  (.retrieveFile *ftp-client* pathname output-stream))

(defn retrieve-file-stream
  "Retrieves a file as an output stream from the ftp server."
  [pathname]
  (.retrieveFileStream *ftp-client* pathname))

(defn store-file
  "Stores a file on the ftp server."
  [pathname input-stream]
  (.storeFile *ftp-client* pathname input-stream))

(defn store-file-by-name
  "Stores a file with the given path name under the same path name ont the ftp server."
  [filename]
  (store-file filename (input-stream filename)))

(defn store-unique-file
  "Store a file with a unique name on the ftp server."
  ([input-stream]
   (.storeUniqueFile *ftp-client* input-stream))
  ([pathname input-stream]
   (.storeUniqueFile *ftp-client* pathname input-stream)))

(defn store-file-stream
  "Store a file stream on the ftp server."
  [pathname]
  (.storeFileStream *ftp-client* pathname))

(defn store-unique-file-stream
  "Store a file stream with a unique name on the ftp server."
  ([]
   (.storeUniqueFileStream *ftp-client*))
  ([pathname]
   (.storeUniqueFileStream *ftp-client* pathname)))

(defn enter-local-active-mode
  []
  (.enterLocalActiveMode *ftp-client*))

(defn enter-local-passive-mode
  []
  (.enterLocalPassiveMode *ftp-client*))

(defn enter-remote-passive-mode
  []
  (.enterRemotePassiveMode *ftp-client*))

; TODO map to :keywords
(defn get-data-connection-mode
  []
  (.getDataConnectionMode *ftp-client*))

(defn send-noop
  ""
  []
  (.sendNoOp *ftp-client*))

(defn set-control-keep-alive-timeout
  "Sets the time to wait between sending control connection keepalive messages when processing file upload or download."
  [value]
  (.setControlKeepAliveTimeout *ftp-client* value))

(defn set-control-keep-alive-reply-timeout
  "Sets how long to wait for control keep-alive message replies."
  [value]
  (.setControlKeepAliveReplyTimeout *ftp-client* value))

(defn set-data-timeout
  "Sets the timeout in milliseconds to use when reading from the data connection."
  [value]
  (.setDataTimeout *ftp-client* value))

(defn set-file-type
  "Sets the file type to be transferred (:ascii, :binary, :local, :ebcdic)."
  [value]
  (.setFileType *ftp-client* (file-type value)))

(defn set-transfer-mode
  "Sets the transfer mode (:block, :compressed, :stream)."
  [value]
  (.setFileTransferMode *ftp-client* (transfer-mode value)))

(defn add-protocol-command-listener
  "Add a protocol command listener."
  [listener]
  (.addProtocolCommandListener *ftp-client* listener))

(defmacro with-ftp-connection
  "Executes body with a ftp connection created based on opts. Opts can include the following keys [default]:

     :host      the host to connect to [localhost]
     :port      the port to use [21]
     :user      the login user [anonymous]
     :password  the password of the login user []
     :localMode the local mode (:active/:passive) [:passive]

   Any keys not mentioned here are passed through as properties to the FTPClient (see ftp-client)."
  [opts & body]
  `(let [opts# ~opts
         host# (get opts# :host "localhost")
         port# (get opts# :port 21)
         user# (get opts# :user "anonymous")
         password# (get opts# :password "")
         local-mode# (keyword (get opts# :local-mode :passive))
         file-type# (keyword (get opts# :file-type :ascii))
         opts# (dissoc opts# :port :host :user :password :local-mode :file-type)]
     (binding [*ftp-client* (ftp-client opts#)]
       (when-let [success# (connect *ftp-client* host# port#)]
         (login *ftp-client* user# password#)
         (if (= local-mode# :active)
           (enter-local-active-mode)
           (enter-local-passive-mode))
         ~@body
         (disconnect *ftp-client*)))))
