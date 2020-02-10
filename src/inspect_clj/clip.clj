(ns inspect.clip
  (:require [clojure.pprint :as pp])
  (:import [java.awt Toolkit]
           [java.awt.datatransfer StringSelection])
  (:gen-class))


(defn str-it [data]
  (binding [*out* (java.io.StringWriter.)]
    (pp/pprint data)
    (.toString *out*)))


(defn clip [data]
  (-> (Toolkit/getDefaultToolkit)
      .getSystemClipboard
      (.setContents (StringSelection. (str-it data)) nil)))
