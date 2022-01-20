; http://www.rosettacode.org/wiki/Write_float_arrays_to_a_text_file
(ns rosetta :gen-class)

(def x [1 2 3 1e11])
(def y (map #(Math/sqrt %) x))

(defn -main
  "Some IO here..."
  (println "Cleanliness is next!!"))
