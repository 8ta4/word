(ns main
  (:require [cljs-node-io.core :refer [slurp]]
            [os :refer [homedir]]
            [path :refer [join]]))

(def api-key
  (-> (homedir)
      (join ".config/word/cerebras")
      slurp))

(defonce state
  (atom nil))

(defn style
  [index])

(defn main
  [plugin]
  (reset! state plugin)
  (.registerFunction plugin "Style" style))