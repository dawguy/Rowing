(ns rowing-client.browser
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [rowing-client.views :as views]))

(defn default []
  [:div "DEFAULT TEXT"])

(defonce active-page (r/atom default))
(defonce active-text (r/atom "abc"))

(defn aPage [] [:div "Ayoo!"])
(defn active-text-page [] [:div @active-text])

(defn ^:dev/after-load start []
  (rdom/render [:div [views/navbar]] (js/document.getElementById "app"))
  )

(defn init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (start))                                 ;; TODO. Figure out why this isn't working

;; this is called before any code is reloaded
(defn ^:dev/before-load stop []
  (js/console.log "stop"))
