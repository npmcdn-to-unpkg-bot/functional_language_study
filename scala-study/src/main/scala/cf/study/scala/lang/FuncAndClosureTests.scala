package cf.study.scala.lang


import org.junit.Test

import scala.io.Source

/**
 * Created by fan on 2015/8/10.
 */

object LongLines {
	def processFile(filename: String, width: Int): Unit = {
		val source = Source.fromFile(filename)
		for (line <- source.getLines())
			processLine(filename, width, line)
	}

	private def processLine(filename: String, width: Int, line: String): Unit = {
		if (line.length > width)
			println("%s: %s".format(filename, line.trim))
	}

	def _processFile(filename: String, width: Int): Unit = {
		def processLine(filename: String, width: Int, line: String): Unit = {
			if (line.length > width)
				println("%s: %s".format(filename, line.trim))
		}

		val source = Source.fromFile(filename)
		for (line <- source.getLines())
			processLine(filename, width, line)
	}
}

//def func() {
//	println(func)
//}

class FuncAndClosureTests {
	@Test def testFirstClassFunc(): Unit = {
		var increase = (x: Int) => x + 1
		println(increase)
		println(increase(10))
		println(increase.apply(10))

		increase = (x: Int) => {
			println("we")
			println("are")
			println("here")
			x + 1
		}

		println(increase)
		println(increase(10))
		println(increase.apply(10))
	}

	@Test def testLambda(): Unit = {
		val list:List[Long] = MathTests.pi2Longs(10)
		val gt5 = (x: Long) => x > 5

		println(list.filter(gt5).toList)
		println(list.filter((x: Long) => x > 5).toList)
		println(list.filter(_ > 5).toList)


		val oneToTen: List[Int] = (0 to 10).toList
		println((0 /: oneToTen) (_ + _))
	}

	@Test def testPartiallyAppliedFunc(): Unit = {
		def sum(a: Int, b: Int, c: Int) = a + b + c
		println(sum(_: Int, _: Int, _: Int))
		val a = sum _
		println(a)
		println(a(1, 2, 3))
		println(a.apply(1, 2, 3))
		println(a.curried.apply(1).apply(2).apply(3))

		val b = sum(1, _: Int, 3)
		println(b(2))
		println(b(5))

		def printFunc(f: (Int, Int, Int) => Int) = println(f)
		printFunc(sum)
	}

	@Test def testClosure(): Unit = {
		var more = 1
		val addMore = (x: Int) => x + more
		println((addMore(10)))
		more = 2
		println((addMore(10)))

		def makeIncreaser(more: Int) = (x: Int) => x + more
		val inc1 = makeIncreaser(1)
		println(inc1(5))
	}

	@Test def specialFuncCalls(): Unit = {
		def echo(args: String*) =
			for (arg <- args) println(arg)

		echo()
		echo("one")
		echo("hello", "world!")

		val arr = Array("What's", "up", "doc?")
//		error: type mismatch; found : Array[java.lang.String]	required: String
		//		echo(arr)
		echo(arr: _*)
	}

	
}
