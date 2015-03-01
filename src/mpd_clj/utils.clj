(ns mpd-clj.utils
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

(defn send-cmd
  "send request to mpd server"
  [cmd]
  (let [c @(client (:host mpd-server) (:port mpd-server))]
    @(s/put! c cmd)
    (return-obj c)))
