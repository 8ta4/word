(ns main
  (:require [cljs-node-io.core :refer [slurp]]
            [os :refer [homedir]]
            [path :refer [join]]
            [promesa.core :as promesa]))

(def api-key
  (-> (homedir)
      (join ".config/word/cerebras")
      slurp))

(defonce state
  (atom nil))

(defn get-selection-bounds
  []
  (promesa/let [positions (promesa/all (map #(.nvim.callFunction @state "getpos" %) ["." "v"]))]
    (sort (map (comp vec
                     drop-last
                     rest
                     js->clj)
               positions))))

(defn style
  [index])

(defn main
  [plugin]
  (reset! state plugin)
  (.registerFunction plugin "Style" style))