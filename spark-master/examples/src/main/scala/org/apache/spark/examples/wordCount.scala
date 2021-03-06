package org.apache.spark.examples

import org.apache.spark.{SparkConf, SparkContext}

object wordCount {
  def main(args: Array[String]) {
    val inputFile = "./examples/src/main/scala/org/apache/spark/examples/inputWordCount"
    val conf = new SparkConf().setAppName("wordCount")
    // Create a Scala Spark Context.
    val sc = new SparkContext(conf)
    // Load our input data.
    val input =  sc.textFile(inputFile)
    // Split up into words.
    val words = input.flatMap(line => line.split(" "))
    // Transform into word and count.
    val counts = words.map(word => (word, 1)).reduceByKey{case (x, y) => x + y}
    // Save the word count back out to a text file, causing evaluation.

    counts.collect().foreach(x => println(x))
  }
}
