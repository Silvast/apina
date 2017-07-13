(ns apina.handler
  (:require [compojure.core :refer [defroutes routes]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [apina.routes.home :refer [home-routes]]
            [yesql.core :refer [defqueries]]
            [clojure.pprint :refer [pprint]]
            [random-string.core]))

(defn init []
  (println "apina is starting"))

(defn destroy []
  (println "apina is shutting down"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (routes home-routes app-routes)
      (handler/site)
      (wrap-base-url)))

(def db-spec {:classname "org.postgresql.Driver"
              :subprotocol "postgresql"
              :subname "//localhost:5432/myDatabaseName"
              :user "clojuredb"})


(defqueries "apina/sql/cats.sql"
            {:connection db-spec})
