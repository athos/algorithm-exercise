(ns algorithm-exercise.ishi1
  (:use [algorithm-exercise.common :only (prompt Game run-game)]))

(defrecord IshitoriGame [num max]
  Game
  (initial-state [this] num)
  (next-state [this strategy state]
    (loop []
      (let [x (strategy state max)]
        (if (<= 1 x (min state max))
          (- state x)
          (recur)))))
  (game-over? [this state]
    (= state 0)))

(defn AI-strategy [state max]
  (let [x (mod (- state 1) (+ max 1))]
    (if (= x 0) 1 x)))

(defn main []
  (let [num (prompt "石の数？")
        max (prompt "1回に取れる最大の石の数？")
        game (->IshitoriGame num max)
        player (fn [_ _] (prompt "何個取りますか？"))
        AI (fn [state max]
              (let [x (AI-strategy state max)]
                (println "私は %d 個の石を取ります．" x)
                x))
        logger (fn [s _] (printf "残りは%d個です．\n" s) (flush))]
    (let [winner (run-game game player AI logger)]
      (if (identical? winner player)
        (println "私の負けです！")
        (println "あなたの負けです！")))))
