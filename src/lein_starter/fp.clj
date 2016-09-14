(ns lein-starter.fp)

(defn rsum
  ([vals] (rsum vals 0))
  ([vals acc-total]
   (if (empty? vals)
    acc-total
    (recur (rest vals) (+ (first vals) acc-total)))))

(require '[clojure.string :as s])
(defn cleanlol
  [text]
  (s/replace (s/trim text) #"lol" "LOL"))

(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})
(def c-int (comp :intelligence :attributes))
(def c-str (comp :strength :attributes))
(def c-dex (comp :dexterity :attributes))

(defn spell-slots
  [char]
  (int (inc (/ (c-int char) 2))))

(def spell-slots-comp (comp int inc #(/ % 2) c-int))

(defn sleepy-identity
  "Returns the given value after 1 second"
  [x]
  (Thread/sleep 1000)
  x)
(def memo-sleepy-identity (memoize sleepy-identity))

(defmacro defattrs
  "In Chapter 5 you created a series of functions (c-int, c-str, c-dex)
  to read an RPG character’s attributes. Write a macro that defines an
  arbitrary number of attribute-retrieving functions using one macro call."
  ([] [])
  ([fname attrib & remain]
   (conj [] `(def ~fname (comp ~attrib :attributes))))) ; todo: how to recurse
  ;  (def c-int (comp :intelligence :attributes))
  ;  (def c-str (comp :strength :attributes))
  ;  (def c-dex (comp :dexterity :attributes))
