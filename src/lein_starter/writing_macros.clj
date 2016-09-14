(ns lein-starter.writing-macros)

(def my-name "writing-macros")

(def order-details
  {:name "Mitchard Blimmons"
   :email "michard.blimmonsgmail.com"})


(def order-details-good
  {:name "Mitchard Blimmons"
   :email "michard.blimmons@gmail.com"})

(def order-details-validations
  {:name ["Please enter a name" not-empty]
   :email ["Please enter an email address"
           not-empty
           "Your email address doesn't look like an email address"
           #(or (empty? %) (re-seq #"@" %))]})

(defn error-messages-for
  "Return a seq of error messages"
  [to-validate message-validator-pairs]
  (map first (filter #(not ((second %) to-validate))
                     (partition 2 message-validator-pairs))))

(defn validate
  "Returns a map with a vector of errors for each key"
  [to-validate validations]
  (reduce (fn [errors validation]
            (let [[fieldname validation-check-groups] validation
                  value (get to-validate fieldname)
                  error-messages (error-messages-for value validation-check-groups)]
                (if (empty? error-messages)
                  errors
                  (assoc errors fieldname error-messages))))
          {} validations))

(defmacro if-valid
  "Handle validation"
  [to-validate validations errors-name & then-else]
  `(let [~errors-name (validate ~to-validate ~validations)]
      (if (empty? ~errors-name)
        ~@then-else)))

(defmacro when-valid
  "If validation passes do the following forms"
  [to-validate validations & do-if-valid]
  `(let [errors# (validate ~to-validate ~validations)]
      (if (empty? errors#)
        (do ~@do-if-valid)
        nil)))

(defmacro fand
  "Evaluates exprs one at a time, from left to right. If a form
  returns logical false (nil or false), fand returns that value and
  doesn't evaluate any of the other expressions, otherwise it returns
  the value of the last expr. (fand) returns true."
  {:added "1.0"}
  ([] true)
  ([x] x)
  ([x & next]
   `(let [and# ~x]
      (if and#
        (fand ~@next)
        and#))))

(defmacro fif
  "Evaluates exprs one at a time, from left to right.  If a form
  returns logical true, fif returns that value and doesn't evaluate
  any of the other expressions, otherwise it returns the value of the
  last expr.  (fif) returns true."
  ([] true)
  ([x] x)
  ([x & next]
   `(let [fif# ~x]
      (if fif#
        fif#
        (fif ~@next)))))
