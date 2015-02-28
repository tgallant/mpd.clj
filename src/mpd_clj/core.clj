(ns mpd-clj.core
  (:require [clojure.string :as str]
            [manifold.deferred :as d]
            [manifold.stream :as s]
            [aleph.tcp :as tcp]
            [gloss.core :as gloss]
            [gloss.io :as io]))

;; set host and port here
(def mpd-server {:host "192.168.1.2" :port 6600})

;; protocol definition
(def protocol (gloss/string :utf-8 :delimiters ["\n"]))

(defn wrap-duplex-stream
  "wrap byte stream"
  [protocol s]
  (let [out (s/stream)]
    (s/connect
      (s/map #(io/encode protocol %) out)
      s)
    (s/splice
      out
      (io/decode-stream s protocol))))

(defn client
  "client wrapper"
  [host port]
  (d/chain (tcp/client {:host host, :port port})
    #(wrap-duplex-stream protocol %)))

(defn res-str
  "takes mpd protocol response, returns key value pair"
  [res]
  (let [res-arr (str/split res #": ")]
    {:key (first res-arr)
     :value (last res-arr)}))

(defn return-obj
  "takes response channel, returns clojure keyword map"
  [c]
  (defn obj-inner [mpd-obj]
    (let [res @(s/take! c)
          res-key (res-str res)]
      (cond
        (= "OK" res) (if (empty? mpd-obj) nil mpd-obj)
        (re-find #"OK MPD" res) (obj-inner mpd-obj)
        :else (obj-inner
                (assoc mpd-obj
                  (keyword (:key res-key))
                  (:value res-key))))))
  (obj-inner {}))

(defn currentsong
  "get current song info"
  []
  (let [c @(client (:host mpd-server) (:port mpd-server))]
    @(s/put! c "currentsong\n")
    (return-obj c)))

(defn status
  "get server status"
  []
  (let [c @(client (:host mpd-server) (:port mpd-server))]
    @(s/put! c "status\n")
    (return-obj c)))

(defn stats
  "get server stats"
  []
  (let [c @(client (:host mpd-server) (:port mpd-server))]
    @(s/put! c "stats\n")
    (return-obj c)))

(defn next-song
  "play next song in playlist"
  []
  (let [c @(client (:host mpd-server) (:port mpd-server))]
    @(s/put! c "next\n")
    (return-obj c)))

(defn previous-song
  "play previous song in playlist"
  []
  (let [c @(client (:host mpd-server) (:port mpd-server))]
    @(s/put! c "previous\n")
    (return-obj c)))

(defn stop
  "stop playback"
  []
  (let [c @(client (:host mpd-server) (:port mpd-server))]
    @(s/put! c "stop\n")
    (return-obj c)))

(defn pause
  "pause playback"
  []
  (let [c @(client (:host mpd-server) (:port mpd-server))]
    @(s/put! c "pause 1\n")
    (return-obj c)))

(defn resume
  "resume playback"
  []
  (let [c @(client (:host mpd-server) (:port mpd-server))]
    @(s/put! c "pause 0\n")
    (return-obj c)))

(defn clear-playlist
  "clear current playlist"
  []
  (let [c @(client (:host mpd-server) (:port mpd-server))]
    @(s/put! c "clear\n")
    (return-obj c)))

(defn playlist
  "show current playlist"
  []
  (let [c @(client (:host mpd-server) (:port mpd-server))]
    @(s/put! c "playlist\n")
    (return-obj c)))

(defn saved-playlists
  "show saved playlists"
  []
  (let [c @(client (:host mpd-server) (:port mpd-server))]
    @(s/put! c "listplaylists\n")
    (return-obj c)))

(defn set-consume
  "consume playlist"
  []
  (let [c @(client (:host mpd-server) (:port mpd-server))]
    @(s/put! c "consume 1\n")
    (return-obj c)))

(defn unset-consume
  "un-consume playlist"
  []
  (let [c @(client (:host mpd-server) (:port mpd-server))]
    @(s/put! c "consume 0\n")
    (return-obj c)))
