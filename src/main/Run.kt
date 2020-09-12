package main

import som.Lattice
import som.SOM
import view.Window

// http://www.ai-junkie.com/ann/som/som1.html
fun main() {
    val lattice = Lattice(40, 3)

    // train data - colors as 3 dimensional vectors are put into 2 dimensional grid
    val train: Array<Array<Double>> = arrayOf(
        arrayOf(252.0, 10.0, 9.0),
        arrayOf(253.0, 101.0, 62.0),
        arrayOf(1.0, 1.0, 133.0),
        arrayOf(1.0, 3.0, 248.0),
        arrayOf(0.0, 129.0, 64.0),
        arrayOf(2.0, 253.0, 0.0),
        arrayOf(252.0, 250.0, 51.0),
        arrayOf(252.0, 1.0, 252.0)
    )
    // transform colors into <0;1> range
    for (d1 in train) {
        for (i in d1.indices)
            d1[i] = d1[i] / 255.0
    }

    // train for 2000 iterations
    val iterations = 2000

    SOM.train(lattice, train, iterations)

    // show results
    val window = Window()
    val raster = window.raster

    for (x in lattice.data.indices) {
        for (y in lattice.data[0].indices) {
            raster.drawArea(x, y, 15, lattice.data[x][y])
        }
    }
    raster.repaint()
    window.isVisible = true
}
