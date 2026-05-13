(ns core
  (:require [com.rpl.specter :as specter :refer [STOP]]))

(defn multi-path
  ([]
   STOP)
  ([& paths]
   (apply specter/multi-path paths)))
