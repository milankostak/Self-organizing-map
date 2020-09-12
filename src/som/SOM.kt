package som

import kotlin.math.exp
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.sqrt

object SOM {

    /**
     * Train using self-organizing map
     *
     * @param lattice 2 dimensional grid which values are being fit to train data
     * @param train train data, currently 3 dimensional array (theoretically N dimensional)
     * @param iterations number of iterations for training
     */
    fun train(lattice: Lattice, train: Array<Array<Double>>, iterations: Int) {
        for (n in 0 until iterations) {
            val random = (Math.random() * train.size).toInt()
            val trainElement = train[random]

            var minX = 0
            var minY = 0
            var minDistance = Double.MAX_VALUE
            for (x in lattice.data.indices) {
                for (y in lattice.data[0].indices) {
                    val distance = getDistance(trainElement, lattice.data[x][y])
                    if (distance < minDistance) {
                        minDistance = distance
                        minX = x
                        minY = y
                    }
                }
            }

            val radius = getRadius(lattice.data.size, n, iterations)

            val learningRate = getLearningRate(n, iterations)
            for (x in lattice.data.indices) {
                for (y in lattice.data[0].indices) {
                    val distance = getDistance(minX, minY, x, y)
                    if (distance > radius) continue

                    val currentWeight = lattice.data[x][y]
                    val influence = exp(-distance / (2 * radius))
                    val newWeight = trainElement
                        .minus(currentWeight)
                        .multiply(learningRate)
                        .multiply(influence)
                        .plus(currentWeight)
                    lattice.update(x, y, newWeight)
                }
            }
        }
    }

    /**
     * Return per-element distance of two array
     *
     * @param array1 first array
     * @param array2 second array
     * @return distance
     */
    private fun getDistance(array1: Array<Double>, array2: Array<Double>): Double {
        var distance = 0.0
        for (i in array1.indices) {
            distance += (array1[i] - array2[i]).pow(2.0)
        }
        return sqrt(distance)
    }

    /**
     * Get radius in which the cells will be updated
     *
     * @param width of the 2 dimensional lattice
     * @param timeStep current iteration
     * @param iterations total number of iterations
     * @return radius for further calculation
     */
    private fun getRadius(width: Int, timeStep: Int, iterations: Int): Double {
        val lambda = iterations / ln(width.div(2).toDouble())
        return width.div(2) * exp(-timeStep / lambda)
    }

    /**
     * Return euclidean distance of two points
     *
     * @param x1 X coordinate of point 1
     * @param y1 Y coordinate of point 1
     * @param x2 X coordinate of point 2
     * @param y2 Y coordinate of point 2
     * @return distance
     */
    private fun getDistance(x1: Int, y1: Int, x2: Int, y2: Int): Double {
        return sqrt((x2 - x1).toDouble().pow(2.0) + (y2 - y1).toDouble().pow(2.0))
    }

    /**
     * Learning rate decreases with increasing number of iterations
     *
     * @param timeStep current iteration
     * @param iterations total number of iterations
     * @return current learning rate between <0;1>
     */
    private fun getLearningRate(timeStep: Int, iterations: Int): Double {
        return 0.1 * exp(-timeStep / iterations.toDouble())
    }

}
