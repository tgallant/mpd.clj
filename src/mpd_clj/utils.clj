(ns mpd-clj.utils
  "utility functions for connecting to the mpd server"
  (:require [clojure.string :as str]
            [manifold.deferred :as d]
            [manifold.stream :as s]
            [aleph.tcp :as tcp]
            [gloss.core :as gloss]
            [gloss.io :as io]))

;; protocol definition
(def ^:private protocol (gloss/string :utf-8 :delimiters ["\n"]))

(defn- wrap-duplex-stream
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
  [server-obj]
  (d/chain (tcp/client {:host (:host server-obj) :port (:port server-obj)})
    #(wrap-duplex-stream protocol %)))

(defn- res-str
  "takes mpd protocol response, returns key value pair"
  [res]
  (let [res-arr (str/split res #": ")]
    {:key (first res-arr)
     :value (last res-arr)}))

(defn- res-handle
  "handles whether or not to return an array or a map"
  [res mpd-obj ret-vec]
  (let [res-kv (res-str res)
        res-key (:key res-kv)
        res-val (:value res-kv)]
    (if ((keyword res-key) mpd-obj)
      [{(keyword res-key) res-val} (conj ret-vec mpd-obj)]
      [(assoc mpd-obj (keyword res-key) res-val) ret-vec])))

(defn- return-obj
  "takes response channel, returns clojure keyword map"
  [c]
  (defn obj-inner [mpd-obj ret-vec]
    (let [res @(s/take! c)]
      (cond
        (re-find #"OK MPD" res) (obj-inner mpd-obj ret-vec)
        (= "OK" res) (if (empty? ret-vec) (if (empty? mpd-obj) nil mpd-obj) (conj ret-vec mpd-obj))
        :else
        (let [next-obj (res-handle res mpd-obj ret-vec)
              new-obj (get next-obj 0)
              new-vec (get next-obj 1)]
          (obj-inner new-obj new-vec)))))
  (obj-inner {} []))

(defn send-cmd
  "send request to mpd server"
  [cmd mpd-server]
  (s/put! @mpd-server cmd)
  (return-obj @mpd-server))
