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
            [random-string.core]
            [hiccup.bootstrap.middleware :refer :all]
            [clj-http.client :as client]))

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
      (wrap-base-url)
      (wrap-bootstrap-resources)))

(def db-spec {:classname "org.postgresql.Driver"
              :subprotocol "postgresql"
              :subname "//localhost:5432/myDatabaseName"
              :user "clojuredb"})


(defqueries "apina/sql/cats.sql"
            {:connection db-spec})

;;Takes organisational id (ytunnus) and the language, returns name, address, email and id in a json string

(defn get-organisation [id language]
  (let [url (str "https://virkailija.opintopolku.fi/organisaatio-service/rest/organisaatio/" id "?includeImage=" "false")
        data  (-> (client/get url)
                  (:body)
                  (cheshire.core/parse-string ))
        name (get-in data ["nimi" language])
        email (get-in data [ "yhteystietoArvos" 0 "YhteystietoArvo.arvoText"])
        address (str (get-in data ["postiosoite" "osoite"]) " " (.replaceAll (get-in data ["postiosoite" "postinumeroUri"]) "[^0-9]" "") " "(get-in data ["postiosoite" "postitoimipaikka"]))]
    (assoc {} :name name :email email :address address :businessId id)))


(get-organisation "0124610-9" "sv")


(defn get-organisation2 [id language]
  (let [url (str "https://virkailija.opintopolku.fi/organisaatio-service/rest/organisaatio/" id "?includeImage=" "false")
        data  (-> (client/get url)
                  (:body)
                  (cheshire.core/parse-string ))
        address (get-in data ["yhteystiedot" 0 "postitoimipaikka"])]
    (assoc {} :address address)))

(get-organisation2 "0124610-9" "fi")


(.replaceAll (get-in data ["postiosoite" "postinumeroUri"]) "[^0-9]" "")

;;(get-in (cheshire.core/parse-string (get-organisation "0135662-3")) ["nimi" "fi"]) ;; "Nakkilan kunta"
;;(get-in (cheshire.core/parse-string (get-organisation "0135662-3")) ["postiosoite" "osoite"]) ;;PL 50
;;(get-in (cheshire.core/parse-string (get-organisation "0135662-3")) ["postiosoite" "postinumeroUri"]) ;;"posti_29251"
;;(get-in (cheshire.core/parse-string (get-organisation "0135662-3")) ["postiosoite" "postitoimipaikka"]) ;;NAKKILA

;; (get-in (cheshire.core/parse-string (:body (client/get url)))))

