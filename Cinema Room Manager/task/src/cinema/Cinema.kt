package cinema

val rows = initRows()
val num = initNum()
val seats = MutableList(rows) { MutableList(num) { 'S' } }
var purchased = 0
val totalPrice = if (rows * num <= 60) {
    rows * num * 10
} else {
    (rows / 2) * num * 10 + ((rows - (rows / 2)) * num * 8)
}
var currentIncome = 0

fun main() {
    while (true) {
        showMenu()
        when (readln()) {
            "1" -> showTheSeats()
            "2" -> buyTicket()
            "3" -> statistics()
            "0" -> break
        }
    }

}


fun initNum(): Int {
    println("Enter the number of seats in each row:")
    return readln().toInt()
}

fun initRows(): Int {
    println("Enter the number of rows:")
    return readln().toInt()
}

fun showMenu() {
    println(
        """
1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit"""
    )
}

fun showTheSeats() {
    seats.also {
        println("Cinema:")
        print(" ")
        for (n in 1..num) print(" $n")
        println()
    }
        .forEachIndexed() { index, it ->
            print("${index + 1} ")
            println(it.joinToString(" "))
        }
}

fun buyTicket() {
    while (true) {
        println("Enter a row number:")
        val theRow = readln().toInt()
        println("Enter a seat number in that row:")
        val theNum = readln().toInt()
        try {
            if (seats[theRow - 1][theNum - 1] == 'B') {
                println("That ticket has already been purchased!")
                continue
            }
            seats[theRow - 1][theNum - 1] = 'B'
        } catch (e: IndexOutOfBoundsException) {
            println("Wrong input!")
            continue
        }
        val price = if (rows * num <= 60) {
            10
        } else {
            if (theRow <= rows / 2) {
                10
            } else {
                8
            }
        }
        println("Ticket price:")
        println("$$price")
        purchased++
        currentIncome += price
        break
    }
}

fun statistics() {
    println(
        "Number of purchased tickets: $purchased\n" +
                "Percentage: ${"%.2f".format(purchased.toDouble() / (rows.toDouble() * num.toDouble()) * 100)}%\n" +
                "Current income: $$currentIncome\n" +
                "Total income: $$totalPrice"
    )
}
