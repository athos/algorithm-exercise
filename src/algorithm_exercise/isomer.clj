(ns algorithm-exercise.isomer)

(defn ^:dynamic divide [n]
  (nth (for [i (range)
             j (range (inc i))
             k (range (inc j))]
         [i j k])
       (- n 1)))

(defn ^:dynamic size [n]
  (if (= n 0)
    0
    (let [[i j k] (divide n)]
      (+ (#'size i) (#'size j) (#'size k) 1))))

(defn ^:dynamic length [n]
  (if (= n 0)
    0
    (let [[i j k] (divide n)]
      (+ (length i) 1))))

(defmacro with-memoized [& body]
  `(binding [divide (memoize divide)
             size (memoize size)
             length (memoize length)]
     ~@body))

(defn count-isomers [s]
  (count (concat
          (for [i (range)
                :let [si (size i), li (length i)]
                :while (<= (* 2 li) s)
                j (range (inc i))
                :let [sj (size j), lj (length j)]
                :when (and (= li lj)
                           (= (+ si sj) s))]
            1)
          (for [i (range)
                :let [si (size i), li (length i)]
                :while (<= (* 2 li) s)
                j (range (inc i))
                :let [sj (size j), lj (length j)]
                :when (and (= li lj)
                           (< (+ si sj) s))
                k (range (inc j))
                :let [sk (size k), lk (length k)]
                :when (< (+ si sj sk) s)
                h (range (inc k))
                :let [sh (size h), lh (length h)]
                :when (= (+ si sj sk sh 1) s)]
            1))))
