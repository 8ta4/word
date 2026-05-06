(ns main
  (:require [cljs-node-io.core :refer [slurp]]
            [os :refer [homedir]]
            [path :refer [join]]
            [promesa.core :as promesa :refer [all]]))

(def api-key
  (-> (homedir)
      (join ".config/word/cerebras")
      slurp))

(defonce state
  (atom {}))

(defn get-selection-bounds
  []
  (promesa/let [positions (all (map #(.callFunction (:nvim @state) "getpos" %) ["." "v"]))]
    (sort (map (comp vec
                     (partial map dec)
                     drop-last
                     rest
                     js->clj)
               positions))))

(defn get-sentences
  [start-pos end-pos]
  (promesa/let [start-sentence (.callFunction (:nvim @state) "Get" (clj->js {:pos start-pos}))]
    (if (js->clj start-sentence)
      (promesa/let [end-sentence* (.callFunction (:nvim @state) "Get" (clj->js {:pos end-pos}))
                    end-sentence (if (js->clj end-sentence*)
                                   (js->clj end-sentence*)
                                   (.callFunction (:nvim @state) "Get" (clj->js {:offset -1
                                                                                 :pos end-pos})))]
        (if (= (js->clj start-sentence) (js->clj end-sentence))
          [(js->clj start-sentence)]
          (promesa/loop [sentences [(js->clj end-sentence)]]
            (promesa/let [previous-sentence (.callFunction (:nvim @state) "Get" (clj->js {:offset -1
                                                                                          :pos (drop-last (first sentences))}))]
              (if (= (js->clj start-sentence) (js->clj previous-sentence))
                (cons (js->clj start-sentence) sentences)
                (promesa/recur (cons (js->clj previous-sentence) sentences)))))))
      [])))

(defn style
  [index])

(defn set-sentence-extmark
  [[row start-col end-col]]
  (.request (:nvim @state) "nvim_buf_set_extmark" (clj->js [0
                                                            (:namespace @state)
                                                            row
                                                            start-col
                                                            {:end_col end-col
                                                             :end_row row}])))

(defn set-sentence-extmarks
  [sentences]
  (all (map set-sentence-extmark sentences)))

(defn prepend
  [sentences]
  (promesa/let [previous-sentence (.callFunction (:nvim @state) "Get" (clj->js {:offset -1
                                                                                :pos (drop-last (first sentences))}))]
    (cons (or (js->clj previous-sentence) [0 0 0]) sentences)))

(defn set-range-extmark
  [[previous-sentence current-sentence]]
  (.request (:nvim @state) "nvim_buf_set_extmark" (clj->js [0
                                                            (:namespace @state)
                                                            (first previous-sentence)
                                                            (last previous-sentence)
                                                            {:end_col (last current-sentence)
                                                             :end_row (first current-sentence)}])))

(defn set-range-extmarks
  [sentences]
  (promesa/let [sentences* (prepend sentences)]
    (all (map set-range-extmark (partition 2 1 sentences*)))))

(defn suggest
  [])

(defn main
  [plugin]
  (promesa/let [namespace (.createNamespace (.-nvim plugin) "word")]
    (reset! state {:nvim (.-nvim plugin)
                   :namespace namespace}))
  (.registerFunction plugin "Style" style)
  (.registerFunction plugin "Suggest" suggest))