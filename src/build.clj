(ns build
  (:require [clojure.java.shell :refer [sh]]
            [clojure.string :as string]
            [babashka.fs :refer [expand-home file]]))

(defn configure-plugin
  []
  (->> "DEVENV_ROOT"
       System/getenv
       (string/replace (slurp "template.lua") "{{dir}}")
       (spit (file (expand-home "~/.config/nvim/lua/plugins/devenv.lua")))))

(defn build
  {:shadow.build/stage :flush}
  [state]
  (println "Configuring plugin.")
  (configure-plugin)
  (println "Updating Neovim remote plugins.")
  (sh "nvim" "--headless" "+Lazy load word" "+UpdateRemotePlugins" "+qa!")
  state)