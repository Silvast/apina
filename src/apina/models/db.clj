(ns apina.models.db
  (:require [yesql.core :refer [defqueries]]
            [clj-time.core :as t]
            [clj-time.coerce :as c]
            [clojure.pprint :refer [pprint]]))

(def db-spec {:classname "org.postgresql.Driver"
              :subprotocol "postgresql"
              :subname "//localhost:5432/myDatabaseName"
              :user "clojuredb"})

(defqueries "apina/sql/cats.sql"
            {:connection db-spec})


(get-cats)

; (add-message {:id 9
;               :timestamp (c/to-sql-date (new java.util.Date))
;               :name "Bansku"
;               :message "you man"})
;
;
; ;
 (get-messages)
;

; (def currentmaxid
;   (atom (as->  (get-max-id) v
;         (into {} v)
;         (get v :id))))
;
; (pprint currentmaxid)

(def seid (as->  (get-max-id) v
                 (into {} v)
                 (get v :id)))

(type seid)

(def currentmaxid (atom seid))

(type currentmaxid)

(defn addcurrentmaxid
  []
  (swap! currentmaxid inc))

(addcurrentmaxid)

(type currentmaxid)
(addcurrentmaxid)

(defn save-message
  [name message]
  (add-message {:id (addcurrentmaxid)
                :timestamp (c/to-sql-date (new java.util.Date))
                :name name
                :message message}))


(save-message "Janne" "Heissulivei!")
