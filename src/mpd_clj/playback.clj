(ns mpd-clj.playback
  "controlling playback http://www.musicpd.org/doc/protocol/playback_commands.html"
  (:require [clojure.string :as str]
            [mpd-clj.utils :refer [send-cmd]]))

(defn next-song
  "play next song in playlist"
  []
  (send-cmd "next"))

(defn pause
  "pause playback"
  []
  (send-cmd "pause 1"))

(defn resume
  "resume playback"
  []
  (send-cmd "pause 0"))

(defn play-song-pos
  "start playlist at song number"
  [songpos]
  (send-cmd (str/join " " ["play" songpos])))

(defn play-song-id
  "start playlist at song id"
  [songid]
  (send-cmd (str/join " " ["playid" songid])))

(defn previous-song
  "play previous song in playlist"
  []
  (send-cmd "previous"))

(defn seek
  "seeks to the position TIME(in seconds) of song at SONGPOS in the playlist"
  [time songpos]
  (send-cmd (str/join " " ["seek" time songpos])))

(defn seekid
  "seeks to the position TIME(in seconds) of song at SONGID in the playlist"
  [time songid]
  (send-cmd (str/join " " ["seek" time songid])))

(defn seekcur
  "seeks to the position TIME(in seconds) of current song"
  [time]
  (send-cmd (str/join " " ["seek" time])))

(defn stop
  "stop playback"
  []
  (send-cmd "stop"))
