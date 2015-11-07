;
;   Copyright (c) Ludger Solbach. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file license.txt at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.
;
(ns org.soulspace.clj.net.ftp.ftp-reply
  (:import [org.apache.commons.net.ftp FTPReply])
  (:use [clojure.set :only [map-invert]]))

; TODO convert keywords to lower case
(def reply-code 
  "Map of reply code keywords to reply codes."
  {
   :restart-marker                                  FTPReply/RESTART_MARKER
   :service-not-ready                               FTPReply/SERVICE_NOT_READY
   :data-connection-already-open                    FTPReply/DATA_CONNECTION_ALREADY_OPEN
   :file-status-ok                                  FTPReply/FILE_STATUS_OK
   :command-ok                                      FTPReply/COMMAND_OK
   :command-is-superfluous                          FTPReply/COMMAND_IS_SUPERFLUOUS
   :system-status                                   FTPReply/SYSTEM_STATUS
   :directory-status                                FTPReply/DIRECTORY_STATUS
   :file-status                                     FTPReply/FILE_STATUS
   :help-message                                    FTPReply/HELP_MESSAGE
   :name-system-type                                FTPReply/NAME_SYSTEM_TYPE
   :service-ready                                   FTPReply/SERVICE_READY
   :service-closing-control-connection              FTPReply/SERVICE_CLOSING_CONTROL_CONNECTION
   :data-connection-open                            FTPReply/DATA_CONNECTION_OPEN
   :closing-data-connection                         FTPReply/CLOSING_DATA_CONNECTION
   :entering-passive-mode                           FTPReply/ENTERING_PASSIVE_MODE
   :entering-epsv-mode                              FTPReply/ENTERING_EPSV_MODE
   :user-logged-in                                  FTPReply/USER_LOGGED_IN
   :file-action-ok                                  FTPReply/FILE_ACTION_OK
   :pathname-created                                FTPReply/PATHNAME_CREATED
   :need-password                                   FTPReply/NEED_PASSWORD
   :need-account                                    FTPReply/NEED_ACCOUNT
   :file-action-pending                             FTPReply/FILE_ACTION_PENDING
   :service-not-available                           FTPReply/SERVICE_NOT_AVAILABLE
   :cannot-open-data-connection                     FTPReply/CANNOT_OPEN_DATA_CONNECTION
   :transfer-aborted                                FTPReply/TRANSFER_ABORTED
   :file-action-not-taken                           FTPReply/FILE_ACTION_NOT_TAKEN
   :action-aborted                                  FTPReply/ACTION_ABORTED
   :insufficient-storage                            FTPReply/INSUFFICIENT_STORAGE
   :unrecognized-command                            FTPReply/UNRECOGNIZED_COMMAND
   :syntax-error-in-arguments                       FTPReply/SYNTAX_ERROR_IN_ARGUMENTS
   :command-not-implemented                         FTPReply/COMMAND_NOT_IMPLEMENTED
   :bad-command-sequence                            FTPReply/BAD_COMMAND_SEQUENCE
   :command-not-implemented-for-parameter           FTPReply/COMMAND_NOT_IMPLEMENTED_FOR_PARAMETER
   :not-logged-in                                   FTPReply/NOT_LOGGED_IN
   :need-account-for-storing-files                  FTPReply/NEED_ACCOUNT_FOR_STORING_FILES
   :file-unavailable                                FTPReply/FILE_UNAVAILABLE
   :page-type-unknown                               FTPReply/PAGE_TYPE_UNKNOWN
   :storage-allocation-exceeded                     FTPReply/STORAGE_ALLOCATION_EXCEEDED
   :file-name-not-allowed                           FTPReply/FILE_NAME_NOT_ALLOWED
   :security-data-exchange-complete                 FTPReply/SECURITY_DATA_EXCHANGE_COMPLETE
   :security-data-exchange-successfully             FTPReply/SECURITY_DATA_EXCHANGE_SUCCESSFULLY
   :security-mechanism-is-ok                        FTPReply/SECURITY_MECHANISM_IS_OK
   :security-data-is-acceptable                     FTPReply/SECURITY_DATA_IS_ACCEPTABLE
   :unavailable-resource                            FTPReply/UNAVAILABLE_RESOURCE
   :bad-tls-negotiation-or-data-encryption-required FTPReply/BAD_TLS_NEGOTIATION_OR_DATA_ENCRYPTION_REQUIRED
   :denied-for-policy-reasons                       FTPReply/DENIED_FOR_POLICY_REASONS
   :request-denied                                  FTPReply/REQUEST_DENIED
   :failed-security-check                           FTPReply/FAILED_SECURITY_CHECK
   :requested-prot-level-not-supported              FTPReply/REQUESTED_PROT_LEVEL_NOT_SUPPORTED
   :extended-port-failure                           FTPReply/EXTENDED_PORT_FAILURE
   })

(def reply-key
  "Map of reply codes to reply code keywords."
  (map-invert reply-code))


(defn positive-completion?
  "Determine if a reply code is a positive completion response."
  [reply]
  (FTPReply/isPositiveCompletion reply))

(defn positive-intermediate?
  "Determine if a reply code is a positive intermediate response."
  [reply]
  (FTPReply/isPositiveIntermediate reply))

(defn positive-preliminary?
  "Determine if a reply code is a positive preliminary response."
  [reply]
  (FTPReply/isPositivePreliminary reply))

(defn negative-permanent?
  "Determine if a reply code is a negative permanent response."
  [reply]
  (FTPReply/isNegativePermanent reply))

(defn negative-transient?
  "Determine if a reply code is a negative transient response."
  [reply]
  (FTPReply/isNegativeTransient reply))

(defn protected-reply-code?
  "Determine if a reply code is a protected response."
  [reply]
  (FTPReply/isProtectedReplyCode reply))
