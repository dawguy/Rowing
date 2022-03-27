(ns rowing-client.views
  (:require [reagent.dom :as rdom]))

(defn render [comp]
  (rdom/render comp (js/document.getElementById "app")))

(defn navbar []
   [:div.navbar
   [:ul.navbar-nav
     [:li.nav-item [:a.nav-link {:href "/home"} "Home"]]
     [:li.nav-item [:a.nav-link {:href "/workouts"} "Workouts"]]
     [:li.nav-item [:a.nav-link {:href "/calendar"} "Calendar"]]]])