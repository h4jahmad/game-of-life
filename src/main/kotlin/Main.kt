import Status.*

const val ROWS_COUNT = 6
const val COLS_COUNT = 6

val table = Array(ROWS_COUNT) { Array(COLS_COUNT) { DEAD } }

enum class Status(val value: Char) {
	ALIVE('#'),
	DEAD(' '),
	;

	override fun toString(): String = value.toString()
}

fun checkTable() {
	val cellsToBringToLife = mutableListOf<Pair<Int, Int>>()
	val cellsToKill = mutableListOf<Pair<Int, Int>>()
	table.forEachIndexed { rowPosition, row ->
		row.forEachIndexed { colPosition, cell ->
			val count = getNeighboursCount(rowPosition, colPosition)
			if (cell == ALIVE) {
				when {
					count < 2 -> cellsToKill.add(Pair(rowPosition, colPosition))
					count > 3 -> cellsToKill.add(Pair(rowPosition, colPosition))
					else -> {}
				}
			} else if (count == 3) cellsToBringToLife.add(Pair(rowPosition, colPosition))
		}
	}

	cellsToKill.forEach { pair -> table[pair.first][pair.second] = DEAD }
	cellsToBringToLife.forEach { pair -> table[pair.first][pair.second] = ALIVE }
}

fun getNeighboursCount(rowIndex: Int, colIndex: Int): Int {
	var liveNeighboursCount = 0
	for (row in rowIndex - 1..rowIndex + 1) {
		if (row == -1 || row == ROWS_COUNT) continue
		for (col in colIndex - 1..colIndex + 1) {
			if (col == -1 || col == COLS_COUNT) continue
			if (row == rowIndex && col == colIndex) continue
			if (table[row][col] == ALIVE) liveNeighboursCount += 1
		}
	}

	return liveNeighboursCount
}

fun printTable() {
	table.forEach { row ->
		print("\r")
		row.forEach { cell ->
			print(cell)
		}
		println()
	}
	repeat(COLS_COUNT) {
		print("-")
	}
	println()
}

fun setInitState() {
	table[0][1] = ALIVE
	table[1][1] = ALIVE
	table[2][1] = ALIVE
	table[2][2] = ALIVE
	table[2][3] = ALIVE
	table[2][4] = ALIVE
	table[2][5] = ALIVE
}

fun main() {
	setInitState()
	while (true) {
		printTable()
		checkTable()
		Thread.sleep(1000)
	}
}