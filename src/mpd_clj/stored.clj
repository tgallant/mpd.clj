(ns mpd-clj.stored
  (:require [clojure.string :as str]
            [mpd-clj.utils :refer [send-cmd]]))

;; stored playlists
;; http://www.musicpd.org/doc/protocol/playlist_files.html

(defn list-playlist
  "lists songs in the playlist"
  [name]
  (send-cmd (str/join " " ["listplaylist" name])))

(defn list-playlist-info
  "lists songs in the playlist. with metadata"
  [name]
  (send-cmd (str/join " " ["listplaylistinfo" name])))

(defn list-playlists
  "show saved playlists"
  []
  (send-cmd "listplaylists"))

(defn load-playlist
  "loads the playlist into the current queue. RANGE is optional"
  ([name] (send-cmd (str/join " " ["load" name])))
  ([name range] (send-cmd (str/join " " ["load" name range]))))

(defn playlist-add
  "adds URI to the playlist NAME.m3u. NAME.m3u will be created if does not exist"
  [name uri]
  (send-cmd (str/join " " ["playlistadd" name uri])))

(defn playlist-clear
  "clears the playlist NAME.m3u"
  [name]
  (send-cmd (str/join " " ["playlistclear" name])))

(defn playlist-delete
  "deletes SONGPOS from the playlist NAME.m3u"
  [name songpos]
  (send-cmd (str/join " " ["playlistdelete" name songpos])))

(defn playlist-move
  "moves SONGID in the playlist NAME.m3u to SONGPOS"
  [name songid songpos]
  (send-cmd (str/join " " ["playlistmove" name songid songpos])))

(defn playlist-rename
  "renames the playlist NAME.m3u to NEWNAME.m3u"
  [name newname]
  (send-cmd (str/join " " ["rename" name newname])))

(defn playlist-rm
  "removes the playlist NAME.m3u from the playlist directory"
  [name]
  (send-cmd (str/join " " ["rm" name])))

(defn playlist-save
  "saves the current playlist to NAME.m3u in the playlist directory"
  [name]
  (send-cmd (str/join " " ["save" name])))
