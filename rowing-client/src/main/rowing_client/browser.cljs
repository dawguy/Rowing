(ns rowing-client.browser
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [rowing-client.views :as views]
            [rowing-client.cljs-http-client :as http]
            [cljs.core.async :refer [<!]])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(defn default []
  (views/pageContent (views/workoutContainer views/sample-workout-1)))

(defonce active-page (r/atom (default)))
(defonce active-selection (r/atom {:page :home
                                   :id nil}))
(defonce db-workouts (r/atom {}))

(defn aPage [] [:div "Ayoo!"])
(defn active-text-page [] [:div @active-text])

(defn ^:dev/after-load start []
  (rdom/render (views/mainPage active-page) (js/document.getElementById "app"))
  )

(defonce saved-response (r/atom {}))

; For right now let's only focus on getting a single workout loaded and swapping between home page and that workout.
(defn load-data [response]
  (prn (identity response))
  (reset! saved-response (:body response))
  (swap! db-workouts assoc :workout (get-in response [:body :workout]))
  (prn (get-in response [:body :workout]))
  (prn @saved-response)
  (prn @db-workouts)
  (reset! active-page (views/workoutContainer @saved-response))
  (start)
  )

(go (load-data (<! (http/get "http://localhost:8080/workouts/erg/1" {
         :headers {"Access-Control-Request-Method" "GET"}
         :with-credentials? false}))))

(defn init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (start))                                                  ;; TODO. Figure out why this isn't working

;; this is called before any code is reloaded
(defn ^:dev/before-load stop []
  (js/console.log "stop"))

(comment
  "Helpful data sets for the REPL"
  []
  (def sample-response-workout {:ergWorkout 1, :date 1645506000000, :athlete 1, :assignedWorkout nil, :workout 1, :splits [{:ergSplit 2, :seq 0, :duration 100, :distance 1000, :heartRate 135, :power 200} {:ergSplit 3, :seq 1, :duration 100, :distance 1000, :heartRate 135, :power 200} {:ergSplit 51, :seq 2, :duration 100, :distance 1000, :heartRate 135, :power 200} {:ergSplit 52, :seq 3, :duration 100, :distance 1000, :heartRate 135, :power 200} {:ergSplit 53, :seq 4, :duration 100, :distance 1000, :heartRate 135, :power 200} {:ergSplit 54, :seq 5, :duration 100, :distance 1000, :heartRate 135, :power 200} {:ergSplit 55, :seq 6, :duration 100, :distance 1000, :heartRate 135, :power 200} {:ergSplit 56, :seq 7, :duration 100, :distance 1000, :heartRate 135, :power 200} {:ergSplit 68, :seq 8, :duration 100, :distance 1000, :heartRate 135, :power 200}], :name ""})
  (def sample-response-splits [{:ergSplit 2, :seq 0, :duration 100, :distance 1000, :heartRate 135, :power 200} {:ergSplit 3, :seq 1, :duration 100, :distance 1000, :heartRate 135, :power 200} {:ergSplit 51, :seq 2, :duration 100, :distance 1000, :heartRate 135, :power 200} {:ergSplit 52, :seq 3, :duration 100, :distance 1000, :heartRate 135, :power 200} {:ergSplit 53, :seq 4, :duration 100, :distance 1000, :heartRate 135, :power 200} {:ergSplit 54, :seq 5, :duration 100, :distance 1000, :heartRate 135, :power 200} {:ergSplit 55, :seq 6, :duration 100, :distance 1000, :heartRate 135, :power 200} {:ergSplit 56, :seq 7, :duration 100, :distance 1000, :heartRate 135, :power 200} {:ergSplit 68, :seq 8, :duration 100, :distance 1000, :heartRate 135, :power 200}])
  (def sample-response {:status 200, :success true, :body {:ergWorkout 1, :date 1645506000000, :athlete 1, :assignedWorkout nil, :workout 1, :splits [{:ergSplit 2, :seq 0, :duration 100, :distance 1000, :heartRate 135, :power 200} {:ergSplit 3, :seq 1, :duration 100, :distance 1000, :heartRate 135, :power 200} {:ergSplit 51, :seq 2, :duration 100, :distance 1000, :heartRate 135, :power 200} {:ergSplit 52, :seq 3, :duration 100, :distance 1000, :heartRate 135, :power 200} {:ergSplit 53, :seq 4, :duration 100, :distance 1000, :heartRate 135, :power 200} {:ergSplit 54, :seq 5, :duration 100, :distance 1000, :heartRate 135, :power 200} {:ergSplit 55, :seq 6, :duration 100, :distance 1000, :heartRate 135, :power 200} {:ergSplit 56, :seq 7, :duration 100, :distance 1000, :heartRate 135, :power 200} {:ergSplit 68, :seq 8, :duration 100, :distance 1000, :heartRate 135, :power 200}], :name ""}, :headers {"content-type" "application/json"}, :trace-redirects ["http://localhost:8080/workouts/erg/1" "http://localhost:8080/workouts/erg/1"], :error-code :no-error, :error-text ""})
)
