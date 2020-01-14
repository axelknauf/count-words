(ns second.core-test
  (:require [clojure.string :refer [split]]
            [clojure.test :refer :all]
            [second.core :refer :all]))

(deftest test-count-words

  (testing "counts words"
    (let [words (split "foo bar baz foo bar foo" #" ")
          actual (count-words words)]
      (is (= (:foo actual) 3))
      (is (= (:bar actual) 2))
      (is (= (:baz actual) 1))))

  (testing "words get trimmed")
    (let [words ["foo
                 " "bar " "	baz"]
          actual (count-words words)]
      (is (= (:foo actual) 1))
      (is (= (:bar actual) 1))
      (is (= (:baz actual) 1)))

  (testing "does not count empty words")
    (let [words ["foo" "" "baz"]
          actual (count-words words)]
      (is (= (count actual) 2))
      (is (= (:foo actual) 1))
      (is (= (:baz actual) 1)))

  (testing "removes interpunctation"
    (let [words [".foo" "bar;" "';baz ?!"]
          actual (count-words words)]
      (is (= (:foo actual) 1))
      (is (= (:bar actual) 1))
      (is (= (:baz actual) 1))))

  (testing "removes leading numbers"
    (let [words ["123foo" "99bar" "baz234"]
          actual (count-words words)]
      (is (= (:foo actual) 1))
      (is (= (:bar actual) 1))
      (is (= (:baz234 actual) 1))))

  (testing "removes embedded blanks"
    (let [words ["f oo" "ba  r" "b a z"]
          actual (count-words words)]
      (is (= (:foo actual) 1))
      (is (= (:bar actual) 1))
      (is (= (:baz actual) 1)))))

