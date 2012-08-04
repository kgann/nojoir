(ns nojoir.views.dal
  (:require [nojoir.views.common :as common]
            [noir.content.getting-started]
            [nojoir.utils :as util])
  (:use [noir.core   :only [defpage]]
        [hiccup.core :only [html]]
        korma.db korma.core))

(defpage "/:conn/:db/:tbl.:format" {conn :conn db-name :db tbl :tbl f :format}
  (defdb db
    (mysql {:db db-name :user "root" :password "temp!@#$"}))
  ;call response type method from utils
  ;need to resolve from utils namespace
  ((ns-resolve 'nojoir.utils (symbol f))
    (select tbl) tbl))
