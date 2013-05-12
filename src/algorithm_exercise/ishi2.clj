(ns algorithm-exercise.ishi2
  (:use [algorithm-exercise.common :only (prompt Game run-game)]))

(defrecord IshitoriGame [num]
  Game
  (initial-state [this] {:num num, :max (- num 1)})
  (next-state [this strategy {num :num max :max :as state}]
    (loop []
      (let [x (strategy state)]
        (if (<= 1 x (min num max))
          {:num (- num x), :max (* x 2)}
          (recur)))))
  (game-over? [this {num :num}]
    (= num 0)))

(defn ^:private fibs
  ([] (fibs 1 1))
  ([a b]
   (lazy-seq
     (cons a (fibs b (+ a b))))))

(defn ^:private to-sum-of-fibs [n]
  (let [fs (reverse (take-while #(<= % n) (fibs)))]
    (loop [n n, [f & fs' :as fs] fs, ns nil]
      (cond (= n f) (cons f ns)
            (> n f) (recur (- n f) fs' (cons f ns))
            :else (recur n fs' ns)))))

(defn AI-strategy [{num :num, max :max}]
  (let [[f] (to-sum-of-fibs num)]
    (if (> f max) 1 f)))

(defn main []
  (let [num (prompt "石の数？")
        game (->IshitoriGame num)
        player (fn [{max :max}]
                 (printf "%d 個まで取れます.\n" max)
                 (prompt "何個取りますか？"))
        AI (fn [{max :max :as state}]
             (printf "%d 個まで取れます.\n" max)
             (let [x (AI-strategy state)]
               (printf "私は %d 個の石を取ります.\n" x)
               x))
        logger (fn [{n :num} _] (printf "残りは %d 個です.\n" n) (flush))
        loser (run-game game player AI logger)]
    (if (identical? loser player)
      (println "あなたの負けです！")
      (println "私の負けです！"))))
