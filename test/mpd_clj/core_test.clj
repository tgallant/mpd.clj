(ns mpd-clj.core-test
  (:require [clojure.test :refer :all]
            [mpd-clj.utils :as u]
            [mpd-clj.db :as db]
            [manifold.stream :as s]
            [manifold.deferred :as d]))

(def mpd-server {:host "localhost" :port 6600})

(defn client-with-response [response]
  (let [client (d/deferred)
        stream (s/stream (count response))
        put (partial s/put! stream)]
    (d/success! client stream)
    (s/put-all! stream response)
    client))

;; utils tests

(comment "this is an integration test"
         (deftest client-test
           (let [c @(u/client mpd-server)
                 req @(s/put! c "status")
                 res @(s/take! c)]
             (testing "client gets a valid response"
               (is (= (re-find #"OK MPD" res) "OK MPD"))))))

(deftest send-cmd-map-test
  (let [client (client-with-response ["OK MPD ..." "a: 1" "b: 2" "OK"])
        res (u/send-cmd "status" client)]
    (testing "testing map response type"
      (is
        (= (type res) clojure.lang.PersistentArrayMap)
        (= {:a "1" :b "2"} res)))))

(deftest send-cmd-vec-test
  (let [client (client-with-response ["OK MPD ..." "a: 1" "a: 2" "OK"])
        res (u/send-cmd "playlistinfo" client)]
    (testing "testing vector response type"
      (is
        (= (type res) clojure.lang.PersistentVector)
        (= [{:a "1"} {:a "2"}] res)))))

;; db tests

(comment "this is an integration test" 
         (deftest track-count-test
           (let [res-count (count (db/list-tracks "Awake" (u/client mpd-server)))]
             (testing "testing db returns proper number of things"
               (is (= res-count 8))))))
