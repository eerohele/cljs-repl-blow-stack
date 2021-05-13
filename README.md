# ClojureScript REPL issue repro

1. Start socket REPL:

   ```
   clj -J-Dclojure.server.repl="{:port 6666 :accept clojure.core.server/repl}" -A:dev
   ```

1. Connect to socket REPL:

   ```
   nc localhost 6666
   ```

1. Build ClojureScript:

   ```
   (build/build "src"
     {:main 'my.app
      :output-to "out/main.js"
      :target :browser
      :verbose false})
   ```

1. Start REPL:

   ```
   (repl/repl (browser/repl-env :launch-browser false)
     :watch "src"
     :output-dir "out"
     ;; Make sure we get a stacktrace.
     :caught (fn [ex _ _]
               ((requiring-resolve 'clojure.pprint/pprint) (Throwable->map ex))))
   ```

1. Open browser and go to http://localhost:9000.

1. In the REPL, type `(ns my.app)` and press Enter.

   Output:

   ```
   ClojureScript 1.10.844
cljs.user=> (ns my.app)
{:via
 [{:type clojure.lang.ExceptionInfo,
   :message
   "Execution error (RangeError) at (<cljs repl>:1).\nMaximum call stack size exceeded\n",
   :data
   {:type :js-eval-exception,
    :error
    {:status :exception,
     :value
     "Execution error (RangeError) at (<cljs repl>:1).\nMaximum call stack size exceeded\n"},
    :repl-env
    {:es
     #object[java.util.concurrent.ThreadPoolExecutor 0x1be22bcf "java.util.concurrent.ThreadPoolExecutor@1be22bcf[Running, pool size = 16, active threads = 0, queued tasks = 0, completed tasks = 20]"],
     :browser-state
     #<Atom@3f7f60df:
       {:return-value-fn
        #object[cljs.repl.browser$browser_eval$fn__7820 0x6c469665 "cljs.repl.browser$browser_eval$fn__7820@6c469665"],
        :client-js
        #object[java.net.URL 0x4ce7e1ed "jar:file:/my/home/.m2/repository/org/clojure/clojurescript/1.10.844/clojurescript-1.10.844.jar!/brepl_client.js"],
        :closure-defines {}}>,
     :working-dir ".repl-1.10.844",
     :preloaded-libs [],
     :launch-browser false,
     :static-dir ["." "out/"],
     :src "src/",
     :port 9000,
     :ordering #<Agent@28282c73: {:expecting 4, :fns {}}>,
     :host "localhost",
     :server-state
     #<Atom@413fbb8a:
       {:socket
        #object[java.net.ServerSocket 0x3351995d "ServerSocket[addr=0.0.0.0/0.0.0.0,localport=9000]"],
        :listeners 1,
        :port 9000}>},
    :form (ns my.app),
    :js "goog.provide('my.app');\ngoog.require('cljs.core');\n"},
   :at [cljs.repl$evaluate_form invokeStatic "repl.cljc" 577]}],
 :trace
 [[cljs.repl$evaluate_form invokeStatic "repl.cljc" 577]
  [cljs.repl$evaluate_form invoke "repl.cljc" 498]
  [cljs.repl$eval_cljs invokeStatic "repl.cljc" 692]
  [cljs.repl$eval_cljs invoke "repl.cljc" 685]
  [cljs.repl$repl_STAR_$read_eval_print__6964 invoke "repl.cljc" 1162]
  [cljs.repl$repl_STAR_$fn__6970$fn__6982 invoke "repl.cljc" 1210]
  [cljs.repl$repl_STAR_$fn__6970 invoke "repl.cljc" 1209]
  [cljs.compiler$with_core_cljs invokeStatic "compiler.cljc" 1456]
  [cljs.compiler$with_core_cljs invoke "compiler.cljc" 1445]
  [cljs.repl$repl_STAR_ invokeStatic "repl.cljc" 1168]
  [cljs.repl$repl_STAR_ invoke "repl.cljc" 1033]
  [cljs.repl$repl invokeStatic "repl.cljc" 1296]
  [cljs.repl$repl doInvoke "repl.cljc" 1226]
  [clojure.lang.RestFn invoke "RestFn.java" 559]
  [user$eval1631 invokeStatic "NO_SOURCE_FILE" 6]
  [user$eval1631 invoke "NO_SOURCE_FILE" 6]
  [clojure.lang.Compiler eval "Compiler.java" 7181]
  [clojure.lang.Compiler eval "Compiler.java" 7136]
  [clojure.core$eval invokeStatic "core.clj" 3202]
  [clojure.core$eval invoke "core.clj" 3198]
  [clojure.main$repl$read_eval_print__9110$fn__9113
   invoke
   "main.clj"
   437]
  [clojure.main$repl$read_eval_print__9110 invoke "main.clj" 437]
  [clojure.main$repl$fn__9119 invoke "main.clj" 458]
  [clojure.main$repl invokeStatic "main.clj" 458]
  [clojure.core.server$repl invokeStatic "server.clj" 180]
  [clojure.core.server$repl invoke "server.clj" 180]
  [clojure.lang.AFn applyToHelper "AFn.java" 152]
  [clojure.lang.AFn applyTo "AFn.java" 144]
  [clojure.lang.Var applyTo "Var.java" 705]
  [clojure.core$apply invokeStatic "core.clj" 667]
  [clojure.core.server$accept_connection invokeStatic "server.clj" 73]
  [clojure.core.server$start_server$fn__8902$fn__8903$fn__8905
   invoke
   "server.clj"
   117]
  [clojure.lang.AFn run "AFn.java" 22]
  [java.lang.Thread run "Thread.java" 832]],
 :cause
 "Execution error (RangeError) at (<cljs repl>:1).\nMaximum call stack size exceeded\n",
 :data
 {:type :js-eval-exception,
  :error
  {:status :exception,
   :value
   "Execution error (RangeError) at (<cljs repl>:1).\nMaximum call stack size exceeded\n"},
  :repl-env
  {:es
   #object[java.util.concurrent.ThreadPoolExecutor 0x1be22bcf "java.util.concurrent.ThreadPoolExecutor@1be22bcf[Running, pool size = 16, active threads = 0, queued tasks = 0, completed tasks = 20]"],
   :browser-state
   #<Atom@3f7f60df:
     {:return-value-fn
      #object[cljs.repl.browser$browser_eval$fn__7820 0x6c469665 "cljs.repl.browser$browser_eval$fn__7820@6c469665"],
      :client-js
      #object[java.net.URL 0x4ce7e1ed "jar:file:/my/home/.m2/repository/org/clojure/clojurescript/1.10.844/clojurescript-1.10.844.jar!/brepl_client.js"],
      :closure-defines {}}>,
   :working-dir ".repl-1.10.844",
   :preloaded-libs [],
   :launch-browser false,
   :static-dir ["." "out/"],
   :src "src/",
   :port 9000,
   :ordering #<Agent@28282c73: {:expecting 4, :fns {}}>,
   :host "localhost",
   :server-state
   #<Atom@413fbb8a:
     {:socket
      #object[java.net.ServerSocket 0x3351995d "ServerSocket[addr=0.0.0.0/0.0.0.0,localport=9000]"],
      :listeners 1,
      :port 9000}>},
  :form (ns my.app),
  :js "goog.provide('my.app');\ngoog.require('cljs.core');\n"}}
   ```
