(ns lein-starter.fp-test
  (:require [clojure.test :refer :all]
    [lein-starter.fp :refer :all]))

(deftest rsum-test
  (testing "rsum - recursion to illustrate alternative to for/while"
    (is (= 20 (rsum [5 5 5 5])))
    (is (= 20 (rsum [1 2 3 4] 10)))))

(deftest cleanlol-test
  (testing "function composition - stacking a string of functions"
    (is (= "My boa constrictor is so sassy LOL!"
           (cleanlol "My boa constrictor is so sassy lol!  ")))))

(require '[clojure.string :as s])
(deftest comp-test
  (testing "composition"
    (is (= 10 (c-int character)))
    (is (= 4 (c-str character)))
    (is (= 5 (c-dex character)))
    (is (= 6 (spell-slots character)))
    (is (= 6 (spell-slots-comp character))))
  (testing "twocomp"
    (defn twocomp
      [f g]
      (fn [& args]
        (f (apply g args))))
    (def trimcap (twocomp s/capitalize s/trim))
    (is (= "Bob" (trimcap "bob  "))))
  (testing "optional args"
    (defn printarg
      [f & args]
      (println (str "[printarg] [" args "]"))
      args)
    (is (= [1 2] (printarg 0 1 2))))
  (testing "composition with any number functions"
    (defn fcomp
      [f & args]
      (if (empty? args)
        f ;; base case
        (let
          [g (first args)
           new-args (rest args)]
         (recur (fn [& zargs]
                  (f (apply g zargs))) new-args)))) ;; run anonymous function
    (def ftrimcap (fcomp s/upper-case s/lower-case s/triml s/trimr))
    (is (= "BOB" (ftrimcap "  bob  ")))))

(defmacro get-function-name
  [expr]
  (let [sym (= (type expr) clojure.lang.Symbol)]
    (str (:name (meta (if sym
                        (resolve expr)
                        (resolve (first expr))))))))
(defmacro ftime
  "Evaluates expr and prints the time it took.  Returns {time: x, return: y}"
  [expr]
  `(let [start# (. System (nanoTime))
         ret# ~expr
         fn# (get-function-name ~expr)]
     (def elapsedTime (/ (double (- (. System (nanoTime)) start#)) 1000000.0))
     (prn (str "Elapsed ftime for (" fn#  "): " elapsedTime " msecs"))
     {:time elapsedTime, :function fn#, :return ret#}))


(deftest memoize-test
  (testing "get-function-name"
    (is (= "+" (get-function-name (+ 2 2)))))
  (testing "sleepy-identity"
    (is (< 1 (:time (ftime (sleepy-identity 9)))))
    (is (< 1 (:time (ftime (sleepy-identity 9))))))
  (testing "memo-sleepy-identity"
    (ftime (memo-sleepy-identity 9)) ;; skip the first check
    (is (> 1 (:time (ftime (memo-sleepy-identity 9))))))) ;; subsequent should skip sleep


(deftest defattrs-test
  (testing "defattrs"
    (print "\n--=\n")
    (print (macroexpand `(defattrs f-int :intelligence
                                  f-str :strength
                                  f-dex :dexterity)))
    (print "\n=--\n")
    (defattrs f-int :intelligence
              f-str :strength
              f-dex :dexterity)
    (is (= 10 (f-int character)))
    (is (= 4 (f-str character)))
    (is (= 5 (f-dex character)))))
