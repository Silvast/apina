(ns apina.models.db
  (:require [yesql.core :refer [defqueries]]
            [clj-time.core :as t]
            [clj-time.coerce :as c]
            [clojure.pprint :refer [pprint]]
            [random-string.core]))

(random-string.core/string)

(def db-spec {:classname "org.postgresql.Driver"
              :subprotocol "postgresql"
              :subname "//localhost:5432/myDatabaseName"
              :user "clojuredb"})

(defqueries "apina/sql/cats.sql"
            {:connection db-spec})

(def seid (as->  (get-max-id) v
                 (into {} v)
                 (get v :id)))

(def currentmaxid (atom seid))

(defn addcurrentmaxid
  []
  (swap! currentmaxid inc))

  (as->  (get-max-id) v
                   (into {} v)
                   (get v :id))

(defn save-message
  [name message]
  (let [maxid  (as->  (get-max-id) v
                   (into {} v)
                   (get v :id))]
    (add-message {:id (inc maxid)
                :timestamp (c/to-sql-date (new java.util.Date))
                :name name
                :message message})))
;to test
;(save-message "Anne-Mari" "Jee, pääsen konffaan!")
