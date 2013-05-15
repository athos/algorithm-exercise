(ns algorithm-exercise.isomer-test
  (:use algorithm-exercise.isomer
        [clojure.test :only (deftest testing is)]))

(deftest test-count-isomers
  (testing "count-isomers counts the number of isomers"
    (with-memoized
      (is (= (count-isomers 1) 1))
      (is (= (count-isomers 2) 1))
      (is (= (count-isomers 3) 1))
      (is (= (count-isomers 4) 2))
      (is (= (count-isomers 5) 3))
      (is (= (count-isomers 6) 5))
      (is (= (count-isomers 7) 9)))))
