import org.scalatest.FlatSpec
import util.TestRunner

class FileTester extends FlatSpec {

  val INPUT_FILE_NAME = "input.in"
  val OUTPUT_FILE_NAME = "output.out"

  "The Solution object"
    .should(s"be able to solve the cases in $INPUT_FILE_NAME equal to $OUTPUT_FILE_NAME") in {

    val inputFile = getClass.getResourceAsStream(INPUT_FILE_NAME)
    val solutionFile = getClass.getResourceAsStream(OUTPUT_FILE_NAME)

    TestRunner()
      .mainMethod(Main.main)
      .input(inputFile)
      .expectOutput(solutionFile)
      .run()

  }
}
