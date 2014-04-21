(ns mscrobbler.routes.scrobble
  (:use compojure.core)
  (:require [cheshire.core]
            [mscrobbler.models.db :as db]
            [mscrobbler.util :as util]))

;; Submission contains following details
;; s - sessionID (required)
;; a - artist (required)
;; t - track title (required)
;; i - time when the track started playing as a UNIX timestamp which must be in UTC timezone (required)
;; o - source (required) will be one of
;;     P - chosen by user and there are (R, E, L, U) which aren't useful
;; r - rating of the track (not required)
;;     L - love, B - Ban, S - Skip
;; l - length of the track in seconds (required when the source is P)
;; b - album title (not required)
;; n - position of the track on the album (not required)
;; m - MusicBrainz track ID (not required)

(defn get-all-users []
  (cheshire.core/generate-string (db/get-users)))
