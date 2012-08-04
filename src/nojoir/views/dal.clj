(ns nojoir.views.dal
  (:require [nojoir.views.common :as common]
            [noir.content.getting-started]
            [nojoir.utils :as util])
  (:use [noir.core   :only [defpage]]
        [hiccup.core :only [html]]
        korma.db korma.core))

(defpage "/:conn/:db/:tbl.:format" {conn :conn db-name :db tbl :tbl f :format}
  (let [conn-fn     (ns-resolve 'korma.db     (symbol conn))
        response-fn (ns-resolve 'nojoir.utils (symbol f))]
    (defdb db
      (conn-fn {:db db-name :user "root" :password "temp!@#$"}))
    (response-fn
      (select tbl) tbl)))
