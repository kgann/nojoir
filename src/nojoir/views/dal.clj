(ns nojoir.views.dal
  (:require [nojoir.views.common :as common]
            [noir.content.getting-started]
            [nojoir.utils :as util])
  (:use [noir.core   :only [defpage]]
        [hiccup.core :only [html]]
        [clojure.data.json :only (json-str)]
        clojure.data.xml
        korma.db korma.core))

(defn json [col tbl]
  (json-str {tbl col}))

(defn xml [col tbl]
  (emit-str
    (sexp-as-element [:root (util/xml-ready col tbl)])))

(defpage "/:conn/:db/:tbl.:format" {conn :conn db-name :db tbl :tbl f :format}
  (defdb db
    (mysql {:db db-name :user "root" :password "temp!@#$"}))
  ((ns-resolve 'nojoir.views.dal (symbol f)) ; call response type method
    (select tbl) tbl))
