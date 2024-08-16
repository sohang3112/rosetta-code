; rosettacode.org/wiki/Merge_and_aggregate_datasets

(ns merge-aggregate-datasets)

(def nth '(- 5 4))
(def exprs '[(* 2 3) (- 5 2) (+ 7 2) (/ 20 2)])

(defmacro nth-expr [n & exprs]
  `(case ~n 
     ~@(mapcat list (range) exprs)
     (throw (Error. "Bad nth value!"))))

(nth-expr (- 2 1) (* 2 3) (- 5 2) (+ 7 2) (/ 20 2))