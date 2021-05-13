(ns user
  (:require
   [cljs.build.api :as build]
   [cljs.repl :as repl]
   [cljs.repl.browser :as browser]))

(comment
  (build/build "src"
    {:main 'my.app
     :output-to "out/main.js"
     :target :browser
     :verbose false})

  (repl/repl (browser/repl-env :launch-browser false)
    :watch "src"
    :output-dir "out")
  ,)
