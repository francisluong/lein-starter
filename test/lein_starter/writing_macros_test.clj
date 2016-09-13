(ns lein-starter.writing-macros-test
  (:require [clojure.test :refer :all]
            [lein-starter.writing-macros :refer :all]))

(deftest my-name-test
  (testing "x"
    (is (= "writing-macros" my-name))))

(deftest order-details-test
  (testing "error-messages-for"
    (is (= ["Please enter a name"]
           (error-messages-for "" (:name order-details-validations))))
    (is (= ["Please enter an email address"]
           (error-messages-for "" (:email order-details-validations))))
    (is (= ["Your email address doesn't look like an email address"]
           (error-messages-for (:email order-details)
                               (:email order-details-validations)))))
  (testing "validate"
    (is (= {:email ["Your email address doesn't look like an email address"]}
           (validate order-details order-details-validations)))))
