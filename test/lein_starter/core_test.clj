(ns lein-starter.core-test
  (:require [clojure.test :refer :all]
            [lein-starter.core :refer :all]))

(deftest myname-test
  (testing "myname"
    (is (= myname "Franco"))))

(deftest foo-test
  (testing "foo"
    (is (= (foo "Franco") "Franco Hello, World!"))))

(deftest x-chop-test
  (testing "x-chop"
    (is (= (x-chop "Kanye East"), "I karate chop Kanye East! Take that!"))
    (is (= (x-chop "Kanye West" "slap") "I slap chop Kanye West! Take that!"))))


(deftest weird-arity-test
  (testing "weird-arity"
    (is (= (weird-arity) "Destiny dressed you this morning,..."))
    (is (= (weird-arity 1) 2))))

(deftest offmylawn-test
  (testing "offmylawn"
    (is (= (offmylawn "Franco") "Get off my lawn, Franco!!!"))))

(deftest allthekids-test
  (testing "allthekids"
    (is (= (allthekids "a" "b") ["Get off my lawn, a!!!" "Get off my lawn, b!!!"]))))

(deftest favorite-things-test
  (testing "favorite-things"
    (is (= (favorite-things "Franco" "raindrops" "roses" "kisses" "kittens")
           "Hi, Franco, here are my favorite things: raindrops, roses, kisses, kittens"))))

(deftest top-choices-test
  (testing "top-choices"
    (is (= (top-choices ["alpha" "bravo" "charlie" "delta"])
           (str "Your first choice is: alpha\n"
                "Your second choice is bravo\n"
                "We're ignoring the rest of your choices. "
                "Here they are in case you want to cry over them: charlie, delta")))))

(deftest treasure-coordinates-test
  (testing "treasure-coordinates"
    (is (= (treasure-coordinates {:lat 1 :lng 2})
           "lattitude: 1\nlongitude: 2\nall: {:lat 1, :lng 2}"))))

(deftest divide-by-zero
  (testing "ArithmeticException when divide by zero"
    (is (thrown? ArithmeticException (/ 1 0)))))

(deftest anon-fn-test
  (testing "anon-fn"
    (is (= (anon-fn) ["Hi, Darth Vader" "Hi, Scooby Doo"]))))

(deftest times-three-test
  (testing "times-three"
    (is (= (times-three 8) 24))))

(deftest maps
  (testing "fetching values"
    (is (= (:name {:name "Franco" :rank "Admiral"}) "Franco"))))

(deftest let-test
  (testing "let basics and scoping"
     (def x 3)
     (def y 2)
     (is (= x 3))
     ; in the context of let, x will represent the value: 4
     (let [x 4]
       (is (= x 4))
       ; y is available thanks to lexical scope
       (is (= y 2)))
     ; ...and then returns to its previous value after we exit the context
     (is (= x 3)))
  (testing "remainder variables"
    (def dalmatian-list ["Pongo" "Perdita" "Puppy 1" "Puppy 2"])
    (let [[first & others] dalmatian-list]
      (is (= first "Pongo"))
      (is (= others ["Perdita" "Puppy 1" "Puppy 2"])))
    ))

(deftest into-test
  ; into is used because many functions that operate on a collection return a seq
  (testing "into example which does set stuff first"
    (is (= (into [] (set [:a :a])) [:a])))
  (testing "even simpler version - convert a set to a vector"
    (is (= (into [] #{:a :b}) [:b :a])))
  (testing "seems to reverse order... how about with a map/identity?"
    (is (= (into [] (map identity #{:a :b})) [:b :a])))
  (testing "into an existing value"
    (is (= (into [:a] [:b :c]) [:a :b :c]))))

(deftest indentity-test
  (testing "identity"
    (testing "on a symbol"
      (is (= (identity :a)) :a))
    (testing "on a string"
      (is (= (identity "bob") "bob")))))

;(deftest rest-test
;  (testing "rest on vector"
;    (is (= (rest [1 2 3])
;           ([2 3])))))

(deftest loop-test
  (testing "this prints to stdout using a loop"
    (println "LOOP-TEST: this prints to stdout")
    (loop [iteration 0]
      (println (str "Iteration " iteration))
      (if (> iteration 3)
        ; then (exit condition)
        (println "Goodbye!")
        ; else (recur)
        (recur (inc iteration))))
    (println "LOOP-TEST end")
    (is (= 1 1))))

(deftest re-find-test
  (testing "basic matching and return values"
    (is (= (re-find #"^left-" "left-eye") "left-"))
    (is (= (re-find #"^left-" "right-eye") nil))))

(deftest reduce-test
  (testing "basic against an array"
    (is (= (reduce + [1 2 3 4]) 10)))
  (testing "with initial value"
    (is (= (reduce + 15 [1 2 3 4]) 25))))

(deftest vector-test
  (testing "creating a vector by function"
    (is (= (vector 1 2 3) [1 2 3]))))

(deftest hash-map-test
  (testing "basic"
    (is (= (hash-map :a 2 :b 4 :a 3) {:b 4 :a 3}))))

(deftest hash-set-test
  (testing "basic hash-set creates a set from all args, eliminating dupes"
    (is (= (hash-set 1 2 3 4 4 2 3 1) #{1 2 3 4}))))

(deftest set-franco-test
  (testing "set creates a set from vector/list, eliminating dupes"
    (is (= (set [:a :b :a :c]) #{:c :b :a}))
    (is (= (set '(1 2 3 4 4 4)) #{1 2 3 4}))))

(deftest map-test
  (testing "basic map function"
    (is (= (map inc [1 2 3]) [2 3 4]))))

(deftest mapset-test
  (testing "basic mapset"
    (is (= (mapset inc [1 1 1 2 2 2 3 3 3]) [2 3 4]))))
