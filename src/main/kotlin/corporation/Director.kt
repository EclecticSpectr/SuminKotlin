package corporation

class Director(
    id: Int,
    name: String,
    age: Int = 0
) : Worker(id, name, age, Position.DIRECTOR) {

    override fun work() {
        println("Пью кофе...")
    }

    fun takeCoffee(assistant: Assistant) {
        val drinkName: String = assistant.bringCoffee()
        println("Спасибо, ${assistant.name}! $drinkName вкусный")
    }

    fun getConsultantToWork(consultant: Consultant) {
        val count = consultant.serveCustomers()
        println("Консультант ${consultant.name} обслужил $count клиентов")
    }
}