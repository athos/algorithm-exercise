(ns algorithm-exercise.ishi1-test
  (:use algorithm-exercise.ishi1
        [algorithm-exercise.common :only (run-game)]
        [clojure.test :only (deftest testing is)]))

(deftest test-AI-strategy
  (let [game (->IshitoriGame 10 3)
        log (atom [])
        player (fn [_ _] 1)
        logger (fn [state _] (swap! log conj state))]
    (is (identical?  (run-game game player AI-strategy logger)
                     AI-strategy))
    (is (= @log [9 8 7 5 4 1 0]))))
