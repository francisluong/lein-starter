(ns lein-starter.atoms-test
  (:require [clojure.test :refer :all]
    [lein-starter.atoms :refer :all]))



(deftest atoms-test
  (def z-0-0 {:hunger-level 0, :percent-deteriorated 0})
  (def z-10-10 {:hunger-level 10, :percent-deteriorated 10})
  (def z-22-2 {:hunger-level 22, :percent-deteriorated 2})
  (testing "fred"
    (let [fred (atom z-0-0)]
      (is (= z-0-0 @fred))
      (swap! fred
             (fn [current-state]
               (merge-with + current-state {:hunger-level 1
                                            :percent-deteriorated 1})))
      (is (= {:hunger-level 1
              :percent-deteriorated 1} @fred))
      (swap! fred increase-hunger 10)
      (is (= {:hunger-level 11
              :percent-deteriorated 1} @fred))
      ; a quick dereference lets to have both values
      (let [prev @fred]
        (swap! fred update-in [:hunger-level] + 10)
        (is (= {:hunger-level 21
                :percent-deteriorated 1} @fred))
        (is (= {:hunger-level 11
                :percent-deteriorated 1} prev)))
      ; reset! doesn't do checks
      (reset! fred {:hunger-level 0
                    :percent-deteriorated 0})
      (is (= {:hunger-level 0
              :percent-deteriorated 0} @fred))))

  (testing "update-in"
    (let [in-value {:a {:b 3}}
          key-vec [:a :b]]
      (is (= {:a {:b 4}} (update-in in-value key-vec inc)))))

  (testing "shuffle-speed"
    (is (= 0 (shuffle-speed z-0-0)))
    (is (= 900 (shuffle-speed z-10-10))))

  (testing "adding a watch on fred the zed using shuffle-alert"
    (let [fred (atom z-22-2)]
      (add-watch fred :fred-shuffle-alert shuffle-alert)
      (swap! fred update-in [:percent-deteriorated] + 1)
      (swap! fred update-in [:hunger-level] + 30)))

  (testing "adding a validator to fred the zed using percent-deteriorated-validator"
    (let [fred (atom z-0-0 :validator percent-deteriorated-validator)]
      (is (thrown? java.lang.IllegalStateException
            (swap! fred update-in [:percent-deteriorated] + 200))))))
