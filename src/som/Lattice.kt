package som

class Lattice(size: Int, dimensions: Int) {

    val data: Array<Array<Array<Double>>> = Array(size) { Array(size) { Array(dimensions) { Math.random() } } }

    fun update(x: Int, y: Int, newWeight: Array<Double>) {
        data[x][y] = newWeight
    }

}
