(ns mscrobbler.models.schema
  (:require [clojure.java.jdbc :as sql]
            [noir.io :as io]))

(def db-store "site.db")

(def db-spec {:classname "org.h2.Driver"
              :subprotocol "h2"
              :subname (str (io/resource-path) db-store)
              :user "sa"
              :password ""
              :make-pool? true
              :naming {:keys clojure.string/lower-case
                       :fields clojure.string/upper-case}})
(defn initialized?
  "checks to see if the database schema is present"
  []
  (.exists (new java.io.File (str (io/resource-path) db-store ".h2.db"))))

(defn create-student-table
  []
  (sql/with-connection
    db-spec
    (sql/create-table
     :student
     [:id "INTEGER PRIMARY KEY AUTO_INCREMENT"]
     [:timestamp :timestamp]
     [:name "varchar(30)"]
     [:address "varchar(200)"])
    (sql/do-commands
      "CREATE INDEX student_timestamp_index ON student (timestamp)")))

(defn create-user-table
  []
  (sql/with-connection
    db-spec
    (sql/create-table
     :user
     [:id "INTEGER PRIMARY KEY AUTO_INCREMENT"]
     [:username "varchar(30)"]
     [:firstname "varchar(30)"]
     [:lastname "varchar(30)"]
     [:timestamp :timestamp])
    (sql/do-commands
      "CREATE INDEX username_index ON user (username)")))


(defn create-tables
  "creates the database tables used by the application"
  []
  (create-student-table)
  (create-user-table))
