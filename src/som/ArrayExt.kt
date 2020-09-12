package som

fun Array<Double>.plus(array2: Array<Double>): Array<Double> {
    val newArray = Array(this.size) { 0.0 }
    for (i in this.indices) {
        newArray[i] = this[i] + array2[i]
    }
    return newArray
}

fun Array<Double>.minus(array2: Array<Double>): Array<Double> {
    val newArray = Array(this.size) { 0.0 }
    for (i in this.indices) {
        newArray[i] = this[i] - array2[i]
    }
    return newArray
}

fun Array<Double>.multiply(value: Double): Array<Double> {
    val newArray = Array(this.size) { 0.0 }
    for (i in this.indices) {
        newArray[i] = this[i] * value
    }
    return newArray
}
