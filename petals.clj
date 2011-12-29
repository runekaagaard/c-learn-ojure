"
Plays Petals around the rose on the command line. See
   http://en.wikipedia.org/wiki/Petals_Around_the_Rose.

Run with
   java -cp ~/.clojure/clojure.jar clojure.main petals.clj
"

(require 'clojure.string)

(defn printfln [& args] (apply printf args) (println))

(defn prompt-read [prompt]
	(print (format "%s: " prompt))
	(flush)
	(read-line))

(def faces [
	["-----"
	 "|   |"
	 "| o |"
	 "|   |"
	 "-----"]

	["-----"
	 "|o  |"
	 "|   |"
	 "|  o|"
	 "-----"]

	["-----"
	 "|o  |"
	 "| o |"
	 "|  o|"
	 "-----"]

	["-----"
	 "|o o|"
	 "|   |"
	 "|o o|"
	 "-----"]

	["-----"
	 "|o o|"
	 "| o |"
	 "|o o|"
	 "-----"]

	["-----"
	 "|o o|"
	 "|o o|"
	 "|o o|"
	 "-----"]
])

(defn num-petals [n] (get (vector 0 0 2 0 4 0) n))
(defn face [n] (get faces n 1))
(defn throw-dice [] (repeatedly 5 #(rand-int 6)))


(defn format-faces [faces-nums]
	"Get face strings from faces-nums and aligns them horisontally."
	(map 
		#(clojure.string/join "  " %) 
		(apply map vector (map face faces-nums))))

(defn print-faces [faces-nums]
	"Prints the faces horisontally." 
	(println)
	(println (clojure.string/join "\n" (format-faces faces-nums))))

(defn parse-input [answer]
		"Get the users answer and returns whether it's correct."
		(if (= (prompt-read "Whats the answer to this roll?") (str answer))
			(do (println "Correct!") true)
			(do (printfln "Nope! The correct answer is %d." answer) false)))

(defn print-status [n]
	(if (> n 0)
		(printfln "%d correct answer(s) in a row." n))
	(if (> n 5)
		(println "Congratulations! You are now a Potentate of the Rose!")))

(defn correct-answer [faces]
	(reduce + (map num-petals faces)))

(defn ask-question [faces]
	(print-faces faces)
	(parse-input (correct-answer faces)))

(defn main [n]
	(print-status n)
	(recur (if (ask-question (throw-dice))
		(inc n) 
		0)))

(println 
"There are three rules:

    1) The name of the game is \"Petals Around the Rose\", 
       and the name of the game is the key to the game.
    2) The answer is always zero or an even number.
    3) Anyone who knows the game may give the answer to 
       any roll, but they must not disclose the reasoning.

Get six correct answers in a row to become a Potentate of the Rose.")

(main 0)