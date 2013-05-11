(ns algorithm-exercise.ishi1)

(defn step [state strategy]
  (strategy state))

(defn prompt [msg]
  (printf "%s " msg)
  (flush)
  (read))

(defn make-player [{max :max}]
  (fn [n]
    (let [x (prompt "何個取りますか？")]
      (if (and (< 0 x (inc max))
               (<= x n))
        (- n x)
        (recur n)))))

(defn make-AI [{max :max}]
  (fn [n]
    (let [x (mod (- n 1) (+ max 1))
          x (if (= x 0) 1 x)]
      (println "私は %d 個の石を取ります．" x)
      (- n x))))

(defn game-over? [n]
  (= n 0))

(defn run [player AI init logger]
  (loop [state init, [turn & next] (cycle [player AI])]
    (if (game-over? state)
      turn
      (let [state' (step state turn)]
        (logger state' turn)
        (recur state' next)))))

(defn make-new-game []
  (let [num (prompt "石の数？")
        max (prompt "1回に取れる最大の石の数？")]
    {:num num, :max max}))

(defn make-initial-state [{num :num}]
  num)

(defn main []
  (let [game (make-new-game)
        player (make-player game)
        AI (make-AI game)
        init (make-initial-state game)
        logger (fn [s _] (printf "残りは%d個です．\n" s) (flush))]
    (let [result (run player AI init logger)]
      (if (identical? result player)
        (println "私の負けです！")
        (println "あなたの負けです！")))))
