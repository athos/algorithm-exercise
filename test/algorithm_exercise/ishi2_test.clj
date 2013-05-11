(ns algorithm-exercise.ishi2-test
  (:use algorithm-exercise.ishi2
        [algorithm-exercise.common :only (run-game)]
        [clojure.test :only (deftest testing is)]))

(deftest test-AI-strategy
  (let [game (->IshitoriGame 30)
        log (atom [])
        player (fn [{num :num max :max}]
                 (if (<= num max)
                   num
                   (min 10 max)))
        logger (fn [{num :num} _] (swap! log conj num))]
    (is (identical? (run-game game player AI-strategy logger)
                    player))
    (is (= @log [20 18 14 13 11 10 8 7 5 4 2 0]))))
