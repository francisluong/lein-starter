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
           (validate order-details order-details-validations))))

  (testing "if-valid"
    (is (= ":failure {:email (\"Your email address doesn't look like an email address\")}"
           (if-valid order-details order-details-validations errors
              :success-code
              (str :failure " " errors)))))

  (testing "when-valid"
    (defn when-valid-case
      [to-validate validation-rules]
      (when-valid to-validate validation-rules
                  (println "It's a success!")
                  (println :success)
                  :success))
    (is (= nil (when-valid-case order-details order-details-validations)))
    (is (= :success (when-valid-case order-details-good order-details-validations))))

  (testing "fand"
    (is (= true (fand)))
    (is (= true (fand true)))
    (is (= false (fand false)))
    (is (= 1 (fand 1)))
    (is (= 3 (fand 1 1 1 3)))
    (is (= false (fand false 1 1 1 3)))
    (is (= nil (fand nil 1 1 1 3))))

  (testing "fif"
    (is (= true (fif)))
    (is (= true (fif true)))
    (is (= false (fif false)))
    (is (= false (fif nil false)))
    (is (= 1 (fif 1)))
    (is (= 1 (fif 1 1 1 3)))
    (is (= 1 (fif false 1 1 1 3)))
    (is (= 3 (fif false nil 3 1)))))
