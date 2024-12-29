package corporation

open class Worker(
    val id: Int,
    val name: String,
    val age: Int = 0,
    val position: Position
) {

    open fun work() {
        println("Работаю...")
    }

    fun printInfo(){
        print("Id: $id, Name: $name, Age: $age, Position: $position")
    }
}