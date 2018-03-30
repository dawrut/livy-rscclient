package livy.example

import org.apache.livy.{Job, JobContext}

class PiJob(samples: Int) extends Job[Double] with (Int => Int) with ((Int, Int) => Int) {
  require(samples > 0)

  override def apply(v1: Int): Int = {
    val x = Math.random
    val y = Math.random

    if (x * x + y * y < 1) 1
    else 0
  }

  override def apply(v1: Int, v2: Int): Int = v1 + v2

  override def call(jobContext: JobContext): Double = {
    val sampleList = 1 to samples toList

    4.0d * jobContext
      .sc
      .parallelize(sampleList)
      .map(this)
      .reduce(this) / samples
  }
}
