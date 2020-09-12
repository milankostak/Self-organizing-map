package view

import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.image.BufferedImage
import javax.swing.JPanel

class Raster: JPanel() {

    private val img: BufferedImage
    private val g: Graphics

    private val WIDTH = 15 * 40
    private val HEIGHT = 15 * 40

    init {
        preferredSize = Dimension(WIDTH, HEIGHT)
        img = BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB)
        g = img.graphics
    }

    public override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        g.drawImage(img, 0, 0, null)
    }

    fun drawArea(x: Int, y: Int, size: Int, color: Array<Double>) {
        val floats = Color.RGBtoHSB(
            (color[0] * 255).toInt(),
            (color[1] * 255).toInt(),
            (color[2] * 255).toInt(),
            null
        )
        val colorHSB = Color.getHSBColor(floats[0], floats[1], floats[2])
        g.color = colorHSB
        g.fillRect(x * size, y * size, size, size)
    }
}