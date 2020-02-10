(ns inspect.core
  (:require
   [inspect.html-dump-style :as html-style]
   [inspect.row-dump-style :as row-style]
   [clojure.pprint :as pp]
   [clojure.walk :as walk]
   [clojure.string :as s])
  (:gen-class))

(def postfix
  {:vector "]"
   :list ")"
   :set "}"})

(def prefix
  {:vector "["
   :list "("
   :set "#{"})


(comment
  "super slow" "Elapsed time: 2.47274 msecs"
  (time (dotimes [n 5000] (build-map-old {:test 1 :gg 1 :1 2 :2 2 :3 3 :4 4})))
  (time (dotimes [n 5000] (build-map {:test 1 :gg 1 :1 2 :2 2 :3 3 :4 4})))
  ())


(comment
  "Elapsed time: 1276.493252 msecs"
  (time (dotimes [n 100] (build-seq :list (range 1 1000))))
  ())

        
(defn build-seq [type data]
  (let [sp "<span class='sp'> </span>"
        newline "<span class='sp'> \n</span>"
        data (s/join sp data)
        element (name type)]
    (s/join ["<div class='" element "-block'>"
             "<span class='" element "-start'>" (type prefix) "</span>"
             sp data sp
             "<span class='" element "-end'>" (type postfix) "</span>"
             "</div>"])))

(comment
  ;; 79.587064 msecs
  (time (dotimes [n 10000] (build-map {:test 1 :gg 1 :1 2 :2 2 :3 3 :4 4})))
  ())


(defn build-map [data]
  (let [data (reduce
              (fn [ret [key val]]
                (s/join [ret
                         "<div class='map-row'>"
                         "<span class='map-key'>" key "</span>"
                         "<span class='map-value'>" val "</span>"
                         "</div>"]))
              "" data)]
            
          
    (s/join ["<div class='map-block'>"
            ;  "<span class='map-start'>{</span>"
             data
            ;  "<span class='map-end'>}</span>"
             "</div>"])))



(defn phtml [data]
  (cond
    (map-entry? data) data
    (vector? data) (build-seq :vector data)
    (list? data) (build-seq :list data)
    (set? data) (build-seq :set data)
    (map? data) (build-map data)
    (nil? data) "<span>nil<span>"
    (string? data) (pr-str data)
    :else data))


(defn lazy? [data]
  (= (class data) clojure.lang.LazySeq))
    
(defn expand-lazy [data]
  (if (not (lazy? data))
    data
    (binding [*out* (java.io.StringWriter.)]
      (pr data)
      (read-string (.toString *out*)))))

(defn wrap [style data]
  (s/join [style " \n<pre><code>" data "</code></pre>"]))

(defn dump [data]
  (->> (with-out-str (pp/pprint data))
       (wrap row-style/css)
       (spit "dump.html"))) 


(defn dump-html [data]
  (->> (expand-lazy data)
       (walk/postwalk phtml)
       (wrap html-style/css)
       (spit "dump.html")))


(comment
  #_(walk/postwalk phtml [1 2 3])

  (require 'inspect.core :reload)
  (ns inspect.core)

  (def data1 (read-string (slurp "resources/data1.edn")))
  (def data2 (read-string (slurp "resources/data2.edn")))

  (do
    (require '[inspect.core] :reload)
    (dump-html data1))
  ())
