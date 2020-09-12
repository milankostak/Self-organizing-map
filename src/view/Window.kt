package view

import java.awt.FlowLayout
import javax.swing.JFrame

class Window : JFrame() {
    val raster: Raster

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        title = "SOM"

        raster = Raster()

        val layout = FlowLayout()
        layout.vgap = 0
        layout.hgap = 0

        setLayout(layout)
        add(raster)
        pack()
        setLocationRelativeTo(null)

        raster.isFocusable = true
        raster.grabFocus()
    }

}
