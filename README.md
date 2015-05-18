# mpd-clj

An MPD client implementation in clojure

## Declaring Dependency

`[mpd-clj "0.1.8"]`

## Basic Usage

You can pull in the entire library by requireing mpd-clj.core

```clojure
(ns example.core
  (:require [mpd-clj.core :as mpd]))

;; define the host and port of your mpd server
(def mpd-server {:host "10.0.0.1" :port 6600})

;; execute the stats command on the specified server
(mpd/stats mpd-server)
```

Or you can pull in individual pieces 

```clojure
(ns example.core
  (:require [mpd-clj.status :refer :all])

;; define the host and port of your mpd server
(def mpd-server {:host "10.0.0.1" :port 6600})

;; execute the stats command on the specified server
(stats mpd-server)
```

The namespaces you can pull in are

* [mpd-clj.status](http://tgallant.github.io/mpd.clj/mpd-clj.status.html)
* [mpd-clj.options](http://tgallant.github.io/mpd.clj/mpd-clj.options.html)
* [mpd-clj.playback](http://tgallant.github.io/mpd.clj/mpd-clj.playback.html)
* [mpd-clj.controlls](http://tgallant.github.io/mpd.clj/mpd-clj.controlls.html)
* [mpd-clj.stored](http://tgallant.github.io/mpd.clj/mpd-clj.stored.html)
* [mpd-clj.db](http://tgallant.github.io/mpd.clj/mpd-clj.db.html)

All functions require a server config parameter. In my usage I call it
`mpd-server` but you can call it anything. The server configuration is
a map with two key value pairs, `host` and `port`. `host` expects a
string of either an IP address or FQDN. `port` expects an integer.

## Documentation

[API docs](https://tgallant.github.io/mpd.clj)

## Todo

* make library feature complete with mpd documentation
* write more tests

## License

Copyright © 2015 Tim Gallant

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
