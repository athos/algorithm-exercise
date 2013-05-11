(ns algorithm-exercise.ishi1-test
  (:use algorithm-exercise.ishi1
        [clojure.test :only (deftest testing is)]))

(deftest test-run
  (testing ""
    (let [num 10, max 3, log (atom [])]
      (letfn [(player [n] (- n 1))
              (AI [n]
                (let [x (mod (- n 1) (+ max 1))
                      x (if (= x 0) 1 x)]
                  (- n x)))
              (logger [state _]
                (println state)
                (swap! log conj state))]
        (is (identical?  (run player AI num logger)
                         AI))
        (is (= @log [9 8 7 5 4 1 0]))))))
