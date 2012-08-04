(ns nojoir.views.dal
  (:require [nojoir.views.common :as common]
            [nojoir.utils        :as util])
  (:use     [noir.core     :only [defpage]]
            [noir.response :only [content-type]]
            korma.db korma.core))

(defpage "/:conn/:db-name/:tbl.:fmt"
         {:keys [conn db-name tbl fmt w f] :or {w true f '*}}
  (let [conn-fn      (ns-resolve 'korma.db      (symbol conn))
        response-fn  (ns-resolve 'nojoir.utils  (symbol fmt))]
    (defdb db
      (conn-fn {:db db-name :user "root" :password "temp!@#$"}))
    (content-type fmt
      (response-fn ; expects a result set and table name
        (select tbl (where w)
                    (fields f))
        tbl))))