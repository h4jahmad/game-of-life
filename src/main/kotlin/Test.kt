var anim = 0

fun printLine(line: String) {
//	if (lastLine.length < line.length) {
	val temp = StringBuilder()
	repeat(line.length) {
		temp.append(" ")
	}
	/*if (temp.length > 1)*/
	print("\r$temp")
//	}
	print("\r$line")
}

fun animate(line: String) {
	when (anim) {
		1 -> printLine("[ \\ ] $line")
		2 -> printLine("[ | ] $line")
		3 -> printLine("[ / ] $line")
		else -> {
			anim = 0
			printLine("[ - ] $line")
		}
	}
	anim++
}

fun main() {
	while (true) {
		(0 until 20).forEach { i ->
			animate(i.toString())
			Thread.sleep(300)
		}
	}
}