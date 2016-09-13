(ns lein-starter.alchemy-test
 (:require [clojure.test :refer :all]
           [lein-starter.alchemy :refer :all]))

(deftest eval-test
  (testing "evaluation of list values"
    (let [add-list (list + 1 2)]
      (is (= 3 (eval add-list)))
      (is (= 13 (eval (concat add-list [10])))))))
      ; (eval (list 'def 'lucky-number (concat add-list [10])))
      ; (is (= 13 user/lucky-number)))))

(deftest reading-test
  (testing "reading"
    (is (= "To understand what recursion is, you must first understand recursion."
           (str "To understand what recursion is," " you must first understand recursion.")))
    (is (list? (read-string "(+ 1 2)")))
    (is (= '(+ 1 2) (read-string "(+ 1 2)")))
    (is (= '(:zagglewag + 1 2) (conj (read-string "(+ 1 2)") :zagglewag))))

 (deftest infix-test
   (testing "infix"
     (let [infix (read-string "(1 + 1)")]
       (is (= 2 (eval (list (second infix)
                            (first infix)
                            (last infix))))))
     (is (= 3 (infix-m (1 + 2)))))))

(deftest ignore-last-operand-test
  (testing "ignore-last-operand macro"
    (is (= 3 (ignore-last-operand (+ 1 2 10))))
    ; last operand is NEVER evaluated... so no output is printed
    (is (= 3 (ignore-last-operand (+ 1 2 (println "look at me!!!")))))))
    ; works in repl but not here
    ; (is (= '(+ 1 2) (macroexpand '(ignore-last-operand (+ 1 2 10)))))))

(deftest threading-stabby-macro
  (testing "threading-stabby-macro"
    (let [path "src/lein_starter/alchemy.clj"
          long (read-resource-long path)
          m (read-resource-m path)]
      (is (= long m)))))

(deftest backwards-test
  (testing "reversal of forms via def backwards"
    (is (= "I am backwards" (backwards (" backwards" " am" "I" str))))))

;; syntax highlights are pretty horked after the backwards bit
