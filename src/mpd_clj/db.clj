(ns mpd-clj.controlls
  (:require [clojure.string :as str]
            [mpd-clj.utils :refer [send-cmd]]))

;; the music database
;; http://www.musicpd.org/doc/protocol/database.html
