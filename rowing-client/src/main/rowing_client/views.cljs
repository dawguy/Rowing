(ns rowing-client.views
  (:require [reagent.dom :as rdom]))

(defn render [comp]
  (rdom/render comp (js/document.getElementById "app")))

(defn navbar []
  [:nav.bg-gray-800
   [:div.flex-1.flex.items-center.justify-center {:class "sm:items-stretch sm:justify-start"}
    [:div.flex.space-x-4
     [:a.text-gray-300.rounded-md.px-3.py-2 {:href "/home"} "Home"]
     [:a.text-gray-300.rounded-md.px-3.py-2 {:href "/workouts"} "Workouts"]
     [:a.text-gray-300.rounded-md.px-3.py-2 {:href "/calendar"} "Calendar"]]]])

(defn workoutContainer [workout]
  [:tr
   [:td.w-full.p-3.text-center.border.lg:table-cell.border-b.hidden (get-in workout [:boat :name])]
   [:td.w-full.p-3.text-center.border.lg:table-cell.border-b.hidden (get-in workout [:athlete :name])]
   [:td.w-full.p-3.text-center.border.lg:table-cell.border-b (:date workout)]
   [:td.w-full.p-3.text-center.border.lg:table-cell.border-b (:power workout)]
   [:td.w-full.p-3.text-center.border.lg:table-cell.border-b (:duration workout)]
   [:td.w-full.p-3.text-center.border.lg:table-cell.border-b (count (:splits workout))]
   ])

(def sample-workouts [
                      {:id 1 :duration 40 :power 200 :athlete {:id 1 :name "David Wright"} :boat {:id 0 :name "Erg"} :date "2022-10-05" :splits [:a :b :c]}
                      {:id 2 :duration 500 :power 220 :athlete {:id 1 :name "David Wright"} :boat {:id 0 :name "Erg"} :date "2022-10-05" :splits [:a :b :c]}
                      {:id 3 :duration 240 :power 200 :athlete {:id 1 :name "David Wright"} :boat {:id 1 :name "Beaver"} :date "2022-10-05" :splits [:a :b :c]}
                      ])

(defn workoutsContainer []
  [:div.sm:px-7.w-full
   [:div.bg-white.py-4.md:py-7.px-4.md:px-8.xl:px-10
      [:div.relative.w-full.px-4.max-w-full.flex-grow.flex-1 [:h3.text-lg "Workouts"]]
    [:div.mt-7.block.w-full.overflow-x-auto
     [:table.w-full.whitespace-nowrap
      [:thead [:tr
               [:th.p-3.font-bold.uppercase.border.lg:table-cell.hidden "Boat"]
               [:th.p-3.font-bold.uppercase.border.lg:table-cell.hidden "Athlete"]
               [:th.p-3.font-bold.uppercase.border.lg:table-cell "Date"]
               [:th.p-3.font-bold.uppercase.border.lg:table-cell "Duration"]
               [:th.p-3.font-bold.uppercase.border.lg:table-cell "Power"]
               [:th.p-3.font-bold.uppercase.border.lg:table-cell "Splits"]]]
      [:tbody
       (map workoutContainer sample-workouts)
       ]]]]])

(defn pageContent []
  [:div workoutsContainer])


(defn mainPage []
  [:div.min-h-screen.bg-gray-100
   [navbar]
   [workoutsContainer]])