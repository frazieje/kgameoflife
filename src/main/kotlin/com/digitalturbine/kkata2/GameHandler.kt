package com.digitalturbine.kkata2

interface GameHandler {

    fun setupGrid(width: Int, height: Int): Array<Array<Cell>>

    fun initializeGrid(grid: Array<Array<Cell>>)

    fun evolveGrid(previousGrid: Array<Array<Cell>>)

    fun getNeighbors(i: Int, j: Int, grid: Array<Array<Cell>>): Array<Cell>

    fun getLivingNeighbors(i: Int, j: Int, grid: Array<Array<Cell>>): Int

}