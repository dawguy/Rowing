(ns rowing-client.browser
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [rowing-client.views :as views]
            [rowing-client.router :as router]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(defonce app-db (r/atom {
                         ; Temporary start
                         :target :ergWorkout
                          :targetId 1
                         ; Temporary end
                         }))
(defonce saved-response (r/atom {}))

(defn page [data]
  (fn []
    (prn @data)
    (let [target-id (:targetId @data)
          target (:target @data)]
  (case (:target @data)
    :ergWorkout (views/workoutContainer
                  (get-in @data [(:target @data) (:targetId @data)])
                  (get-in @data [:athlete (:athlete (get-in @data [(:target @data) (:targetId @data)]))])
                  (get-in @data [:boat (:boat (get-in @data [(:target @data) (:targetId @data)]))]))
    (views/home)))))


(defn mainPage [data]
  [:div.min-h-screen.bg-gray-100
   [views/navbar]
   [page app-db]
   ])

(defn ^:dev/after-load start []
  (rdom/render (mainPage app-db) (js/document.getElementById "app"))
  )

; Seperated because splits are from workouts, not from the request :body
(defn save-data [vals table-name pk]
  ;(prn (str "Saving to app-db " table-name " pk " pk " value " vals))
  (swap! app-db assoc table-name (assoc (table-name @app-db) pk vals))
  )

(defn retrieve-erg-workout [response]
  (let [vals (:body response)]
    (reset! saved-response vals)
    (save-data vals :ergWorkout (:ergWorkout vals))
  (doseq [split (:splits vals)]
    (save-data split :ergSplit (:ergSplit split)))))



(comment []
         (go (retrieve-erg-workout
               (<! (http/get "http://localhost:8080/workouts/erg/1" {
                                                                     :headers           {"Access-Control-Request-Method" "GET"}
                                                                     :with-credentials? false}))))
         (go (retrieve-erg-workout
               (<! (http/get "http://localhost:8080/workouts/erg/7" {
                                                                     :headers           {"Access-Control-Request-Method" "GET"}
                                                                     :with-credentials? false}))))

         )



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
  (swap! app-db assoc :targetId 1)
  (def k :splits)
  (def k :ergWorkout)
  )
