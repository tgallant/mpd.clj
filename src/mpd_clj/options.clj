(ns mpd-clj.options
  "playback options  http://www.musicpd.org/doc/protocol/playback_option_commands.html"
  (:require [clojure.string :as str]
            [mpd-clj.utils :refer [send-cmd]]))

(defn set-consume
  "consume playlist"
  [mpd-server]
  (send-cmd "consume 1" mpd-server))

(defn unset-consume
  "un-consume playlist"
  [mpd-server]
  (send-cmd "consume 0" mpd-server))

(defn set-crossfade
  "set crossfading between songs (in seconds)"
  [s mpd-server]
  (send-cmd (str/join " " ["crossfade" s]) mpd-server))

(defn set-mixrampdb
  "sets the threshold at which songs will be overlapped. (in decibels)"
  [dbs mpd-server]
  (send-cmd (str/join " "  ["mixrampdb" dbs]) mpd-server))

(defn set-mixrampdelay
  "additional time subtracted from the overlap calculated by mixrampdb. (in seconds)"
  [s mpd-server]
  (send-cmd (str/join " " ["mixrampdelay" s]) mpd-server))

(defn set-random
  "set random state"
  [mpd-server]
  (send-cmd "random 1" mpd-server))

(defn unset-random
  "unset random state"
  [mpd-server]
  (send-cmd "random 0" mpd-server))

(defn set-repeat
  "set repeat state"
  [mpd-server]
  (send-cmd "repeat 1" mpd-server))

(defn unset-repeat
  "unset repeat state"
  [mpd-server]
  (send-cmd "repeat 0" mpd-server))

(defn set-volume
  "set volume (integer between 0 and 100)"
  [vol mpd-server]
  (send-cmd (str/join " " ["setvol" vol]) mpd-server))

(defn set-single
  "set single state"
  [mpd-server]
  (send-cmd "single 1" mpd-server))

(defn unset-single
  "unset single state"
  [mpd-server]
  (send-cmd "single 0" mpd-server))

(defn set-replay-gain-mode
  "set the replay gain mode. one of [off track album auto]"
  [mode mpd-server]
  (send-cmd (str/join " " ["replay_gain_mode" mode]) mpd-server))

(defn replay-gain-status
  "prints replay gain mode"
  [mpd-server]
  (send-cmd "replay_gain_status" mpd-server))
