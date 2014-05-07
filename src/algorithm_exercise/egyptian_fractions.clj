(ns algorithm-exercise.egyptian-fractions)

(defn egyptian-fractions [r]
  (loop [r r, fracs []]
    (if (integer? (/ r))
      (conj fracs r)
      (let [q (quot (denominator r) (numerator r))
            frac (/ (+ q 1))]
        (recur (- r frac)
               (conj fracs frac))))))
