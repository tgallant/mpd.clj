# mpd-clj

An MPD client implementation in clojure

## Declaring Dependency

`[mpd-clj "0.2.0"]`

*Note*: A breaking change in the API has occurred since 0.1.8. Please read the updated documentation below.

## Basic Usage

You can pull in the entire library by requireing mpd-clj.core

```clojure
(ns example.core
  (:require [mpd-clj.core :as mpd]))

;; create a client and pass it your host and port info.
(def mpd-server (mpd/client {:host "10.0.0.1" :port 6600}))

;; execute the stats command using the specified client
(mpd/stats mpd-server)
```

Or you can pull in individual pieces 

```clojure
(ns example.core
  (:require [mpd-clj.utils :as mpd]
            [mpd-clj.status :refer :all])

;; define the host and port of your mpd server
(def mpd-server (mpd/client {:host "10.0.0.1" :port 6600}))

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
* [mpd-clj.utils](http://tgallant.github.io/mpd.clj/mpd-clj.utils.html)

All functions require a client object. In my usage I call it
`mpd-server` but you can call it anything. The client function returns an object representing an asyncronous tcp connection. The client function requires a server configuration object. The server configuration is a map with two key value pairs, `host` and `port`. `host` expects a string of either an IP address or FQDN. `port` expects an integer.

## Documentation

[API docs](https://tgallant.github.io/mpd.clj)

## Todo

* make library feature complete with mpd documentation
* write more tests

## License

Copyright © 2015 Tim Gallant

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
