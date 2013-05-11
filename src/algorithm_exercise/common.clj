(ns algorithm-exercise.common)

(defn prompt [msg]
  (printf "%s " msg)
  (flush)
  (read))

(defprotocol Game
  (initial-state [this])
  (next-state [this strategy state])
  (game-over? [this state]))

(defn run-game [game player AI logger]
  (let [init (initial-state game)]
   (loop [state init, [turn & next] (cycle [player AI])]
     (if (game-over? game state)
       turn
       (let [state' (next-state game turn state)]
         (logger state' turn)
         (recur state' next))))))
