(defproject apina "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.2"]
                 [hiccup "1.0.5"]
                 [ring-server "0.4.0"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]
                 [yesql "0.5.3"]
                 [clj-time "0.13.0"]
                 [org.clojure/data.generators "0.1.2"]
                 [random-string "0.1.0"]]
  :plugins [[lein-ring "0.8.12"]]
  :ring {:handler apina.handler/app
         :init apina.handler/init
         :destroy apina.handler/destroy}
  :profiles
  {:uberjar {:aot :all}
   :production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.5.1"]]}})
