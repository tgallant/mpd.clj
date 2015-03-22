(ns mpd-clj.status
  "querying mpd's status http://www.musicpd.org/doc/protocol/command_reference.html"
  (:require [clojure.string :as str]
            [mpd-clj.utils :refer [send-cmd]]))

(defn clearerror
  "clears the current error message"
  [mpd-server]
  (send-cmd "clearerror" mpd-server))

(defn currentsong
  "get current song info"
  [mpd-server]
  (send-cmd "currentsong" mpd-server))

(defn set-idle
  "returns noteworthy change in subsystems"
  [subsystems mpd-server]
  (send-cmd (str/join " " [ "idle" subsystems]) mpd-server))

(defn status
  "get server status"
  [mpd-server]
  (send-cmd "status" mpd-server))

(defn stats
  "get server stats"
  [mpd-server]
  (send-cmd "status" mpd-server))
