(ns org.soulspace.clj.fop.test.fop-test
  (:use [org.soulspace.clj.fop fop]))

(fo-to-pdf "integrationtest/fo.xml" "build/test.pdf")