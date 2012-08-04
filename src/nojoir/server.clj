(ns nojoir.server
  (:require [noir.server :as server]))

(server/load-views "src/nojoir/views/")

(defn -main [& args]
  (let [mode (keyword (or (first args) :dev))
        port (Integer. (get (System/getenv) "PORT" "8080"))]
    (server/start port {:mode mode :ns 'nojoir})))
