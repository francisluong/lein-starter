(ns lein-starter.functions)

(def human-consumption   [8.1 7.3 6.6 5.0])
(def critter-consumption [0.0 0.2 0.3 1.1])

(defn unify-diet-data
  [human critter]
  {:human human :critter critter})

(def sum #(reduce + %))
(def avg #(double (/ (sum %) (count %))))

(defn crude-stats
  [numbers]
  (map #(% numbers) [sum count avg]))

(def identities
  [{:alias "Batman" :real "Bruce Wayne"}
   {:alias "Spider-Man" :real "Peter Parker"}
   {:alias "Santa" :real "Your mom"}
   {:alias "Easter Bunny" :real "Your dad"}])

(defn mapmap
  [function map-input]
  (reduce (fn [acc-map [key val]]
            (assoc acc-map key (function val))) {} map-input))

