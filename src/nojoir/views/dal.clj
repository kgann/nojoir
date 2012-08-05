(ns nojoir.views.dal
  (:require [nojoir.views.common :as common]
            [nojoir.utils        :as util]
            [korma.core          :as sql])
  (:use     [noir.core     :only [defpage]]
            [noir.response :only [content-type]]
            korma.db))

(defpage "/:conn/:db-name/:table.:fmt"
         ; conn    - connection type (mysql, oracle, postgres)
         ; db-name - database name
         ; table   - table name
         ; fmt     - format (json, xml)
         ; ---- Query Params ----
         ; where   - where clause params ex: where[col]=val
         ; f       - select fields ex: f=col1,col2,col6
         ; limit
         ; offset 
         {:keys [conn db-name table fmt where f limit offset]
           :or  {where true, f '*, limit util/MAX_INT, offset 0}}
  (let [conn-fn      (ns-resolve 'korma.db      (symbol conn))
        response-fn  (ns-resolve 'nojoir.utils  (symbol fmt))
        user         (:user util/DB_CONF)
        pass         (:pass util/DB_CONF)]
    (defdb db
      (conn-fn {:db db-name :user user :password pass}))
    (content-type fmt
      (response-fn ; expects a result set and table name
        (sql/select table
          (sql/fields f)
            (sql/where where)
              (sql/limit limit)
                (sql/offset offset)) table))))