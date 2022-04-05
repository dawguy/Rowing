(ns rowing-client.views
  (:require [reagent.dom :as rdom]
            [d3 :as d3]
            [rowing-client.graphs :as graphs]))

(defn render [comp]
  (rdom/render comp (js/document.getElementById "app")))


(def power-profile-sample-data
  [
   {:duration 1 :power 500}
   {:duration 2 :power 444}
   {:duration 3 :power 400}
   {:duration 4 :power 350}
   {:duration 5 :power 340}
   {:duration 6 :power 330}
   {:duration 7 :power 320}
   {:duration 8 :power 310}
   {:duration 9 :power 300}
   {:duration 10 :power 300}
   {:duration 11 :power 290}
   {:duration 12 :power 290}
   {:duration 13 :power 290}
   {:duration 14 :power 285}
   {:duration 15 :power 285}
   {:duration 16 :power 285}
   {:duration 17 :power 280}
   {:duration 18 :power 280}
   ])

(def splits-sample-data
  [
   {:duration 90 :power 250 :seq 0}
   {:duration 30 :power 0 :seq 1}
   {:duration 90 :power 250 :seq 2}
   {:duration 30 :power 0 :seq 3}
   {:duration 90 :power 250 :seq 4}
   {:duration 30 :power 0 :seq 5}
   {:duration 90 :power 250 :seq 6}
   {:duration 30 :power 0 :seq 7}
   {:duration 90 :power 250 :seq 8}
   {:duration 240 :power 0 :seq 9}
   {:duration 90 :power 250 :seq 10}
   {:duration 30 :power 0 :seq 11}
   {:duration 90 :power 250 :seq 12}
   {:duration 30 :power 0 :seq 13}
   {:duration 90 :power 250 :seq 14}
   {:duration 30 :power 0 :seq 15}
   {:duration 90 :power 250 :seq 16}
   {:duration 30 :power 0 :seq 17}
   {:duration 90 :power 250 :seq 18}])

(def splits-sample-data-2
  [
   {:duration 600 :power 200 :seq 0}
   {:duration 600 :power 225 :seq 1}
   {:duration 600 :power 200 :seq 2}
   {:duration 600 :power 190 :seq 3}
   {:duration 600 :power 225 :seq 4}
   {:duration 600 :power 200 :seq 5}
  ])

(defn navbar []
  [:nav.bg-gray-800
   [:div.flex-1.flex.items-center.justify-center {:class "sm:items-stretch sm:justify-start"}
    [:div.flex.space-x-4
     [:a.text-gray-300.rounded-md.px-3.py-2 {:href "/home"} "Home"]
     [:a.text-gray-300.rounded-md.px-3.py-2 {:href "/workouts"} "Workouts"]
     [:a.text-gray-300.rounded-md.px-3.py-2 {:href "/calendar"} "Calendar"]]]])

(defn workoutContainer [workout]
  [:tr {:key (random-uuid)}
   [:td.w-full.p-3.text-center.border.lg:table-cell.border-b.hidden (get-in workout [:boat :name])]
   [:td.w-full.p-3.text-center.border.lg:table-cell.border-b.hidden (get-in workout [:athlete :name])]
   [:td.w-full.p-3.text-center.border.lg:table-cell.border-b (:date workout)]
   [:td.w-full.p-3.text-center.border.lg:table-cell.border-b (:power workout)]
   [:td.w-full.p-3.text-center.border.lg:table-cell.border-b (:duration workout)]
   [:td.w-full.p-3.text-center.border.lg:table-cell.border-b (graphs/workout (:splits workout))]
   ])

(def sample-workouts [
                      {:id 1 :duration 40 :power 200 :athlete {:id 1 :name "David Wright"} :boat {:id 0 :name "Erg"} :date "2022-10-05" :splits splits-sample-data}
                      {:id 2 :duration 500 :power 220 :athlete {:id 1 :name "David Wright"} :boat {:id 0 :name "Erg"} :date "2022-10-05" :splits splits-sample-data-2}
                      {:id 3 :duration 240 :power 200 :athlete {:id 1 :name "David Wright"} :boat {:id 1 :name "Beaver"} :date "2022-10-05" :splits power-profile-sample-data}
                      ])

(def sample-workout-1 [{:id 1 :duration 40 :power 200 :athlete {:id 1 :name "David Wright"} :boat {:id 0 :name "Erg"} :date "2022-10-05" :splits splits-sample-data}])
(def sample-workout-2 [{:id 2 :duration 500 :power 220 :athlete {:id 1 :name "David Wright"} :boat {:id 0 :name "Erg"} :date "2022-10-05" :splits splits-sample-data-2}])

(defn workoutsContainer [w]
  [:div.sm:px-7.w-full
   [:div.bg-white.py-4.md:py-7.px-4.md:px-8.xl:px-10
      [:div.relative.w-full.px-4.max-w-full.flex-grow.flex-1 [:h3.text-lg "Workouts"]]
    [:div.mt-7.block.w-full.overflow-x-auto
     [:table.w-full.whitespace-nowrap
      [:thead [:tr
               [:th.p-3.font-bold.uppercase.border.lg:table-cell.hidden "Boat"]
               [:th.p-3.font-bold.uppercase.border.lg:table-cell.hidden "Athlete"]
               [:th.p-3.font-bold.uppercase.border.lg:table-cell "Date"]
               [:th.p-3.font-bold.uppercase.border.lg:table-cell "Power"]
               [:th.p-3.font-bold.uppercase.border.lg:table-cell "Duration"]
               [:th.p-3.font-bold.uppercase.border.lg:table-cell "Splits"]]]
      [:tbody
       (map workoutContainer w)
       ]]]]])


(defn splitsContainer []
  [:div (graphs/workout (:splits (first sample-workouts)))])

(defn pageContent []
  [:div workoutsContainer])


(defn mainPage []
  [:div.min-h-screen.bg-gray-100
   [navbar]
   (workoutsContainer sample-workouts)
   ])