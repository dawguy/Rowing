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
   [:td.w-full.lg:w-auto.p-3.text-center.border.border-b.block.lg:table-cell.relative.lg:static (get-in workout [:boat :name])]
   [:td.w-full.lg:w-auto.p-3.text-center.border.border-b.block.lg:table-cell.relative.lg:static (get-in workout [:athlete :name])]
   [:td.w-full.lg:w-auto.p-3.text-center.border.border-b.block.lg:table-cell.relative.lg:static (:date workout)]
   [:td.w-full.lg:w-auto.p-3.text-center.border.border-b.block.lg:table-cell.relative.lg:static (:power workout)]
   [:td.w-full.lg:w-auto.p-3.text-center.border.border-b.block.lg:table-cell.relative.lg:static (:duration workout)]
   [:td.w-full.lg:w-auto.p-3.text-center.border.border-b.block.lg:table-cell.relative.lg:static (count (:splits workout))]
   ])

(def sample-workouts [
                      {:id 1 :duration 40 :power 200 :athlete {:id 1 :name "David Wright"} :boat {:id 0 :name "Erg"} :date "2022-10-05" :splits [:a :b :c]}
                      {:id 2 :duration 500 :power 220 :athlete {:id 1 :name "David Wright"} :boat {:id 0 :name "Erg"} :date "2022-10-05" :splits [:a :b :c]}
                      {:id 3 :duration 240 :power 200 :athlete {:id 1 :name "David Wright"} :boat {:id 1 :name "Beaver"} :date "2022-10-05" :splits [:a :b :c]}
                      ])

(defn workoutsContainer []
  [:div.sm:px-7.w-full
   [:div.bg-white.py-4.md:py-7.px-4.md:px-8.xl:px-10
    [:div.mt-7.overflow-x-auto
     [:table.w-full.whitespace-nowrap
      [:thead [:tr
               [:th.p-3.font-bold.uppercase.border.hidden.lg:table-cell "Boat"]
               [:th.p-3.font-bold.uppercase.border.hidden.lg:table-cell "Athlete"]
               [:th.p-3.font-bold.uppercase.border.hidden.lg:table-cell "Date"]
               [:th.p-3.font-bold.uppercase.border.hidden.lg:table-cell "Duration"]
               [:th.p-3.font-bold.uppercase.border.hidden.lg:table-cell "Power"]
               [:th.p-3.font-bold.uppercase.border.hidden.lg:table-cell "Splits"]]]
      [:tbody
       (map workoutContainer sample-workouts)
       ]]]]])

(defn pageContent []
  [:div workoutsContainer])


(defn mainPage []
  [:div.min-h-screen.bg-gray-100
   [navbar]
   [workoutsContainer]])