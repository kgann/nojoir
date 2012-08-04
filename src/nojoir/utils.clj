(ns nojoir.utils
  (:use [clojure.data.json :only [json-str]]
        [clojure.data.xml  :only [emit-str sexp-as-element]]))

(defn xml-ready [col tbl]
  (map #(identity
          ['tbl {}
          (map vector (keys %) (vals %))]) col))

(defn json [col tbl]
  (json-str {tbl col}))

(defn xml [col tbl]
  (emit-str
    (sexp-as-element [:root (xml-ready col tbl)])))