(ns mpd-clj.core
  (:require [potemkin]
            [mpd-clj.utils]
            [mpd-clj.controlls]
            [mpd-clj.db]
            [mpd-clj.options]
            [mpd-clj.playback]
            [mpd-clj.status]
            [mpd-clj.stored]))

(defmacro import-all-vars
  [namespace]
  `(potemkin/import-vars
    [~namespace
     ~@(map key (ns-publics (the-ns namespace)))]))

(import-all-vars mpd-clj.utils)
(import-all-vars mpd-clj.controlls)
(import-all-vars mpd-clj.db)
(import-all-vars mpd-clj.options)
(import-all-vars mpd-clj.playback)
(import-all-vars mpd-clj.status)
(import-all-vars mpd-clj.stored)
