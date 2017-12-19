# open-kattis-scala-setup
Basic scala setup to help you complete a [kattis](https://open.kattis.com) problem or any one file algorithm puzzle.

The idea is that you fork this setup and tweak it to fit your needs. 
The most useful feature included in this setup is the test utility. 
It should be as easy as dragging and dropping you input into `input.in` 
and the expected output in `output.out` then run `FileTester.scala` to validate that your solution is correct. 


## How code a solution:
Code the solution in the `Main.scala` file, or rename it. It is up to you.

A start program can be:
```scala
import scala.io.StdIn._

object Main extends App {
    def solver(line: String): Unit = {
        if(line != null) {
          val Array(a, b) = line.split(" ").map(_.toLong)    
          // solve test case and output answer
          solver(readLine())
        }  
      }      
      solver(readLine())

}
```

A slightly different sample can be found in `Main.scala`.


## Tests
This setup includes full support for [scalatest.](http://www.scalatest.org) Write tests exactly as you want them yourself.
All test can be run with `sbt test`.

This setup includes some helper files to get you started:
- You can use `FileTester.scala` to test that your main method returns the same output as given in `output.out` when the
the input comes from `input.in`.
- You can use `CaseTester.scala` to test specific cases as they arrive.
- If you only need to run the solution given some input, you can use the `MainRunner.scala`

Both of these helper test classes uses the `TestRunner.scala` utility file to be able to do these things. See how the
two helper test classes uses it for examples how you can do the same.


