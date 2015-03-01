(ns mpd-clj.options
  (:require [clojure.string :as str]
            [mpd-clj.utils :refer [send-cmd]]))

;; playback options
;; http://www.musicpd.org/doc/protocol/playback_option_commands.html

(defn set-consume
  "consume playlist"
  []
  (send-cmd "consume 1"))

(defn unset-consume
  "un-consume playlist"
  []
  (send-cmd "consume 0"))

(defn set-crossfade
  "set crossfading between songs (in seconds)"
  [s]
  (send-cmd (str/join " " ["crossfade" s])))

(defn set-mixrampdb
  "sets the threshold at which songs will be overlapped. (in decibels)"
  [dbs]
  (send-cmd (str/join " "  ["mixrampdb" dbs])))

(defn set-mixrampdelay
  "additional time subtracted from the overlap calculated by mixrampdb. (in seconds)"
  [s]
  (send-cmd (str/join " " ["mixrampdelay" s])))

(defn set-random
  "set random state"
  []
  (send-cmd "random 1"))

(defn unset-random
  "unset random state"
  []
  (send-cmd "random 0"))

(defn set-repeat
  "set repeat state"
  []
  (send-cmd "repeat 1"))

(defn unset-repeat
  "unset repeat state"
  []
  (send-cmd "repeat 0"))

(defn set-volume
  "set volume (integer between 0 and 100)"
  [vol]
  (send-cmd (str/join " " ["setvol" vol])))

(defn set-single
  "set single state"
  []
  (send-cmd "single 1"))

(defn unset-single
  "unset single state"
  []
  (send-cmd "single 0"))

(defn set-replay-gain-mode
  "set the replay gain mode. one of [off track album auto]"
  [mode]
  (send-cmd (str/join " " ["replay_gain_mode" mode])))

(defn replay-gain-status
  "prints replay gain mode"
  []
  (send-cmd "replay_gain_status"))
