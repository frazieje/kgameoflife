package com.frazieje.gameoflife

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GameOfLifeTests {

    private val gameHandler: GameHandler = GameHandlerImpl()

    @BeforeAll
    fun setup() {

    }

    @Test
    fun `should do something`() {
        assertThat(3).isEqualTo(3)
    }

    @Test
    fun `should generate grid with correct size`() {
        val grid: Array<Array<Cell>> = gameHandler.setupGrid(10, 10)
        assertThat(grid.size).isEqualTo(10)
        assertThat(grid[0].size).isEqualTo(10)
    }

    @Test
    fun `setup should generate grid with no live cells`() {
        val grid: Array<Array<Cell>> = gameHandler.setupGrid(10, 10)
        for (i in 0 until 10) {
            for (j in 0 until 10) {
                assertThat(grid[i][j].isAlive).isFalse()
            }
        }
    }

    @Test
    fun `initialize should create random grid`() {
        val grid = gameHandler.setupGrid(50, 50)
        gameHandler.initializeGrid(grid)
        var aliveCellFound = false
        var deadCellFound = false
        for (i in 0 until 10) {
            for (j in 0 until 10) {
                if (grid[i][j].isAlive) {
                    aliveCellFound = true
                } else {
                    deadCellFound = true
                }
            }
        }
        assertThat(aliveCellFound).isTrue()
        assertThat(deadCellFound).isTrue()
    }

    @Test
    fun `cell should die with 0 living neighbors`() {
        val grid = gameHandler.setupGrid(10, 10)
        grid[4][4].isAlive = true
        gameHandler.evolveGrid(grid)
        assertThat(grid[4][4].isAlive).isFalse()
    }

    @Test
    fun `cell should die with 1 living neighbors`() {
        val grid = gameHandler.setupGrid(10, 10)
        grid[4][4].isAlive = true
        grid[3][4].isAlive = true
        gameHandler.evolveGrid(grid)
        assertThat(grid[4][4].isAlive).isFalse()
    }

    @Test
    fun `cell should live on with 2 living neighbors`() {
        val grid = gameHandler.setupGrid(10, 10)
        grid[4][4].isAlive = true
        grid[3][4].isAlive = true
        grid[5][4].isAlive = true
        gameHandler.evolveGrid(grid)
        assertThat(grid[4][4].isAlive).isTrue()
    }

    @Test
    fun `cell should live on with 3 living neighbors`() {
        val grid = gameHandler.setupGrid(10, 10)
        grid[4][4].isAlive = true
        grid[3][4].isAlive = true
        grid[5][4].isAlive = true
        grid[4][5].isAlive = true
        gameHandler.evolveGrid(grid)
        assertThat(grid[4][4].isAlive).isTrue()
    }

    @Test
    fun `cell should die with 4 living neighbors`() {
        val grid = gameHandler.setupGrid(10, 10)
        grid[4][4].isAlive = true
        grid[3][4].isAlive = true
        grid[5][4].isAlive = true
        grid[4][5].isAlive = true
        grid[4][3].isAlive = true
        gameHandler.evolveGrid(grid)
        assertThat(grid[4][4].isAlive).isFalse()
    }

    @Test
    fun `cell should die with 5 living neighbors`() {
        val grid = gameHandler.setupGrid(10, 10)
        grid[4][4].isAlive = true
        grid[3][4].isAlive = true
        grid[5][4].isAlive = true
        grid[4][5].isAlive = true
        grid[4][3].isAlive = true
        grid[5][5].isAlive = true
        gameHandler.evolveGrid(grid)
        assertThat(grid[4][4].isAlive).isFalse()
    }

}
