package util

import java.io.{BufferedReader, ByteArrayInputStream, ByteArrayOutputStream, InputStream, InputStreamReader, SequenceInputStream}

import org.scalatest.Assertions._

import scala.annotation.tailrec


case class TestRunner(private val mainMethod: Array[String] => Unit = null,
                      private val inputStream: InputStream = null,
                      private val expectedOutputStream: InputStream = null) {

  def mainMethod(mainMethod: Array[String] => Unit): TestRunner = {
    copy(mainMethod = mainMethod)
  }

  def input(input: String): TestRunner = {
    copy(inputStream = new ByteArrayInputStream(input.getBytes))
  }

  def input(input: InputStream): TestRunner = {
    copy(inputStream = input)
  }

  def withInputLine(line: String): TestRunner = {
    val addedInputStream = new ByteArrayInputStream(correctLine(line).getBytes)

    if (inputStream == null) {
      copy(inputStream = addedInputStream)
    }
    else {
      copy(inputStream = new SequenceInputStream(inputStream, addedInputStream))
    }

  }

  private def correctLine(line: String): String = {
    if (line.endsWith("\n")) line
    else line + "\n"
  }

  def expectOutput(output: String): TestRunner = {
    copy(expectedOutputStream = new ByteArrayInputStream(output.getBytes))
  }

  def expectOutput(output: InputStream): TestRunner = {
    copy(expectedOutputStream = output)
  }

  def run(): Unit = {
    // If there is no expected output, simply run the solution
    if (expectedOutputStream == null) {
      // Set stdIn from inputStream
      Console.withIn(inputStream)(mainMethod.apply(null))
    }
    else {
      runWithTestCoverage()
    }

  }

  private def runWithTestCoverage(): Unit = {
    val stdOutputStream = new ByteArrayOutputStream()

    // Set stdIn from inputStream and write stdout to stdOutputStream
    Console.withIn(inputStream)(Console.withOut(stdOutputStream)(mainMethod.apply(null)))

    val stdOutInputStream = new ByteArrayInputStream(stdOutputStream.toByteArray)

    val stdOutReader = new BufferedReader(new InputStreamReader(stdOutInputStream))
    val expectedOutputReader = new BufferedReader(new InputStreamReader(expectedOutputStream))


    def assertLinesAreEqual(stdOutLine: String, expectedLine: String): Unit = {
      if (stdOutLine == null) fail(s"Missing solution in stdOut, expected line: $stdOutLine")
      else if (expectedLine == null) fail(s"Too many lines in stdOut, extra line: $stdOutLine")
      assert(expectedLine === stdOutLine)
    }

    @tailrec
    def testStdOutIsEqualToExpectedOutput(stdOutLine: String, expectedLine: String): Unit = {
      if (stdOutLine != null && expectedLine != null) {
        assertLinesAreEqual(stdOutLine, expectedLine)
        testStdOutIsEqualToExpectedOutput(
          stdOutReader.readLine(),
          expectedOutputReader.readLine())
      }
    }

    testStdOutIsEqualToExpectedOutput(
      stdOutReader.readLine(),
      expectedOutputReader.readLine())
  }
}