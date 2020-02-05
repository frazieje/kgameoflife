package com.digitalturbine.kkata2

import javafx.application.Application
import javafx.stage.Stage
import javafx.animation.AnimationTimer
import javafx.event.EventHandler
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.paint.Color


open class GameOfLife : Application() {

    override fun start(theStage: Stage) {
        theStage.title = "Game of Life"

        val root = Group()
        val theScene = Scene(root)
        theStage.scene = theScene

        val width = 750.0
        val height = 750.0
        val cellWidth = 5.0
        val cellHeight = 5.0
        val timeStepMillis = 40


        val canvas = Canvas(width, height)

        root.children.add(canvas)

        val gc = canvas.graphicsContext2D

        var clickReset = false

        var lastNanoTime = System.nanoTime()

        val gh = GameHandlerImpl()

        val grid = gh.setupGrid((width / cellWidth).toInt(),(height / cellHeight).toInt())

        gh.initializeGrid(grid)

        object : AnimationTimer() {
            override fun handle(currentNanoTime: Long) {

                val t = (currentNanoTime - lastNanoTime) / 1000000

                if (clickReset) {
                    gh.initializeGrid(grid)
                    clickReset = false
                } else {

                    if (t > timeStepMillis) {
                        lastNanoTime = currentNanoTime

                        gc.fill = Color.WHITE
                        gc.fillRect(0.0, 0.0, width, height)
                        gc.fill = Color.BLACK

                        gh.evolveGrid(grid)

                        for (i in 0 until grid.size) {
                            for (j in 0 until grid[i].size) {
                                if (grid[i][j].isAlive) {
                                    gc.fillRect(i * cellWidth, j * cellHeight, cellWidth, cellHeight)
                                }
                            }
                        }
                    }

                }

            }
        }.start()

        root.onMouseClicked = EventHandler { event -> clickReset = true }

        theStage.show()
    }

}

fun main(args: Array<String>) {
    Application.launch(GameOfLife::class.java)
}