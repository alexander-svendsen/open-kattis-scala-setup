
import scala.annotation.tailrec
import scala.io.StdIn._

object Main extends App {

  val n = readLine().toInt

  @tailrec
  def solveCase(caseNumber: Int = 1): Unit = {
    if (caseNumber <= n) {

      // Todo figure out the solution for this cas

      println(s"Case $caseNumber: The solution")
      solveCase(caseNumber + 1)
    }
  }

  solveCase()
}
