; (:require [clojure.math.numeric-tower :as math]
;           [clojure.math.combinatorics :as combo])

; http://clojure-doc.org/articles/tutorials/getting_started.html

(ns something.core
  (:gen-class))


; basics : square and cube function
(defn square
  "Calculate area of a perimeter for example"
  [x] (* x x))

(defn cube [x] (* x x x))

(square 30)
(cube 30)

(doc square) ;for some reason it throws an error instead of showing docstring ? WHY ?
; (square (if true 3 0)) this doesn't work in clojure. duuh

(defn setmood [x y]
  "this is the docstring somethimes used for descriptions and for produting automatic documentation"
  {:added "1.2"
   :static true} ; how do I retrieve this docstring map info ?
  (str "the mood is"
    (when (> x y) " great")
    (when (< x y) " OK'ish")
    (when (= x y) " meh")))

(setmood 11 12)

(doc setmood) ;why is this not working ?



; Not working. It's conflictiong with something
; I added the dependencies but repl throws err and won't start.
(Math/Pi 3)

;
; (defn error-message
;   [severity]
;   (str "OH GOD! IT'S A DISASTER! "
;     (when (= severity :mild)
;       (do
;         (println "experimentation")
;         (println "something else")
;         "MILDLY INCONVENIENCED!"))
;
;     (when (= severity :grave)
;       "DOOOOOOOMED!")
;     (when (= severity :nothing)
;       "your mum's a bitch!")))
;
; ; (error-message :mild)
;
; (and :dogs :dogs)
;

; using other functions inside functions
(defn formative
  [x y]
  (if (> (cube x) (square y))
    "x is bigger"))

;
(nil? (formative 4 3))
;

; 'or' returns first truthy or last value
(or "sex" nil nil (= 3 1))
(or nil nil false 0)

; 'and' returns first falsey or the last truthy value
(and "bingo" "dingo" "option 1")

(defn functionalerror
  [errtype]
  ;(def problems [:grave :mild :fake])
  (str "yo Dawg I heard you feel"
    (when (= errtype :grave) " grave")
    (when (= errtype :mild) " pas grave")
    (when (= errtype :fake) " bastard")
    ; when none of the above than Dawg is emo.
    ; operator '!=' not good, how do I get it to work ?
    (when (= errtype (!= :fake :mild :grave)) " emo")))

(functionalerror :grave)

(if (= 1 4)
  (do
    ; (str "works better when alone")
    "once upon a time"
    "notworks"))

(range 1 6)

;playing with vectors
(def fructe ["portocale" "mere" "pere" "struguri" "alune"])
(fructe 1)

(defn burger
  [a]
  ; (def fructe ["portocale" "mere" "pere" "struguri" "alune"])
  ; (println (fructe 0)) ; se pare ca nu pot sa folosesc fructe in burger ?
  (str "something smells fishy it must be some " a))

(burger 3)


; MAPS
{:nume "Nedelcu" :prenume "Marius"}
{:name {:first "John" :middle "Jacob" :last "Jingleheimerschmidt"}}
({:a "doing nothing" :b {:x "yes" :y "no"}} :a)

(get (hash-map :a 1 :b 2 :c 3) :c)
(get-in {:a 1 :b {:x 10 :y {:z "a" :q "n"}} :c 3 :d 4} [:b :y :z])
(get {:a 0 :b {:c "ho hum"}} :b)

(:a {:a 1 :b 2 :c 3})

(get [3 2 1] 0)
(get (vector "mere" "pere" "prune" "pepene" "salata") 4)
(conj (vector "mere" "pere" "prune" "pepene" "salata") "idei")
(nth '(1 2 3 4 5) 3)

; elements are added at the beginning of a list.
(conj (list 1 2 4 5 6 "pere") "sex")

#{"kurt vonnegut" 20 :icicle}; this one works only with an even number of elements oO ?

(hash-set 1 2 3 4 5 5 2 1 1 3 9)
(conj #{1 2 3 4 5 5 2 1 1 3 9 5} 99); this one doesn't work (RuntimeException Unmatched delimiter ? WTF)
(conj (hash-set 1 2 3 4 5 5 2 1 1 3 9 5) 99); this one works
(conj (set [1 2 3 4 5 5 2 1 1 3 9 5]) 99)
(contains? (set [1 2 3 4 5 5 2 1 1 3 9 5]) 0)

(:a #{:a "space" :b}) ;you can't get space here
(get #{:a "space" :b} "space") ;but here you can

; surprisingly this exists in cj
(first [1 2 3 4])
(second [1 2 3 4])
(last [1 2 3 4])


(and + -) ; weird use of `and` & `or` like a bosss.
(inc ((and * (if true + -)) 2 5))
(inc 4)

(defn foo [x y] (+ x y))
(foo 3 5)

; Functions with aritiy and stuff
(defn cartofiprajiti
  ([a] (str "un cartof" (if (> a 10) " mare" " mic")))
  ([a b] (str "doi cartofi" (if (> (+ a b) 10) " mari" " mici"))))

(cartofiprajiti 2 9)


; More playing with Functions,
; I'd like some advice on what
; is good indentation practice in clj
(defn LeeKick
  ([name]
   (str "Bruce kicks " name))
  ([name kick1]
   (str "Bruce greets " name " with a " kick1))
  ([name kick1 kick2]
   (str "Bruce greets " name " with a " kick1 " and a " kick2)))

; terminate this people
(LeeKick "Glenn Jones" "slap" "leg")
(LeeKick "Kanye West" "slap")
(LeeKick "Julie")


(defn weird-arity
  ([
     "Destiny dressed you this morning, my friend, and now Fear is
     trying to pull off your pants. If you give up, if you give in,
     you're gonna end up naked with Fear just standing there laughing
     at your dangling unmentionables! - the Tick"])
  ([number
     (inc number)]))

(weird-arity nil); this doesn't work. why ?


(defn thankyou
  [mentor]
  (str "Hi " mentor " You've done so much for me !"))

(defn gratitude
  [& mentors]
  (do
    (map thankyou mentors)
    ; I don't understand : since both expressions are within `do`
    ; why it executes only the last one and not both
    (str "test")))

(gratitude "Arnaud" "Elise" "Someone")

; more playing with functions
(defn favorite-things
  [firstname name & things]
  (str "Hi, " firstname name ", here are my favorite things: "
    (clojure.string/join ", " things)))

(favorite-things "John" "Doe" "bitches" "weed")

;; Return the first element of a collection
(defn my-first
  [[first-thing]] ; Notice that first-thing is within a vector
  first-thing)

(my-first ["oven" "bike" "warior axe"])

(defn chooser
  [[first-choice second-choice & unimportant-choices]]
  (println (str "Your first choice is: " first-choice))
  (println (str "Your second choice is: " second-choice))
  (println (str "We're ignoring the rest of your choices. "
                "Here they are in case you need to cry over them: "
                (clojure.string/join ", " unimportant-choices))))

(chooser ["Marmalade", "Handsome Jack", "Pigpen", "Aquaman"])

(defn choices
  [[a b c & d]]
  (str "you choose " a
    " you choose " b
    " you choose " c
    " you can't choose : "
      (clojure.string/join ", " d)))

(choices ["sex" "drugs" "weed" "Maths PHD" "Samba" "panini" "tuxedo"])


; This is actually better than spreadsheets ! I love clojure

(defn financials
  [a & x]
  (let [spendings (+ 33 16 180 30) revenue (apply + x) taxes (* revenue (* a 0.01)) profit (- revenue taxes spendings)]
    (println (str "your tax is " a " % on revenue"))
    (println (str "your expenses are " spendings " € + " taxes " € taxes making a total of " (+ spendings taxes) " € "))
    (println (str "your revenue is " revenue " €"))
    (println (str "your profit is " profit " €"))))

(financials 3 320 2800 480 900)




; Failed attempts using an external map
;

(def operations [{:tax      "3"
                  :revenue  "say"
                  :profit   "relax"
                  :calc     (+ 1 2)}])

;
(defn myfinance [x & y]
  (->> operations
       (map :calc)
       (interpose ", ")
       (reduce str)))

(myfinance operations)

;
;
;
;
;
;
;
;
;
