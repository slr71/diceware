(ns diceware.core
  (:gen-class)
  (:require [clj-http.client :as http]
            [clojure.string :as string]
            [clojure.tools.cli :refer [parse-opts]]))

(defn- get-cli-options
  "Returns the data structure used to define the CLI options."
  []
  [["-w" "--num-words INTEGER" "The number of words to include in each generated password."
    :default 4
    :parse-fn #(Integer/parseInt %)
    :validate [#(<= 1 % 6) "Must be an integer between 1 and 6."]]
   ["-n" "--num-passwords INTEGER" "The number of passwords to generate."
    :default 1
    :parse-fn #(Integer/parseInt %)
    :validate [#(< 0 %) "Must be an integer greater than zero."]]
   ["-s" "--separator STRING" "The separator to insert between words."
    :default "*"]
   ["-u" "--word-list-url STRING" "The URL to the word list."
    :default "https://www.eff.org/files/2016/07/18/eff_large_wordlist.txt"]
   ["-h" "--help"]])

(defn- print-help
  "Prints the help message and exits"
  [summary errors]
  (println "Generates a Diceware password:")
  (println "\nOptions:")
  (println summary)
  (when errors
    (println "\nErrors:")
    (mapv (partial println "  - ") errors)
    (System/exit 1))
  (System/exit 0))

(defn- load-word-list
  "Loads the word list from the specified URL."
  [url]
  (as-> (http/get url) r
    (string/split (:body r) #"\n")
    (mapv #(string/split % #"\t") r)
    (into {} r)))

(defn- get-generator
  "Returns a password generator function."
  [{:keys [word-list-url num-words separator]}]
  (let [word-list (load-word-list word-list-url)
        key-len   (count (first (keys word-list)))
        roll      (fn [] (inc (rand-int 6)))
        gen-word  (fn [] (get word-list (apply str (take key-len (repeatedly roll)))))]
    (fn [] (string/join separator (take num-words (repeatedly gen-word))))))

(defn- generate-passwords
  "Generates passwords as specified by the options."
  [{:keys [num-passwords] :as options}]
  (mapv println (take num-passwords (repeatedly (get-generator options)))))

(defn -main
  "Generates a Diceware password."
  [& args]
  (let [{:keys [options arguments summary errors]} (parse-opts args (get-cli-options))]
    (when (or errors (:help options))
      (print-help summary errors))
    (generate-passwords options)))
