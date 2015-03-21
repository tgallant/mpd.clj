(ns mpd-clj.stored
  "stored playlists http://www.musicpd.org/doc/protocol/playlist_files.html"
  (:require [clojure.string :as str]
            [mpd-clj.utils :refer [send-cmd]]))

(defn list-playlist
  "lists songs in the playlist"
  [name mpd-server]
  (send-cmd (str/join " " ["listplaylist" name]) mpd-server))

(defn list-playlist-info
  "lists songs in the playlist. with metadata"
  [name mpd-server]
  (send-cmd (str/join " " ["listplaylistinfo" name]) mpd-server))

(defn list-playlists
  "show saved playlists"
  [mpd-server]
  (send-cmd "listplaylists" mpd-server))

(defn load-playlist
  "loads the playlist into the current queue. RANGE is optional"
  ([name mpd-server] (send-cmd (str/join " " ["load" name]) mpd-server))
  ([name range mpd-server] (send-cmd (str/join " " ["load" name range]) mpd-server)))

(defn playlist-add
  "adds URI to the playlist NAME.m3u. NAME.m3u will be created if does not exist"
  [name uri mpd-server]
  (send-cmd (str/join " " ["playlistadd" name uri]) mpd-server))

(defn playlist-clear
  "clears the playlist NAME.m3u"
  [name mpd-server]
  (send-cmd (str/join " " ["playlistclear" name]) mpd-server))

(defn playlist-delete
  "deletes SONGPOS from the playlist NAME.m3u"
  [name songpos mpd-server]
  (send-cmd (str/join " " ["playlistdelete" name songpos]) mpd-server))

(defn playlist-move
  "moves SONGID in the playlist NAME.m3u to SONGPOS"
  [name songid songpos mpd-server]
  (send-cmd (str/join " " ["playlistmove" name songid songpos]) mpd-server))

(defn playlist-rename
  "renames the playlist NAME.m3u to NEWNAME.m3u"
  [name newname mpd-server]
  (send-cmd (str/join " " ["rename" name newname]) mpd-server))

(defn playlist-rm
  "removes the playlist NAME.m3u from the playlist directory"
  [name mpd-server]
  (send-cmd (str/join " " ["rm" name]) mpd-server))

(defn playlist-save
  "saves the current playlist to NAME.m3u in the playlist directory"
  [name mpd-server]
  (send-cmd (str/join " " ["save" name]) mpd-server))
