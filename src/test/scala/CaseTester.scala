import org.scalatest.FlatSpec
import util.TestRunner

class CaseTester extends FlatSpec {

  "The Solution object" should "be able to solve case 1" in {

    val input = "1\n" +
      "Something awesome"

    val expectedOutPut = "Case 1: Something"

    TestRunner()
      .mainMethod(Main.main)
      .input(input)
      .expectOutput(expectedOutPut)
      .run()

    TestRunner()
      .mainMethod(Main.main)
      .withInputLine("1")
      .withInputLine("Something awesome")
      .expectOutput(expectedOutPut)
      .run()

  }


}
