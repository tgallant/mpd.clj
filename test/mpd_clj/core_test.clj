(ns mpd-clj.core-test
  (:require [clojure.test :refer :all]
            [mpd-clj.core :refer :all]))

(deftest res-str-test
  (let [test-str "test: hello!"]
    (testing "res-str input and output"
      (is (and
            (string? (:key (res-str test-str)))
            (string? (:value (res-str test-str))))))))

