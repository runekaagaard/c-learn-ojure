(defn any? [pred col] 
    (not (not-any? pred col)))

(defn is-mult-of [n col]
    (any? true?
        (map #(zero? (mod n %)) col)))

(println 
    (apply + 
        (filter #(is-mult-of % [3 5]) (range 3 1000))))
