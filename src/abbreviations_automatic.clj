; http://rosettacode.org/wiki/Abbreviations,_automatic

(ns abbreviations-automatic
  (:require [clojure.java.io :as io]))

(def script-dir (.getParent (java.io.File. *file*)))

(def days-of-week
  (with-open [reader (io/reader (io/file script-dir
                                         "days-of-the-week.txt"))]
    (vec (for [line (line-seq reader)]
           (.split line "\\s+")))))

(defn min-abbr-size [days]
  nil)

(require '[clojure.test :refer [are deftest run-tests]])

(deftest test-min-abbr-size
  (are [ans days] (= (min-abbr-size days) ans)
    2    ["Sunday" "Monday" "Tuesday" "Wednesday" "Thursday" "Friday" "Saturday"]
    2    ["Sondag" "Maandag" "Dinsdag" "Woensdag" "Donderdag" "Vrydag" "Saterdag"]
    4    ["E_djelë" "E_hënë" "E_martë" "E_mërkurë" "E_enjte" "E_premte" "E_shtunë"]
    2    ["Ehud" "Segno" "Maksegno" "Erob" "Hamus" "Arbe" "Kedame"]
    5    ["Al_Ahad" "Al_Ithinin" "Al_Tholatha'a" "Al_Arbia'a" "Al_Kamis" "Al_Gomia'a" "Al_Sabit"]
    4    ["Guiragui" "Yergou_shapti" "Yerek_shapti" "Tchorek_shapti" "Hink_shapti" "Ourpat" "Shapat"]
    2    ["domingu" "llunes" "martes" "miércoles" "xueves" "vienres" "sábadu"]
    2    ["Bazar_gÜnÜ" "Birinci_gÜn" "Çkinci_gÜn" "ÜçÜncÜ_gÜn" "DÖrdÜncÜ_gÜn" "Bes,inci_gÜn" "Altòncò_gÜn"]
    6    ["Igande" "Astelehen" "Astearte" "Asteazken" "Ostegun" "Ostiral" "Larunbat"]
    4    ["Robi_bar" "Shom_bar" "Mongal_bar" "Budhh_bar" "BRihashpati_bar" "Shukro_bar" "Shoni_bar"]
    2    ["Nedjelja" "Ponedeljak" "Utorak" "Srijeda" "Cxetvrtak" "Petak" "Subota"]
    5    ["Disul" "Dilun" "Dimeurzh" "Dimerc'her" "Diriaou" "Digwener" "Disadorn"]
    2    ["nedelia" "ponedelnik" "vtornik" "sriada" "chetvartak" "petak" "sabota"]))

(run-tests)