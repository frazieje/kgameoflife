package com.digitalturbine.kkata2

import java.time.Instant
import kotlin.random.Random

class GameHandlerImpl : GameHandler {

    override fun initializeGrid(grid: Array<Array<Cell>>) {
        val rand = Random(Instant.now().toEpochMilli())
        for (i in grid.indices) {
            for (element in grid[i]) {
                element.isAlive = rand.nextBoolean()
            }
        }
    }

    override fun setupGrid(width: Int, height: Int): Array<Array<Cell>> {
        return Array(width) {
            Array(height) {
                Cell()
            }
        }
    }

    override fun evolveGrid(previousGrid: Array<Array<Cell>>) {

        val copy = setupGrid(previousGrid.size, previousGrid[0].size)

        for (i in previousGrid.indices) {
            for (j in previousGrid[i].indices) {
                val cell = copy[i][j]
                val living = getNeighbors(i, j, previousGrid).count { it.isAlive }
                if (living < 2) {
                    cell.isAlive = false
                } else if (living > 3) {
                    cell.isAlive = false
                } else if (living == 3 && !cell.isAlive) {
                    cell.isAlive = true
                } else {
                    cell.isAlive = previousGrid[i][j].isAlive
                }
            }
        }

        for (i in previousGrid.indices) {
            for (j in previousGrid[i].indices) {
                previousGrid[i][j].isAlive = copy[i][j].isAlive
            }
        }
    }

    override fun getNeighbors(i: Int, j: Int, grid: Array<Array<Cell>>): Array<Cell> {
        return (-1..1)
            .filter { x -> x + i >= 0 && x + i < grid.size }
            .flatMap { x ->
                (-1..1)
                    .filter { y -> !(x == 0 && y == 0) && y + j >= 0 && y + j < grid[x + i].size }
                    .map { y -> grid[x + i][y + j] }
            }.toTypedArray()

    }

    override fun getLivingNeighbors(i: Int, j: Int, grid: Array<Array<Cell>>): Int {
        return getNeighbors(i, j, grid).count { it.isAlive }
    }

}