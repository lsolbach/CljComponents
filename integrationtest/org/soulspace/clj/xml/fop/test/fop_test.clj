(ns org.soulspace.clj.xml.fop.test.fop-test
  (:use [org.soulspace.clj.xml.fop fop]))

(fo-to-pdf "integrationtest/fo.xml" "build/test.pdf")