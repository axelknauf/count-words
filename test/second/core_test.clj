(ns second.core-test
  (:require [clojure.string :refer [split]]
            [clojure.test :refer :all]
            [second.core :refer :all]))

(deftest test-count-words
  (testing "count-words does counts words correctly"
    (let [words (split "foo bar baz foo bar foo" #" ")
          actual (count-words words)]
      (is (= (:foo actual) 3))
      (is (= (:bar actual) 2))
      (is (= (:baz actual) 1)))))

(deftest test-words-get-trimmed
  (testing "words are trimmed before counting")
  (let [words ["foo
               " "bar " "	baz"]
        actual (count-words words)]
    (is (= (:foo actual) 1))
    (is (= (:bar actual) 1))
    (is (= (:baz actual) 1))))

(deftest does-not-count-empty-words
  (testing "does not count empty words")
  (let [words ["foo" "" "baz"]
        actual (count-words words)]
    (is (= (count actual) 2))
    (is (= (:foo actual) 1))
    (is (= (:baz actual) 1))))

(deftest removes-surrounding-interpunctation
  (testing "removes leading and trailing interpunctation")
  (let [words [".foo" "bar;" "';baz ?!"]
        actual (count-words words)]
    (is (= (:foo actual) 1))
    (is (= (:bar actual) 1))
    (is (= (:baz actual) 1))))

