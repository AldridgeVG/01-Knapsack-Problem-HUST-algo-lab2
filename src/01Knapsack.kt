import java.io.File

// A utility function that returns maximum of two integers
fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

// Returns the maximum value that can be put in a knapsack of capacity W
fun knapSack(W: Int, weight: Array<String>, value: Array<String>, n: Int): Int {
    var i: Int = 0
    var w: Int
    val K = Array(n + 1) { IntArray(W + 1) }

    // Build table K[][] in bottom up manner
    while (i <= n) {
        w = 0
        while (w <= W) {
            if (i == 0 || w == 0)
                K[i][w] = 0
            else if (weight[i - 1].toInt() <= w)
                K[i][w] = max(value[i - 1].toInt() + K[i - 1][w - weight[i - 1].toInt()], K[i - 1][w])
            else
                K[i][w] = K[i - 1][w]
            w++
        }
        i++
    }
    return K[n][W]
}


// Driver program to test above function
fun main(args: Array<String>) {
//    val value = intArrayOf(60, 100, 120)
//    val weight = intArrayOf(10, 20, 30)
//    val W = 50
    println("Input filename to read data:")
    val filename = readLine()
    val line = File(filename).readLines()
    val W = line[0].toInt()
    val weight = line[1].split(" ").toList().toTypedArray()
    val value = line[2].split(" ").toList().toTypedArray()
    var i = 0
    val n = value.size
    println("Max weight W: $W")
    println("weight array:")
    while (i < n) {
        print("${weight[i]}\t")
        i++
    }
    i = 0
    println()
    println("value  array:")
    while (i < n) {
        print("${value[i]}\t")
        i++
    }
    println()
    println("The max value can be carried is:")
    println(knapSack(W, weight, value, n))
}
