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

;; Basic Scrobble User
(defentity user)

(defn save-user
  [username firstname lastname]
  (insert user
          (values {:username username
                   :firstname firstname
                   :lastname lastname
                   :timestamp (java.util.Date.)})))

(defn get-users []
  (select user))

(defn delete-user [username]
  (delete user
          (where {:username username})))
