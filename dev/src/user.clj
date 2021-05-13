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
     :verbose false}
    tutkain.cljs/*compiler-env*)

  (repl/repl (browser/repl-env)
    :watch "src"
    :output-dir "out"
    :need-prompt (constantly false)
    :prompt (constantly "")
    :print tutkain.repl/*print*
    :caught tutkain.repl/*caught*
    :compiler-env tutkain.cljs/*compiler-env*)
  ,)
