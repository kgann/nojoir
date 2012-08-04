(ns nojoir.utils)

(defn xml-ready [col tbl]
  (map #(identity
          ['tbl {}
          (map vector (keys %) (vals %))]) col))