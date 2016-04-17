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

(defn weird-arity
  ([]
    "Destiny dressed you this morning,...")
  ([number]
    (inc number)))

(defn offmylawn
  [name]
  (str "Get off my lawn, " name "!!!"))

(defn allthekids
  [& names]
  (map offmylawn names))

(defn favorite-things
  [name & things]
  (str "Hi, " name ", here are my favorite things: "
       (clojure.string/join ", " things)))