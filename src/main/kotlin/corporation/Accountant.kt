package corporation

import java.io.File

class Accountant(
    id: Int,
    name: String,
    age: Int = 0
) : Worker(id, name, age, Position.ACCOUNTANT) {

    private val fileProductCards = File("product_cards.txt")
    private val fileWorkers = File("employees.txt")

    override fun work() {

        while (true) {
            val operationCodes = OperationCode.entries
            println("Enter the operation code. ")
            for ((index, code) in operationCodes.withIndex()) {
                print("$index - ${code.title}\n")
            }
            val operationIndex = readln().toInt()
            val operationCode = operationCodes[operationIndex]
            when (operationCode) {
                OperationCode.EXIT -> break
                OperationCode.REGISTER_NEW_ITEM -> registerNewItem()
                OperationCode.SHOW_ALL_ITEMS -> showAllItems()
                OperationCode.REMOVE_PRODUCT_CARD -> removeProductCard()
                OperationCode.REGISTER_NEW_EMPLOYEE -> registryNewEmployee()
                OperationCode.FIRE_AN_EMPLOYEE -> fireEmployee()
                OperationCode.SHOW_ALL_EMPLOYEES -> showAllEmployees()
            }
        }
    }

    private fun fireEmployee() {
        print("Enter employee`s id to fire: ")
        val id = readln().toInt()
        val employees = loadAllEmployees()
        fileWorkers.writeText("")
        for (employee in employees) {
            if (employee.id != id) {
                saveWorkerToFile(employee)
            }
        }
    }

    private fun showAllEmployees() {
        val employees = loadAllEmployees()
        for (employee in employees) {
            employee.printInfo()
        }
    }

    private fun registryNewEmployee() {
        val positions = Position.entries
        print("Choose position - ")
        for ((index, position) in positions.withIndex()) {
            print("$index - ${position.title}")
            if (index < positions.size - 1) {
                print(", ")
            } else {
                print(": ")
            }
        }
        val positionIndex = readln().toInt()
        val position = positions[positionIndex]
        print("Enter id: ")
        val id = readln().toInt()
        print("Enter name: ")
        val name = readln()
        print("Enter age: ")
        val age = readln().toInt()
        val worker = when (position) {
            Position.DIRECTOR -> Director(id, name, age)
            Position.ACCOUNTANT -> Accountant(id, name, age)
            Position.ASSISTANT -> Assistant(id, name, age)
            Position.CONSULTANT -> Consultant(id, name, age)
        }
        saveWorkerToFile(worker)
    }

    private fun loadAllEmployees(): MutableList<Worker> {
        val employees = mutableListOf<Worker>()

        if (!fileWorkers.exists()) fileWorkers.createNewFile()

        val content = fileWorkers.readText().trim()

        if (content.isEmpty()) return employees

        val employeesAsText = content.split('\n')
        for (employeeAsText in employeesAsText) {
            val properties = employeeAsText.split('%')
            val id = properties[0].toInt()
            val name = properties[1]
            val age = properties[2].toInt()
            val positionAsText = properties.last()
            val position = Position.valueOf(positionAsText)
            val worker = when (position) {
                Position.DIRECTOR -> Director(id, name, age)
                Position.ACCOUNTANT -> Accountant(id, name, age)
                Position.ASSISTANT -> Assistant(id, name, age)
                Position.CONSULTANT -> Consultant(id, name, age)
            }
            employees.add(worker)
        }
        return employees
    }

    private fun saveWorkerToFile(worker: Worker) {
        fileWorkers.appendText("${worker.id}%${worker.name}%${worker.age}%${worker.position}\n")
    }

    private fun removeProductCard() {
        val cards = loadAllCards()
        print("Enter name of card to removing: ")
        val name = readln()
        for (card in cards) {
            if (card.name == name) {
                cards.remove(card)
                break
            }
        }
        fileProductCards.writeText("")
        for (card in cards) {
            saveProductCardToFile(card)
        }
    }

    private fun loadAllCards(): MutableList<ProductCard> {
        val cards = mutableListOf<ProductCard>()
        val products = fileProductCards.readText().trim().split('\n')

        if (products.isEmpty()) {
            return cards
        }

        for (product in products) {
            val properties = product.split('%')
            val name = properties[0]
            val brand = properties[1]
            val price = properties[2].toInt()
            val type = properties.last()
            val productType = ProductType.valueOf(type)

            val productCard =
                when (productType) {
                    ProductType.FOOD -> {
                        val caloric = properties[3].toInt()
                        FoodCard(name, brand, price, caloric)
                    }

                    ProductType.APPLIANCE -> {
                        val wattage = properties[3].toInt()
                        ApplianceCard(name, brand, price, wattage)
                    }

                    ProductType.SHOE -> {
                        val size = properties[3].toFloat()
                        ShoeCard(name, brand, price, size)
                    }
                }
            cards.add(productCard)
        }
        return cards
    }

    private fun showAllItems() {
        if (!fileProductCards.exists()) fileProductCards.createNewFile()

        val content = fileProductCards.readText().trim()

        if (content.isEmpty()) {
            return
        }

        val products = content.split('\n')
        for (product in products) {
            val properties = product.split('%')
            val name = properties[0]
            val brand = properties[1]
            val price = properties[2].toInt()
            val type = properties.last()
            val productType = ProductType.valueOf(type)

            val productCard =
                when (productType) {
                    ProductType.FOOD -> {
                        val caloric = properties[3].toInt()
                        FoodCard(name, brand, price, caloric)
                    }

                    ProductType.APPLIANCE -> {
                        val wattage = properties[3].toInt()
                        ApplianceCard(name, brand, price, wattage)
                    }

                    ProductType.SHOE -> {
                        val size = properties[3].toFloat()
                        ShoeCard(name, brand, price, size)
                    }
                }
            productCard.printInfo()
        }
    }

    private fun saveProductCardToFile(productCard: ProductCard) {
        fileProductCards.appendText("${productCard.name}%")
        fileProductCards.appendText("${productCard.brand}%")
        fileProductCards.appendText("${productCard.price}%")
        when (productCard) {
            is FoodCard -> {
                val caloric = productCard.caloric
                fileProductCards.appendText("$caloric%${ProductType.FOOD}\n")
            }

            is ShoeCard -> {
                val size = productCard.size
                fileProductCards.appendText("$size%${ProductType.SHOE}\n")
            }

            is ApplianceCard -> {
                val wattage = productCard.wattage
                fileProductCards.appendText("$wattage%${ProductType.APPLIANCE}\n")
            }
        }
    }

    private fun registerNewItem() {
        val productTypes = ProductType.entries
        print("Enter the product type. ")
        for ((index, type) in productTypes.withIndex()) {
            print("$index - ${type.title}")
            if (index < productTypes.size - 1) {
                print(", ")
            } else {
                print(": ")
            }
        }
        val productTypeIndex = readln().toInt()
        val productType = productTypes[productTypeIndex]
        print("Enter the product name: ")
        val productName = readln()
        fileProductCards.appendText("$productName%")
        print("Enter the product brand: ")
        val productBrand = readln()
        fileProductCards.appendText("$productBrand%")
        print("Enter the product price: ")
        val productPrice = readln().toInt()
        fileProductCards.appendText("$productPrice%")
        when (productType) {
            ProductType.FOOD -> {
                print("Enter the product caloric: ")
                val caloric = readln().toInt()
                fileProductCards.appendText("$caloric%")
            }

            ProductType.APPLIANCE -> {
                print("Enter the product wattage: ")
                val wattage = readln().toInt()
                fileProductCards.appendText("$wattage%")
            }

            ProductType.SHOE -> {
                print("Enter the product size: ")
                val size = readln().toFloat()
                fileProductCards.appendText("$size%")
            }
        }
        fileProductCards.appendText("$productType\n")
    }
}