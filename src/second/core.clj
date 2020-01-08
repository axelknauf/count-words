(ns second.core
  (:require clojure.string)
  (:import (java.io BufferedReader FileReader)))

(defn process-file [file-name]
  (let [words {}]
    (with-open [rdr (BufferedReader. (FileReader. file-name))]
      (doseq [line (line-seq rdr)]
        (let [words (clojure.string/split line #" ")]
          (println words))))))

(process-file "README.md")
