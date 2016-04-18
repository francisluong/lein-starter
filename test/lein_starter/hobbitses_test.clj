(ns lein-starter.hobbitses-test
  (:require [clojure.test :refer :all]
            [lein-starter.hobbitses :refer :all]))

(deftest matching-part-test
  (testing "matching-part replaces left with right"
    (is (= (matching-part {:name "left-thumb" :size 1})
           {:name "right-thumb" :size 1})))
  (testing "doesn't change head"
    (is (= (matching-part {:name "head" :size 3})
           {:name "head" :size 3}))))
(deftest symmetrize-body-parts-test
  (testing "symmetrize-body-parts"
    (is (= (symmetrize-body-parts asym-hobbit-body-parts)
           [{:name "head", :size 3} {:name "left-eye", :size 1} {:name "right-eye", :size 1} {:name "left-ear", :size 1} {:name "right-ear", :size 1} {:name "mouth", :size 1} {:name "nose", :size 1} {:name "neck", :size 2} {:name "left-shoulder", :size 3} {:name "right-shoulder", :size 3} {:name "right-upper-arm", :size 3} {:name "left-upper-arm", :size 3} {:name "chest", :size 10} {:name "back", :size 10} {:name "left-forearm", :size 3} {:name "right-forearm", :size 3} {:name "abdomen", :size 6} {:name "left-kidney", :size 1} {:name "right-kidney", :size 1} {:name "left-hand", :size 2} {:name "right-hand", :size 2} {:name "right-knee", :size 2} {:name "left-knee", :size 2} {:name "right-thigh", :size 4} {:name "left-thigh", :size 4} {:name "right-lower-leg", :size 3} {:name "left-lower-leg", :size 3} {:name "right-achilles", :size 1} {:name "left-achilles", :size 1} {:name "right-foot", :size 2} {:name "left-foot", :size 2}]))))
