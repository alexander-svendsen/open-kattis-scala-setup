import util.TestRunner

object MainRunner extends App {

  val input = "1\n" +
    "Something awesome"

  TestRunner()
    .mainMethod(Main.main)
    .input(input)
    .run()


}
