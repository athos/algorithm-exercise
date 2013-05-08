(ns algorithm-exercise.crypt
  (:import java.util.Random))

(defn encrypt [text ^long key]
  (let [^Random r (Random. key)]
    (->> text
         (map #(char (bit-xor (int %) (.nextInt r 256))))
         (apply str))))
