(ns apina.views.layout
  (:require [hiccup.page :refer [html5 include-css]]
            [hiccup.bootstrap.page :refer :all]))

(defn common [& body]
  (html5
    [:head
     [:title "Welcome to apina"]
     (include-css "/css/screen.css")]
    [:body body]))

    (defn page [& body]
      (html5
        [:head
          [:title "Bootstrapped Example"]
          (include-bootstrap)]
        [:body body]))
