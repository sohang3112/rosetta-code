; http://rosettacode.org/wiki/Abbreviations,_automatic

(ns abbreviations-automatic
  (:require [clojure.java.io :as io]))

(def script-dir (.getParent (java.io.File. *file*)))

(def days-of-week 
  (with-open [reader (io/reader (io/file script-dir
                                         "days-of-the-week.txt"))]
    (vec (for [line (line-seq reader)]
           (.split line "\\s+")))))


(require '[clojure.test :refer [is are deftest run-tests]])
