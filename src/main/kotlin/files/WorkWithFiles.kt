package files

import java.io.File

fun main() {

    val file = File("todo_list.txt")
    val operations = OperationCode.entries
    var operationIndex: Int

    while (true) {
        print("Choose action: ")
        for ((index, item) in operations.withIndex()) {
            print("$index - ${item.title}")
            print(
                if (index < operations.size - 1) {
                    ", "
                } else {
                    ": "
                }
            )
        }
        operationIndex = readln().toInt()
        when (operations[operationIndex]) {
            OperationCode.EXIT -> break
            OperationCode.ADD_NEW_TASK -> file.appendText(readln() + '\n')
            OperationCode.SHOW_ALL_TASKS -> {
                val stringsFile = file.readText().trim().split('\n')
                for ((index, item) in stringsFile.withIndex()) {
                    println("$index - $item")
                }
            }
        }
    }
}