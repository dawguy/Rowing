(ns rowing-client.browser
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [rowing-client.views :as views]
            [rowing-client.cljs-http-client :as http]
            [cljs.core.async :refer [<!]])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(defn default []
  [:div "DEFAULT TEXT"])

(defonce active-page (r/atom default))
(defonce active-text (r/atom "abc"))
(defonce db-workouts (r/atom #()))

(defn aPage [] [:div "Ayoo!"])
(defn active-text-page [] [:div @active-text])

(defn ^:dev/after-load start []
  (rdom/render views/mainPage (js/document.getElementById "app"))
  )

(go (prn (<! (http/get "http://localhost:8080/workouts/erg/1"))))

(defn init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (start))                                 ;; TODO. Figure out why this isn't working

;; this is called before any code is reloaded
(defn ^:dev/before-load stop []
  (js/console.log "stop"))
