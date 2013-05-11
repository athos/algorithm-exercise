(ns algorithm-exercise.marriage-test
  (:use algorithm-exercise.marriage
        [clojure.test :only (deftest testing is)]))

(deftest test-solve
  (testing "`solve' provides a solution for each stable marriage problem"
    (is (= (solve {1 [1 3 2], 2 [2 3 1], 3 [3 1 2]}
                  {1 [2 3 1], 2 [2 1 3], 3 [3 2 1]})
           {1 1, 2 2, 3 3}))
    (is (= (solve {1 [:a :b :c :d], 2 [:c :b :a :d], 3 [:a :b :d :c], 4 [:c :a :d :b]}
                  {:a [1 2 3 4], :b [2 1 4 3], :c [2 3 1 4], :d [1 4 3 2]})
           {:a 1, :b 3, :c 2, :d 4}))))
