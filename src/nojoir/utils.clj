(ns nojoir.utils
  (:require [clj-yaml.core     :as yml])
  (:use     [clojure.data.json :only [json-str]]
            [clojure.data.xml  :only [emit-str sexp-as-element]]))

(def MAX_INT (. Integer MAX_VALUE)) ; for LIMIT

(def DB_CONF (yml/parse-string (slurp "config/db.yml")))

; RESPONSE TYPES
; .json
(defn json [col tbl]
  (json-str {tbl col}))

; .xml
(defn xml-ready [col tbl]
  (map #(identity
          [(symbol tbl) {}
            (map vector (keys %) (vals %))])
       col))

(defn xml [col tbl]
  (emit-str
    (sexp-as-element [:root (xml-ready col tbl)])))