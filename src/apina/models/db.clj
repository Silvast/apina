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

(defn save-message
  [name message]
  (add-message { :timestamp (c/to-sql-date (new java.util.Date))
                  :name name
                  :message message}))
;to test
;(save-message "Anne-Mari" "Jee, pääsen konffaan!")
