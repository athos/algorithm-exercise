(ns algorithm-exercise.egyptian-fractions-test
  (:require [algorithm-exercise.egyptian-fractions :refer :all]
            [clojure.test :refer [deftest is]]))

(deftest test-egyptian-fractions
  (is (= (egyptian-fractions 2/5)
         [1/3 1/15])))
