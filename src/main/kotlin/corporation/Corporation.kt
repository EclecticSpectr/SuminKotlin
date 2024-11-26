package corporation

fun main() {
    val director = Director("Andrey", 25)
    val consultant = Consultant("Max")
    val assistant = Assistant("Helen", 20)
    val accountant = Accountant("Christian", 30)
    val employees = listOf<Worker>(director, consultant, assistant, accountant)
    for (employee in employees) {
        employee.work()
    }
}