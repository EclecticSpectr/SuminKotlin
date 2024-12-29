fun main() {

}

open class BankAccount(

) {
    private var balance: Int = 0

    fun deposit(amount: Int): Int {
        if (amount > 0) balance += amount
        return balance
    }

    protected fun withdraw(amount: Int): Int {
        if (amount in 1..<balance) balance -= amount
        return balance
    }

    fun getBalance(): Int {
        println(balance)
        return balance
    }
}

class SpecialBankAccount : BankAccount() {
    fun specialWithdraw(amount: Int): Int {
        withdraw(amount)
        return getBalance()
    }
}