(defproject org.clojars.slr71/diceware "0.0.1-SNAPSHOT"
  :description "A small utility to generate Diceware passwords."
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[clj-http "3.12.3"]
                 [org.clojure/clojure "1.10.3"]
                 [org.clojure/tools.cli "1.0.206"]]
  :main ^:skip-aot diceware.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]
                       :uberjar-name "diceware-standalone.jar"}})
