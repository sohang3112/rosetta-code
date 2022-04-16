(ns abbreviations-easy
  "Solution of http://rosettacode.org/wiki/Abbreviations,_easy
   Given a list of inputs, check if they are valid abbreviations using a
   command table containing a list of words.
   
   NOTE: Unit Tests are included in a rich comment at the end of the file")

; Warnings to improve performance
(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)

(defn words
  "Split string into words"
  [^String str]
  (.split str "\\s+"))

(defn join-words
  "Join words into a single string"
  ^String [strings]
  (String/join " " strings))

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

; TODO: cache word properties
(defn abbr-valid?
  "Is abbr abbreviation of word?"
  ^Boolean [^String abbr, ^String word]
  (and (.startsWith (.toLowerCase word) (.toLowerCase abbr))
       (<= (count (filter #(Character/isUpperCase ^char %) word))
           (count abbr)
           (count word))))

; TODO: how to specify return type as either String or nil ?
(defn find-word-for-abbr
  "Find first word matching abbreviation, or nil if not found"
  [^String abbr]
  (first (filter #(abbr-valid? abbr %) cmd-table)))

(defn solution
  "Find word matching each abbreviation in input (or *error* if not found),
   and join results into a string"
  ^String [^String str]
  (join-words (for [abbr (words str)]
                (if-let [word (find-word-for-abbr abbr)]
                  (.toUpperCase ^String word)
                  "*error*"))))

;; Example Input - Also prints total elapsed time at the end
(time (println (solution "riG   rePEAT copies  put mo   rest    types   fup.    6       poweRin")))

(comment
  ;; Unit Tests

  (require '[clojure.test :refer [is deftest run-tests]])

  ; TODO - how to specify type of variable arguments
  (defn is-multiple
    "Test predicate for all arguments"
    [pred & args]
    (doseq [arg args]
      (is (pred ^String arg))))

  (deftest test-abbr-valid
    (is-multiple #(abbr-valid? % "ALTer") "ALT" "alt" "ALTE" "ALTER")
    (is-multiple #(not (abbr-valid? % "ALTer")) "AL" "ALF" "ALTERS" "TER" "A")
    (is-multiple #(abbr-valid? % "Overlay") "o" "ov" "oVe" "over" "overL" "overla"))

  (deftest test-solution
    (is (= (solution "riG   rePEAT copies  put mo   rest    types   fup.    6       poweRin")
           "RIGHT REPEAT *error* PUT MOVE RESTORE *error* *error* *error* POWERINPUT")))

  (run-tests)
  
  )
