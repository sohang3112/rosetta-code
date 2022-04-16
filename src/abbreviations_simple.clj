; https://rosettacode.org/wiki/Abbreviations,_simple

;; Notes concerning the above   command table:

;;   a command is followed by an optional number, which indicates the minimum abbreviation
;;   if there isn't a number after the command,   then there isn't an abbreviation permitted

;;   A valid abbreviation is a word that has:
;;   at least the minimum length of the word's minimum number in the command table
;;   compares equal (regardless of case) to the leading characters of the word in the command table
;;   a length not longer than the word in the command table

;; Task
;;   The command table needn't be verified/validated.
;;   Write a function to validate if the user "words"   (given as input)   are valid   (in the command table).
;;   If the word   is   valid,   then return the full uppercase version of that "word".
;;   If the word isn't valid,   then return the lowercase string:   *error*       (7 characters).
;;   A blank input   (or a null input)   should return a null string.
;;   Show all output here.

;; Tests
;;   ALT,   aLt,   ALTE,   and   ALTER   are all abbreviations of   ALTER 3
;;   AL,   ALF,   ALTERS,   TER,   and   A   aren't valid abbreviations of   ALTER 3
;;   The   3   indicates that any abbreviation for   ALTER   must be at least three characters
;;   Any word longer than five characters can't be an abbreviation for   ALTER
;;   o,   ov,   oVe,   over,   overL,   overla   are all acceptable abbreviations for   overlay 1


(ns abbreviations-simple)

(defn words
  "Split string into words"
  [^String str]
  (.split str "\\s+"))

(defn parse-cmd-table 
  "Parse list of strings in command table into list of words and numbers"
  [cmd-table]
  (loop [i 0,
         ans []]
    (let [cmd-count (count cmd-table)]
      (if (< i cmd-count)
        (let [word (nth cmd-table i),
              [num i] (try
                        [(Integer/parseInt ^String (nth cmd-table (inc i)))
                         (+ i 2)]
                        (catch NumberFormatException _
                          [1 (inc i)]))]
          (recur i 
                 (conj ans {:word word, :num num})))
        ans))))

(def cmd-table
  (->
   "add 1  alter 3  backup 2  bottom 1  Cappend 2  change 1  Schange  Cinsert 2  Clast 3
   compress 4 copy 2 count 3 Coverlay 3 cursor 3  delete 3 Cdelete 2  down 1  duplicate
   3 xEdit 1 expand 3 extract 3  find 1 Nfind 2 Nfindup 6 NfUP 3 Cfind 2 findUP 3 fUP 2
   forward 2  get  help 1 hexType 4  input 1 powerInput 3  join 1 split 2 spltJOIN load
   locate 1 Clocate 2 lowerCase 3 upperCase 3 Lprefix 2  macro  merge 2 modify 3 move 2
   msg  next 1 overlay 1 parse preserve 4 purge 3 put putD query 1 quit  read recover 3
   refresh renum 3 repeat 3 replace 1 Creplace 2 reset 3 restore 4 rgtLEFT right 2 left
   2  save  set  shift 2  si  sort  sos  stack 3 status 4 top  transfer 3  type 1  up 1"
   words 
   parse-cmd-table))




