(ns lein-starter.alchemy)

(defmacro backwards
  [form]
  (reverse form))

(defmacro ignore-last-operand
  [function-call]
  (butlast function-call))

(defmacro infix-m
  [infix]
  (list (second infix)
        (first infix)
        (last infix)))

(defn read-resource-m
  [path]
  (-> path
      slurp
      read-string))

(defn read-resource-long
  "Read a resource into a string"
  [path]
  (read-string (slurp path)))
