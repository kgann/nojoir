(ns nojoir.views.dal
  (:require [nojoir.views.common :as common]
            [nojoir.utils        :as util])
  (:use     [noir.core     :only [defpage]]
            [noir.response :only [content-type]]
            korma.db korma.core))

(defpage "/:conn/:db/:tbl.:format" {conn :conn db-name :db tbl :tbl fmt :format w :w f :f}
  (let [conn-fn      (ns-resolve 'korma.db      (symbol conn))
        response-fn  (ns-resolve 'nojoir.utils  (symbol fmt))]
    (defdb db
      (conn-fn {:db db-name :user "root" :password "temp!@#$"}))
    (content-type f
      (response-fn (select
                     tbl (where (if w
                                  w true))
                         (fields (if f
                                  f '*)))
        tbl))))