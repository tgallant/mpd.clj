(ns mpd-clj.controlls
  "the current playlist http://www.musicpd.org/doc/protocol/queue.html"
  (:require [clojure.string :as str]
            [mpd-clj.utils :refer [send-cmd]]))

(defn add-song
  "add the file URI to the current playlist. (URI can be directory or single file)"
  [uri]
  (send-cmd (str/join " " ["addid" uri])))

(defn clear-playlist
  "clear current playlist"
  []
  (send-cmd "clear"))

(defn delete-from-playlist
  "delete from playlist. (can be single id or range. range format is 'start:end')"
  [pos]
  (send-cmd (str/join " " ["delete" pos])))

(defn delete-id-from-playlist
  "delete from playlist by song id"
  [songid]
  (send-cmd (str/join " " ["delete" songid])))

(defn move
  "moves the song FROM to TO in the playlist. (FROM can be single position or a range)"
  [from-pos to-pos]
  (send-cmd (str/join " " ["move" from-pos to-pos])))

(defn move-id
  "moves the song FROM to TO in the playlist. (FROM can be any single songid. TO is playlist position)"
  [from-id to-pos]
  (send-cmd (str/join " " ["move" from-id to-pos])))

(defn playlist
  "show current playlist"
  []
  (send-cmd "playlist"))

(defn playlist-find
  "find songs in the current playlist with strict matching"
  [tag needle]
  (send-cmd (str/join " " ["playlistfind" tag needle])))

(defn playlist-id
  "displays a list of songs in the playlist. ID is optional"
  ([] (send-cmd "playlistid"))
  ([id] (send-cmd (str/join " " ["playlistid" id]))))

(defn playlist-info
  "display a list of all songs in the playlist, or for a range or single song"
  ([] (send-cmd "playlistinfo"))
  ([songpos] (send-cmd (str/join " " ["playlistinfo" songpos]))))

(defn playlist-search
  "searches case-insensitively for partial matches in the current playlist"
  [tag needle]
  (send-cmd (str/join " " ["playlistsearch" tag needle])))

(defn plchanges
  "display changed songs currently in the playlist since VERSION"
  [version]
  (send-cmd (str/join " " ["plchanges" version])))

(defn plchanges-posid
  "display changed songs currently in the playlist since VERSION. only returns IDs."
  [version]
  (send-cmd (str/join " " ["plchangesposid" version])))

(defn set-priority
  "set the priority of the specified songs. where SONGPOS is a range."
  [priority songpos]
  (send-cmd (str/join " " ["prio" priority songpos])))

(defn set-priority-id
  "set the priority of the specified song."
  [priority songid]
  (send-cmd (str/join " " ["prio" priority songid])))

(defn range-id
  "specifies the portion of the song that shall be played"
  [id range]
  (send-cmd (str/join " " ["rangeid" id range])))

(defn shuffle-playlist
  "shuffles the current playlist. RANGE is optional."
  ([] (send-cmd "shuffle"))
  ([range] (send-cmd (str/join " " ["shuffle" range]))))

(defn swap-songs
  "swap the positions of SONG1 and SONG2"
  [song1 song2]
  (send-cmd (str/join " " ["swap" song1 song2])))

(defn swap-songs-id
  "swap the positions of SONG1 and SONG2"
  [song1 song2]
  (send-cmd (str/join " " ["swapid" song1 song2])))

(defn add-tag-id
  "adds a tag to the specified song"
  [songid tag value]
  (send-cmd (str/join " " ["addtagid" songid tag value])))

(defn clear-tag-id
  "removes tag from specified song. if TAG is not specified, all tag values will be removed"
  ([songid] (send-cmd (str/join " " ["cleartagid" songid])))
  ([songid tag] (send-cmd (str/join " " ["cleartagid" songid tag]))))
