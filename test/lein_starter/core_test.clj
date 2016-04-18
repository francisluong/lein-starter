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