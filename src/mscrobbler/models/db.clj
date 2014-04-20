(ns mscrobbler.models.db
  (:use korma.core
        [korma.db :only (defdb)])
  (:require [mscrobbler.models.schema :as schema]))

(defdb db schema/db-spec)

;; Student ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defentity student)

(defn save-student
  [name address]
  (insert student
          (values {:name name
                   :address address
                   :timestamp (new java.util.Date)})))

(defn get-students []
  (select student))

;; TODO: Define entities required for audio srobbling functionality
