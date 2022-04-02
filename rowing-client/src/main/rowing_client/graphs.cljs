(ns rowing-client.graphs
  (:require [d3 :as d3]))

(defn power-profile [splits]
  (let
    [size 300
     durations (map :duration splits)
     values (map :power splits)
     x (->
         (d3/scaleLinear)
         (.domain (into-array [1 (apply max durations)]))
         (.range (into-array [0 size])))
     y (->
         (d3/scaleLinear)
         (.domain (into-array [0 (apply max values)]))
         (.range (into-array [size 0])))
     line (->
            (d3/line)
            (.x (fn [d] (x (:duration d))))
            (.y (fn [d] (y (:power d)))))]
    [:svg
     {:viewBox (str 0 " " 0 " " size " " size)}
     [:path
      {:d (line splits),
       :fill "transparent",
       :stroke (first d3/schemeCategory10)}]]))

(defn appendDurations [splits]
  ((fn helper[arr rem sum]
     (cond (< 1 (count rem))
           (helper (conj arr (assoc (first rem) :x sum)) (rest rem) (+ sum (:duration (first rem))))
           :else (conj arr (assoc (first rem) :x sum)))
     ) [] splits 0))

(defn workout [splits]
  (let
    [size 400
     durations (map :duration splits)
     powers (map :power splits)
     y (->
         (d3/scaleLinear)
         (.domain (into-array [0 (apply max powers)]))
         (.range (into-array [size 0])))
     x (->
             (d3/scaleLinear)
             (.domain (into-array [0 (apply + powers)]))    ;; Essentially the goal here is to maximize
             (.range (int-array [0 size])))]
    [:svg
     {:viewBox (str "0 0 " size " " size)}
     (map
       (fn
         [{:keys [duration power split seq]}]
         [:g
          {:key seq, :transform (str "translate(" (x duration) "," -10 ")")} ;; Might want to change -10 to an alternating wiggle value for less overlap
          [:rect
           {:x (x seq),
            :height (size - (y power)),
            :fill (color letter),
            :width (x frequency)}]]) (appendDurations splits))]))
