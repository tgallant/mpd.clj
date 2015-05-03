(ns mpd-clj.db
  "the music database http://www.musicpd.org/doc/protocol/database.html"
  (:require [clojure.string :as str]
            [mpd-clj.utils :refer [send-cmd]]))

(defn list-albums
  "List all albums"
  ([mpd-server] (send-cmd "list album" mpd-server))
  ([artist mpd-server]
     (let [esc-artist (str/join "" ["\"" artist "\""])]
       (send-cmd (str/join " " ["list album artist" esc-artist]) mpd-server))))

(defn list-artists
  "List all artists"
  [mpd-server]
  (send-cmd "list artist" mpd-server))

(defn list-tracks
  "List all tracks in an album"
  [album mpd-server]
  (let [esc-album (str/join "" ["\"" album "\""])]
    (send-cmd (str/join " " ["list title album" esc-album]) mpd-server)))

(defn list-track-files
  "List all track files in an album (useful for adding to playlists)"
  [album mpd-server]
  (let [esc-album (str/join "" ["\"" album "\""])]
    (send-cmd (str/join " " ["list file album" esc-album]) mpd-server)))
