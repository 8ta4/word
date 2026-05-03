(ns main)

(defonce state
  (atom nil))

(defn style
  [index])

(defn main
  [plugin]
  (reset! state plugin)
  (.registerFunction plugin "Style" style))