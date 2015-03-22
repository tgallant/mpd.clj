(ns mpd-clj.db
  "the music database http://www.musicpd.org/doc/protocol/database.html"
  (:require [clojure.string :as str]
            [mpd-clj.core :as mpd]
            [mpd-clj.utils :refer [send-cmd]]))
