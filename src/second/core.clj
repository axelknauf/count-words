(ns second.core
  (:require [clojure.string :refer [split trim]]))

(defn cleanup-word [word]
  (keyword (trim word)))

(defn count-words [all-words]
  (loop [word (first all-words)
         words (rest all-words)
         counted {}]
    (if (nil? word)
      counted
      ; TODO extract into a preprocess function
      (let [kw (cleanup-word word)
            amount (or (kw counted) 0)]
        (recur (first words)
               (rest words)
               (assoc counted kw (inc amount)))))))

(defn count-words-from-file [file-name]
  (let [all-words (split (slurp file-name) #" ")]
    (count-words all-words)))

(count-words-from-file "README.md")

