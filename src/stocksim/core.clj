(ns stocksim.core
  (:use [stocksim.data]
        [incanter core charts stats]))

(defn step [price]
  (* price (- (rand) 0.5)))

(defn next-price [price]
  (+ price (step price)))
(view (line-chart
       (range 0 252)
       (take 252 (iterate next-price 1))))

(step 1)

(def sp500 (series "^GSPC"))
(def date (reverse (map first sp500)))
(def price (reverse (map last sp500)))
(def dr (into [] (map / (rest price) price)))
(mean dr)

(defn boot [price]
  (* price (rand-nth dr)))
(view (line-chart
       (range 0 252)
       (take 252 (iterate boot 1))))



(view (time-series-plot date price))

(view (histogram (map / (rest price) price)))

(view (time-series-plot
       (rest date)
       dr))


(view (line-chart
       (range (count pp))
       pp))

