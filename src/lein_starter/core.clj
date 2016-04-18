(ns lein-starter.core)

(def myname "Franco")

(defn foo
  "I don't do a whole lot."
  [x]
  (do (def hello (str x " Hello, World!"))
      (println hello)
      hello))

(defn x-chop
  "Describe the kind of chop you're inflicting on someone"
  ([name chop-type]
   (str "I " chop-type " chop " name "! Take that!"))
  ([name]
   (x-chop name "karate")))

;(0 or 1)-arity
(defn weird-arity
  ([]
    "Destiny dressed you this morning,...")
  ([number]
    (inc number)))

(defn offmylawn
  [name]
  (str "Get off my lawn, " name "!!!"))

; take many args into a list
(defn allthekids
  [& names]
  (map offmylawn names))

; 1-arity plus variable-arity
(defn favorite-things
  [name & things]
  (str "Hi, " name ", here are my favorite things: "
       (clojure.string/join ", " things)))

; binding names to list positions
(defn top-choices
  [[first-choice second-choice & other-choices]]
  (str "Your first choice is: " first-choice "\n"
       "Your second choice is " second-choice "\n"
       "We're ignoring the rest of your choices. "
       "Here they are in case you want to cry over them: "
       (clojure.string/join ", " other-choices)))

; destructuring a map to bind names to keys
;   :keys provides a list of keys that will be bound to the same variables
;   :as also gives you a name for the full map
(defn treasure-coordinates
  [{:keys [lat lng] :as treasure-location}]
  (str "lattitude: " lat "\n"
       "longitude: " lng "\n"
       "all: " treasure-location))

(defn anon-fn
  "fn form for anonymous function"
  []
  (map (fn [name ] (str "Hi, " name) ) ["Darth Vader" "Scooby Doo"]))

(defn times-three
  "more concise form for anonymous function - made possible by reader macro"
  [operand]
  (#(* % 3) operand))

(defn mapset
  "perform a map and return a set from the result"
  [function seq]
  (set (map function seq)))
