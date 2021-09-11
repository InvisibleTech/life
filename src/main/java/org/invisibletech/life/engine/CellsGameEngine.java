package org.invisibletech.life.engine;

import org.invisibletech.life.board.CellMat;

public interface CellsGameEngine {
  /**
   * Compute the next board based on the current board and then replace the board
   * with the new board. This is pretty much like a back buffer render and swap in
   * UI loops. This means the method changes the state of the current instance.
   *
   * The rules for the Game of Life are the following:
   *
   * If a living cell has 2 or 3 living neighbors it stays alive
   *
   * If a dead cell has 3 neighbors then it becomes alive.
   *
   * All other cases result in a cell dying. This includes cells that are too
   * isolated or too crowded.
   *
   * @return a safe copy of the newly computed board state.
   *
   */
  CellMat computeNextBoard();

  /**
   * @return a safe copy of the current board state. CellMat is mutable and so we
   *         cannot and should not give access of it to others.
   */
  CellMat currentBoard();
}