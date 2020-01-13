(ns second.core
  (:require [clojure.string :refer [split trim]]))

(defn cleanup-word [word]
  (keyword (trim (clojure.string/replace word #"[^a-zA-Z0-9]" ""))))

; needs more work, this will not suffice for exotic (key-)words
(defn shall-process [word]
  (not (empty? (name word))))

(defn count-words [all-words]
  (loop [word (first all-words)
         words (rest all-words)
         counted {}]
    (if (nil? word)
      counted
      (let [kw (cleanup-word word)
            amount (or (kw counted) 0)
            new-counted (if (shall-process kw)
                          (assoc counted kw (inc amount))
                          counted)]
        (recur (first words) (rest words) new-counted)))))

(defn count-words-from-file [file-name]
  (let [all-words (split (slurp file-name) #" ")]
    (count-words all-words)))

(count-words-from-file "README.md")

