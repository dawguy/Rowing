(ns rowing-client.splits)

(def power 200)
(def s "135.25")

(defn left-pad [qty s]
  (cond (> qty (count (first (split-with #(not= "." %) s))))
        (str "0" (left-pad (dec qty) s))
        :else s
        ))

(defn powerToSplit [power]
  (let [s (* 500 (Math/cbrt (/ 2.80 power)))]
               (str (Math/floor (/ s 60)) ":" (left-pad 2 (.toFixed (js/Number (rem s 60)) 1)))))

(defn splitSecondsToPower [s]
  (/ 2.80 (Math/pow (/ s 500) 3)))

