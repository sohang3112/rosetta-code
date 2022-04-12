; Problem - http://rosettacode.org/wiki/Abbreviations,_easy

(ns rosetta
  (:require [clojure.test :refer :all]))

(defn words [str]
  "Split string into words"
  (.split str "\\s+"))

(defn join-words [strings]
  "Join words into a single string"
  (clojure.string/join " " strings))

(def cmd-table
  "Command Table - List of words to match against"
  (words
   "Add ALTer  BAckup Bottom  CAppend Change SCHANGE  CInsert CLAst COMPress COpy
COUnt COVerlay CURsor DELete CDelete Down DUPlicate Xedit EXPand EXTract Find
NFind NFINDUp NFUp CFind FINdup FUp FOrward GET Help HEXType Input POWerinput
Join SPlit SPLTJOIN  LOAD  Locate CLocate  LOWercase UPPercase  LPrefix MACRO
MErge MODify MOve MSG Next Overlay PARSE PREServe PURge PUT PUTD  Query  QUIT
READ  RECover REFRESH RENum REPeat  Replace CReplace  RESet  RESTore  RGTLEFT
RIght LEft  SAVE  SET SHift SI  SORT  SOS  STAck STATus  TOP TRAnsfer Type Up"))

; TODO - cache word properties
(defn abbr-valid?
  "Is abbr abbreviation of word?"
  [abbr word]
  (and (.startsWith (.toLowerCase word) (.toLowerCase abbr))
       (<= (count (filter #(Character/isUpperCase %) word))
           (count abbr)
           (count word))))

(defn find-word-for-abbr
  "Find first word matching abbreviation, or nil if not found"
  [abbr]
  (first (filter #(abbr-valid? abbr %) cmd-table)))

(defn solution
  "Find word matching each abbreviation in input (or *error* if not found),
   and join results into a string"
  [str]
  (join-words (for [abbr (words str)]
                (if-let [word (find-word-for-abbr abbr)]
                  (.toUpperCase word)
                  "*error*"))))

(print (solution "riG   rePEAT copies  put mo   rest    types   fup.    6       poweRin"))

(comment
  ;; Unit Tests

  (for [abbr ["ALT" "alt" "ALTE" "ALTER"]]
    (is (abbr-valid? abbr "ALTer")))
  (for [abbr ["AL", "ALF", "ALTERS", "TER", "A"]]
    (is (not (abbr-valid? abbr "ALTer"))))
  (for [abbr ["o" "ov" "oVe" "over" "overL" "overla"]]
    (is (abbr-valid? abbr "Overlay")))

  (is (= (solution "riG   rePEAT copies  put mo   rest    types   fup.    6       poweRin")
         "RIGHT REPEAT *error* PUT MOVE RESTORE *error* *error* *error* POWERINPUT")))