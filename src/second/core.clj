(ns second.core
  (:require clojure.string)
  (:import (java.io BufferedReader FileReader)))

(defn count-words [file-name]
  (let [all-words (clojure.string/split (slurp file-name) #" ")]
    (loop [word (first all-words)
           words (rest all-words)
           counted {}]
      (println word)
      (if (empty? words)
        counted
        (let [kw (keyword word)
              amount (or (kw counted) 0)]
          (println kw amount)
          (recur (first words)
                 (rest words)
                 (assoc counted kw (inc amount))))))))

(count-words "README.md")


