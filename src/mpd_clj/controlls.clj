(ns mpd-clj.controlls
  "the current playlist http://www.musicpd.org/doc/protocol/queue.html"
  (:require [clojure.string :as str]
            [mpd-clj.utils :refer [send-cmd]]))

(defn add-song
  "add the file URI to the current playlist. (URI can be directory or single file)"
  [uri mpd-server]
  (let [esc-uri (str/join "" ["\"" uri "\""])]
    (send-cmd (str/join " " ["add" esc-uri]) mpd-server)))

(defn clear-playlist
  "clear current playlist"
  [mpd-server]
  (send-cmd "clear" mpd-server))

(defn delete-from-playlist
  "delete from playlist. (can be single id or range. range format is 'start:end')"
  [pos mpd-server]
  (send-cmd (str/join " " ["delete" pos]) mpd-server))

(defn delete-id-from-playlist
  "delete from playlist by song id"
  [songid mpd-server]
  (send-cmd (str/join " " ["delete" songid]) mpd-server))

(defn move
  "moves the song FROM to TO in the playlist. (FROM can be single position or a range)"
  [from-pos to-pos mpd-server]
  (send-cmd (str/join " " ["move" from-pos to-pos]) mpd-server))

(defn move-id
  "moves the song FROM to TO in the playlist. (FROM can be any single songid. TO is playlist position)"
  [from-id to-pos mpd-server]
  (send-cmd (str/join " " ["move" from-id to-pos]) mpd-server))

(defn playlist
  "show current playlist"
  [mpd-server]
  (send-cmd "playlist" mpd-server))

(defn playlist-find
  "find songs in the current playlist with strict matching"
  [tag needle mpd-server]
  (send-cmd (str/join " " ["playlistfind" tag needle]) mpd-server))

(defn playlist-id
  "displays a list of songs in the playlist. ID is optional"
  ([mpd-server] (send-cmd "playlistid" mpd-server))
  ([id mpd-server] (send-cmd (str/join " " ["playlistid" id]) mpd-server)))

(defn playlist-info
  "display a list of all songs in the playlist, or for a range or single song"
  ([mpd-server] (send-cmd "playlistinfo" mpd-server))
  ([songpos mpd-server] (send-cmd (str/join " " ["playlistinfo" songpos]) mpd-server)))

(defn playlist-search
  "searches case-insensitively for partial matches in the current playlist"
  [tag needle mpd-server]
  (send-cmd (str/join " " ["playlistsearch" tag needle]) mpd-server))

(defn plchanges
  "display changed songs currently in the playlist since VERSION"
  [version mpd-server]
  (send-cmd (str/join " " ["plchanges" version]) mpd-server))

(defn plchanges-posid
  "display changed songs currently in the playlist since VERSION. only returns IDs."
  [version mpd-server]
  (send-cmd (str/join " " ["plchangesposid" version]) mpd-server))

(defn set-priority
  "set the priority of the specified songs. where SONGPOS is a range."
  [priority songpos mpd-server]
  (send-cmd (str/join " " ["prio" priority songpos]) mpd-server))

(defn set-priority-id
  "set the priority of the specified song."
  [priority songid mpd-server]
  (send-cmd (str/join " " ["prio" priority songid]) mpd-server))

(defn range-id
  "specifies the portion of the song that shall be played"
  [id range mpd-server]
  (send-cmd (str/join " " ["rangeid" id range]) mpd-server))

(defn shuffle-playlist
  "shuffles the current playlist. RANGE is optional."
  ([mpd-server] (send-cmd "shuffle" mpd-server))
  ([range mpd-server] (send-cmd (str/join " " ["shuffle" range]) mpd-server)))

(defn swap-songs
  "swap the positions of SONG1 and SONG2"
  [song1 song2 mpd-server]
  (send-cmd (str/join " " ["swap" song1 song2]) mpd-server))

(defn swap-songs-id
  "swap the positions of SONG1 and SONG2"
  [song1 song2 mpd-server]
  (send-cmd (str/join " " ["swapid" song1 song2]) mpd-server))

(defn add-tag-id
  "adds a tag to the specified song"
  [songid tag value mpd-server]
  (send-cmd (str/join " " ["addtagid" songid tag value]) mpd-server))

(defn clear-tag-id
  "removes tag from specified song. if TAG is not specified, all tag values will be removed"
  ([songid mpd-server] (send-cmd (str/join " " ["cleartagid" songid]) mpd-server))
  ([songid tag mpd-server] (send-cmd (str/join " " ["cleartagid" songid tag]) mpd-server)))
