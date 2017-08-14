(ns apina.test.handler
  (:use clojure.test
        ring.mock.request
        apina.handler
        data.generators))
;
(deftest test-app
  (testing "main route"
    (let [response (app (request :get "/"))]
      (is (= (:status response) 200))
      (is (.contains (:body response) "Guestbook"))))

  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= (:status response) 404)))))


;; test that the saving of messages works
; (deftest savingmessages
;   (testing "testing saving a message"
;   (let [response (app (request :post "/name=Testikayttaja&message=Testiviesti"))]
;   (is (= (:status response) 200))
;   (is (.contains (:body response) "Testiviesti"))))

; (deftest test-app2
;   (testing "save"
;     (let [amessage (char-array 29) response (app (request :post "/?name=Testikayttaja&message=amessage"))]
;       (is (= (:status response) 200))
;       (is (.contains (:body response) amessage))))
;
;   (testing "not-found route"
;     (let [response (app (request :get "/invalid"))]
;       (is (= (:status response) 404)))))
