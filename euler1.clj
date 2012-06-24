(defn any? [pred col] 
    (not (not-any? pred col)))

(defn is-mult-of [n col]
    (any? true?
        (map #(zero? (mod n %)) col)))

(defn sum-of-multiples [n col]
    (apply + 
        (filter #(is-mult-of % col) (range n))))

(println (sum-of-multiples 1000 [3 5]))
