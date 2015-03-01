(ns mpd-clj.status
  (:require [clojure.string :as str]
            [mpd-clj.utils :refer [send-cmd]]))

;; querying mpd's status
;; http://www.musicpd.org/doc/protocol/command_reference.html

(defn currentsong
  "clears the current error message"
  []
  (send-cmd "clearerror"))

(defn currentsong
  "get current song info"
  []
  (send-cmd "currentsong"))

(defn set-idle
  "returns noteworthy change in subsystems"
  [subsystems]
  (send-cmd (str/join " " [ "idle" subsystems])))

(defn status
  "get server status"
  []
  (send-cmd "status"))

(defn stats
  "get server stats"
  []
  (send-cmd "status"))
