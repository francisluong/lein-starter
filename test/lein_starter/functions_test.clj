(ns lein-starter.functions-test
  (:require [clojure.test :refer :all]
            [lein-starter.functions :refer :all]))

(deftest sequences
  (testing "seq basics"
    (is (= (seq [1 2 3]) '(1 2 3)))
    (is (= (seq {:a 1, :b 2}) '([:a 1] [:b 2])))
    (is (= (into {} '([:a 1] [:b 2])) {:a 1 :b 2}))))

(deftest map-double-test
  (testing "sequence function examples"
    (is (= (map inc [1 2 3])
           '(2 3 4)))
    (is (= '("aA" "bB" "cC") (map str ["a" "b" "c"] ["A" "B" "C"])))
    (is (= '("aA" "bB" "cC") (list (str "a" "A") (str "b" "B") (str "c" "C"))))
    (is (= (map unify-diet-data human-consumption critter-consumption)
           '({:human 8.1, :critter 0.0} {:human 7.3, :critter 0.2}
              {:human 6.6, :critter 0.3} {:human 5.0, :critter 1.1})))))

(deftest sum-test
  (testing "sum over seq abstractions"
    (is (= (sum [5 5 5 5]) 20))))

(deftest avg-test
  (testing "average over seq abstractions"
    (is (= (avg [5 5 5]) 5.0))
    (is (= (avg [1 2 3]) 2.0))))

(deftest crude-stats-test
  (testing "crude-stats"
    (is (= 144/5 144/5))
    (is (= (double (/ 144 5)) 28.8))
    (is (= (double (/ 17 3)) 5.666666666666667))
    (is (= (crude-stats [3 4 10]) '(17 3 5.666666666666667)))
    (is (= (crude-stats [80 1 44 13 6]) '(144 5 28.8)))))

(deftest map-basics-test
  (testing "map with reader"
    (is (= '("Hello Francis") (map #(str "Hello " %) ["Francis"]))))
  (testing "map with args"
    (is (= '(4 5 6) (map #(+ % 3) [1 2 3])))))

(deftest identities-test
  (testing "mapping using keyword"
    (is (= (map :real identities)
           '("Bruce Wayne" "Peter Parker" "Your mom" "Your dad")))))

(deftest assoc-test
  (testing "assoc"
    (is (= (assoc {} :value 1) {:value 1}))
    (is (= (assoc (assoc {} :max 30) :min 10)
           {:min 10 :max 30}))))

(deftest more-reduce-test
  (testing "more reductions"
    (is (= (mapmap inc {:max 30 :min 10}) {:max 31 :min 11}))
    (is (= (reduce (fn [acc-map [key val]]
                     (if (> val 4)
                       (assoc acc-map key val)
                       acc-map)) {} {:human 4.1 :critter 3.9})
           {:human 4.1}))))

(deftest titleze-test
  (testing "titleize"
    (is (= (map titleize ["H" "R"]) '("H for the Brave and True" "R for the Brave and True")))
    (is (= (map titleize '("X")) '("X for the Brave and True")))
    (is (= (map titleize #{"Hashsets" "Soap Carving"}) '("Hashsets for the Brave and True"
                                                         "Soap Carving for the Brave and True")))
    (is (= nil (second {:u "W"})))
    (is (= (map #(titleize (second %)) {:u "W"}) '("W for the Brave and True")))))
