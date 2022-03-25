(ns rowing-client.browser
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]))

(defn rowrowrow [name]
  [:div name " Row Row Row The Boat"
   [:p "Gently down the stream!"]
   [:p "Then " name " caught a crab and it was tasty :)"]])

(defn ^:dev/after-load start []
  (rdom/render (rowrowrow "David Wright") (js/document.getElementById "app"))
  )

(defn init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (start))

;; this is called before any code is reloaded
(defn ^:dev/before-load stop []
  (js/console.log "stop"))
