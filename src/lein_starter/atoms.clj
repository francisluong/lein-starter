(ns lein-starter.atoms)

(defn increase-hunger
  [current-state increase-by]
  (merge-with + current-state {:hunger-level increase-by}))

(defn shuffle-speed
  [zombie]
  (* (:hunger-level zombie)
     (- 100 (:percent-deteriorated zombie))))

(defn shuffle-alert
  [key watched old-state new-state]
  (println "watched:" @watched "\n===\n")
  (let [speed (shuffle-speed new-state)]
    (if (> speed 5000)
      (do
        (println "RUN!!!!!"))
      (do
        (println "All is well")))
    (println "Zed:" new-state)
    (println "Speed:" speed)
    (println "key:" key)))

(defn percent-deteriorated-validator
  [{:keys [percent-deteriorated] :as all}]
  (println "percent-deteriorated-validator:" all)
  (and (>= percent-deteriorated 0)
       (<= percent-deteriorated 100)))
