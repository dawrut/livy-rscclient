package livy.example

import java.io.File
import java.net.URI

import org.apache.livy.LivyClientBuilder

object Main {
  def main(args: Array[String]): Unit = {
    val livyUrl = "http://localhost:8998"
    val piJar = """path/to/spark-livy_2.11-0.1.jar"""
    val samples = 1000

    val client = new LivyClientBuilder()
      .setURI(new URI(livyUrl))
      .build

    try {
      println(s"Uploading $piJar to the Spark context...")
      client.uploadJar(new File(piJar)).get

      println(s"Running PiJob with $samples samples...")
      val pi = client.submit(new PiJob(samples)).get

      println("Pi is roughly: " + pi)
    } finally client.stop(true)
  }

}
