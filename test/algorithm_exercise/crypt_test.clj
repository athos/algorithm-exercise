(ns algorithm-exercise.crypt-test
  (:use [clojure.test :only (deftest testing is)])
  (:require [algorithm-exercise.crypt :as crypt]))

(deftest test-encrypt
  (testing "Encrypting twice results in the same text as the input"
    (let [text "hoge"
          key 12345]
      (is (= text (crypt/encrypt (crypt/encrypt text key) key))))))
