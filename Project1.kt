package tictactoe

fun isNumber(cS: String?): Boolean {
    return if (cS.isNullOrEmpty()) false else cS.all { Character.isDigit(it) }
}

fun main() {
    // Make grid
    val line = "         "
    val s = mutableListOf(
            mutableListOf(line[0], line[1], line[2]),
            mutableListOf(line[3], line[4], line[5]),
            mutableListOf(line[6], line[7], line[8])
    )
    // Print empty grid
    var field = """
    ---------
    | ${s[0][0]} ${s[0][1]} ${s[0][2]} |
    | ${s[1][0]} ${s[1][1]} ${s[1][2]} |
    | ${s[2][0]} ${s[2][1]} ${s[2][2]} |
    ---------
    """.trimIndent()
    println(field)
    // Loop for game
    var result = "none"
    var countX = 0
    var countO = 0
    game@while (result == "none") {
        // Get user X input
        var correctInputX = false
        while (!correctInputX) {
            println("Enter cells: ")
            val c =  readLine()!!
            val cS: List <String> = c.split(' ')
            if (!isNumber(cS[0]) || !isNumber(cS[1])) println("You should enter numbers!") else {
                val cNum1 = cS[0].toInt()
                val cNum2 = cS[1].toInt()
                if (cNum1 < 1 || cNum1 > 3 || cNum2 < 1 || cNum2 > 3) {
                    println("Coordinates should be from 1 to 3!")
                } else {
                    if (s[cNum1 - 1][cNum2 - 1] != ' ') {
                        print("This cell is occupied! Choose another one!")
                    } else {
                        s[cNum1 - 1][cNum2 - 1] = 'X'
                        correctInputX = true
                    }
                }
            }
        }
        countX ++
        // Print new field
        field = """
        ---------
        | ${s[0][0]} ${s[0][1]} ${s[0][2]} |
        | ${s[1][0]} ${s[1][1]} ${s[1][2]} |
        | ${s[2][0]} ${s[2][1]} ${s[2][2]} |
        ---------
        """.trimIndent()
        println(field)
        //Check for X wins or Draw
        when {
             s[0][0] == 'X' && s[0][0] == s[0][1] && s[0][0] == s[0][2] ||
             s[1][0] == 'X' && s[1][0] == s[1][1] && s[1][0] == s[1][2] ||
             s[2][0] == 'X' && s[2][0] == s[2][1] && s[2][0] == s[2][2] ||
             s[0][0] == 'X' && s[0][0] == s[1][0] && s[0][0] == s[2][0] ||
             s[0][1] == 'X' && s[0][1] == s[1][1] && s[0][1] == s[2][1] ||
             s[0][2] == 'X' && s[0][2] == s[1][2] && s[0][2] == s[2][2] ||
             s[0][0] == 'X' && s[0][0] == s[1][1] && s[0][0] == s[2][2] ||
             s[2][0] == 'X' && s[2][0] == s[1][1] && s[2][0] == s[0][2]
            -> {
                 result = "X wins"
                 break@game
            }
            countX == 5 -> {
                result = "Draw"
                break@game
            }
        }
        // Get user X input
        var correctInputO = false
        while (!correctInputO) {
            println("Enter cells: ")
            val c =  readLine()!!
            val cS: List <String> = c.split(' ')
            if (!isNumber(cS[0]) || !isNumber(cS[1])) println("You should enter numbers!") else {
                val cNum1 = cS[0].toInt()
                val cNum2 = cS[1].toInt()
                if (cNum1 < 1 || cNum1 > 3 || cNum2 < 1 || cNum2 > 3) {
                    println("Coordinates should be from 1 to 3!")
                } else {
                    if (s[cNum1 - 1][cNum2 - 1] != ' ') {
                        print("This cell is occupied! Choose another one!")
                    } else {
                        s[cNum1 - 1][cNum2 - 1] = 'O'
                        correctInputO = true
                    }
                }
            }
        }
        countO ++
        // Print new field
        field = """
        ---------
        | ${s[0][0]} ${s[0][1]} ${s[0][2]} |
        | ${s[1][0]} ${s[1][1]} ${s[1][2]} |
        | ${s[2][0]} ${s[2][1]} ${s[2][2]} |
        ---------
        """.trimIndent()
        println(field)
        //Check for O wins
        when {
            s[0][0] == 'O' && s[0][0] == s[0][1] && s[0][0] == s[0][2] ||
            s[1][0] == 'O' && s[1][0] == s[1][1] && s[1][0] == s[1][2] ||
            s[2][0] == 'O' && s[2][0] == s[2][1] && s[2][0] == s[2][2] ||
            s[0][0] == 'O' && s[0][0] == s[1][0] && s[0][0] == s[2][0] ||
            s[0][1] == 'O' && s[0][1] == s[1][1] && s[0][1] == s[2][1] ||
            s[0][2] == 'O' && s[0][2] == s[1][2] && s[0][2] == s[2][2] ||
            s[0][0] == 'O' && s[0][0] == s[1][1] && s[0][0] == s[2][2] ||
            s[2][0] == 'O' && s[2][0] == s[1][1] && s[2][0] == s[0][2]
            -> {
                result = "O wins"
                break@game
            }
        }
    }
    // Print result of game
    println(result)
}
