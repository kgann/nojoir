(ns nojoir.views.dal
  (:require [nojoir.views.common :as common]
            [nojoir.utils        :as util])
  (:use     [noir.core     :only [defpage]]
            [noir.response :only [content-type]]
            korma.db korma.core))

(def MAX_INT (. Integer MAX_VALUE)) ; for LIMIT

(defpage "/:conn/:db-name/:tbl.:fmt"
         ; conn    - connection type (mysql, oracle, postgres)
         ; db-name - database name
         ; tbl     - table name
         ; fmt     - format (json, xml)
         ; ---- Query Params ----
         ; w       - where clause params ex: w[col]=val
         ; f       - select fields ex: f=col1,col2,col6
         ; limit
         ; offset
         {:keys [conn db-name tbl fmt w f limit offset]
           :or  {w true, f '*, limit MAX_INT, offset 0}}
  (let [conn-fn      (ns-resolve 'korma.db      (symbol conn))
        response-fn  (ns-resolve 'nojoir.utils  (symbol fmt))]
    (defdb db
      (conn-fn {:db db-name :user "root" :password "temp!@#$"}))
    (content-type fmt
      (response-fn ; expects a result set and table name
        (select tbl (where w) (fields f) (limit l) (offset o)) tbl))))