(ns second.core
  (:require clojure.string)
  (:import (java.io BufferedReader FileReader)))

(defn process-file [file-name]
  (let [countedwords {}]
    (with-open [rdr (BufferedReader. (FileReader. file-name))]
      (doseq [line (line-seq rdr)]
        (let [words (clojure.string/split line #" ")]
          (doseq [word words]
            (let [kw (keyword word)
                  kwcount (or (kw countedwords) 0)]
              (println kw kwcount))))))))

(process-file "README.md")
