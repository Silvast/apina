(ns apina.models.db
  (:require [yesql.core :refer [defqueries]]
            [clojure.pprint :refer [pprint]]))

(def db-spec {:classname "org.postgresql.Driver"
              :subprotocol "postgresql"
              :subname "//localhost:5432/myDatabaseName"
              :user "clojuredb"})

(defqueries "apina/sql/cats.sql"
            {:connection db-spec})


(get-cats)

(add-message {:id 2
              :timestamp "2017-07-10T14:14:39.882-00:00"
              :name "Ansku"
              :message "whatsupyo"})



(get-messages)
