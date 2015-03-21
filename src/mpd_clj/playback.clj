(ns mpd-clj.playback
  "controlling playback http://www.musicpd.org/doc/protocol/playback_commands.html"
  (:require [clojure.string :as str]
            [mpd-clj.utils :refer [send-cmd]]))

(defn next-song
  "play next song in playlist"
  [mpd-server]
  (send-cmd "next" mpd-server))

(defn pause
  "pause playback"
  [mpd-server]
  (send-cmd "pause 1" mpd-server))

(defn resume
  "resume playback"
  [mpd-server]
  (send-cmd "pause 0" mpd-server))

(defn play-song-pos
  "start playlist at song number"
  [songpos mpd-server]
  (send-cmd (str/join " " ["play" songpos]) mpd-server))

(defn play-song-id
  "start playlist at song id"
  [songid mpd-server]
  (send-cmd (str/join " " ["playid" songid]) mpd-server))

(defn previous-song
  "play previous song in playlist"
  [mpd-server]
  (send-cmd "previous" mpd-server))

(defn seek
  "seeks to the position TIME(in seconds) of song at SONGPOS in the playlist"
  [time songpos mpd-server]
  (send-cmd (str/join " " ["seek" time songpos]) mpd-server))

(defn seekid
  "seeks to the position TIME(in seconds) of song at SONGID in the playlist"
  [time songid mpd-server]
  (send-cmd (str/join " " ["seek" time songid]) mpd-server))

(defn seekcur
  "seeks to the position TIME(in seconds) of current song"
  [time mpd-server]
  (send-cmd (str/join " " ["seek" time]) mpd-server))

(defn stop
  "stop playback"
  [mpd-server]
  (send-cmd "stop" mpd-server))
