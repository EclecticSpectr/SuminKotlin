data class Order(
    val id: Int,
    val status: String,
    val type: String
)

val orders = mutableListOf<Order>()

fun registerOrder() {
    while (true) {
        print("0 to exit; 1 to add ")
        val action = readln().toInt()
        if (action == 0) {
            break
        }
        print("Type: ")
        val type = readln()
        print("Status: ")
        val status = readln()
        val id = orders.size + 1
        val order = Order(id, status, type)
        orders.add(order)
    }
}

fun show() {
    for (order in orders) {
        println("id: ${order.id}, status: ${order.status}, type: ${order.type}")
    }
}

fun removeCompletedOrders(orders: List<Order>, typeToRemove: String): List<Order> {
    val ordersMutable = orders.toMutableList()
    for (order in ordersMutable) {
        if (order.status == "completed" && order.type == typeToRemove) {
            ordersMutable.remove(order)
        }
    }
    return ordersMutable.toList()
}

fun main() {
    registerOrder()
    show()
    val ttt = removeCompletedOrders(orders, "completed")
    for (tt in ttt) {
        println("id: ${tt.id}, status: ${tt.status}, type: ${tt.type}")
    }
}