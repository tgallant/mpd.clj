(ns mpd-clj.core-test
  (:require [clojure.test :refer :all]
            [mpd-clj.utils :as u]
            [mpd-clj.db :as db]
            [manifold.stream :as s]))

(def mpd-server {:host "192.168.1.2" :port 6600})

;; utils tests

(deftest client-test
  (let [c @(u/client (:host mpd-server) (:port mpd-server))
        req @(s/put! c "status")
        res @(s/take! c)]
    (testing "client gets a valid response"
      (is (= (re-find #"OK MPD" res) "OK MPD")))))

(deftest send-cmd-map-test
  (let [res (u/send-cmd "status" mpd-server)]
    (testing "testing map response type"
      (is
        (= (type res) clojure.lang.PersistentHashMap)))))

(deftest send-cmd-vec-test
  (let [res (u/send-cmd "playlistinfo" mpd-server)]
    (testing "testing vector response type"
      (is
        (= (type res) clojure.lang.PersistentVector)))))

;; db tests

(deftest track-count-test
  (let [res-count (count (db/list-tracks "Awake" mpd-server))]
    (testing "testing db returns proper number of things"
      (is (= res-count 8)))))
