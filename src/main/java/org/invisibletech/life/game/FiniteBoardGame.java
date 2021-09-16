package org.invisibletech.life.game;

import org.invisibletech.life.board.Board;
import org.invisibletech.life.board.BoardFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * Represents an instance for the Game Of Life. It supports the computation of
 * the next board and returning a safe copy of the state. See method
 * documentation for details.
 *
 * This game treats the board as finite and cells beyond the border are treated
 * as dead.
 *
 */
public final class FiniteBoardGame implements Game {
  private static final Logger LOGGER = LoggerFactory.getLogger(FiniteBoardGame.class);
  private Board board;

  @Autowired
  public FiniteBoardGame(@Value("${life.board.width:5}") final int width,
      @Value("${life.board.height:5}") final int height, final BoardFactory boardFactory) {
    LOGGER.info("Creating board with dimensions {} x {}", height, width);
    board = boardFactory.createBoard(height, width);
  }

  /**
   * Compute the next board based on the current board and then replace the
   * current one with the new board. This is pretty much like a back buffer render
   * and swap in UI loops. This means the method changes the state of the current
   * instance.
   *
   * The rules for the Game of Life are the following:
   *
   * If a living cell has 2 or 3 living neighbors it stays alive
   *
   * If a dead cell has 3 neighbors then it becomes alive.
   *
   * All other cases result in a cell dying. This rule culls the cells that are
   * too isolated or too crowded.
   *
   * @return a safe copy of the current board state.
   *
   */
  @Override
  public Board computeNextBoard() {
    final var nextBoard = new Board(board.matrixOf());
    LOGGER.debug("Compute next board for {}", board);

    for (var row = 0; row < board.getRows(); row++) {
      for (var col = 0; col < board.getCols(); col++) {
        final var livingNeighbors = countNeighborhoodLives(row, col);
        LOGGER.debug("Living neighbors for [{},{}] == {}", row, col, livingNeighbors);

        if (board.cellAt(row, col)) {
          nextBoard.setCell(row, col, livingNeighbors == 2 || livingNeighbors == 3);
        } else {
          nextBoard.setCell(row, col, livingNeighbors == 3);
        }
      }
    }

    LOGGER.debug("Next board {}", nextBoard);

    board = nextBoard;

    return currentBoard();
  }

  /**
   * Determine current liveliness of the neighborhood of a given cell at (y, x).
   * All we do here is count. Some logic needs to account for a cell being near an
   * edge and having no living neighbors on that side, which is the rule defining
   * this type of rule.
   *
   * @param row which row is the center of the neighborhood
   * @param col which column is the of the neighborhood
   * @return count of living neighbors
   */
  private int countNeighborhoodLives(final int row, final int col) {
    var liveCount = 0;

    final var previousRow = row - 1;
    final var leftStart = Math.max(0, col - 1);
    final var rightEnd = Math.min(col + 1, board.getCols() - 1);

    if (previousRow >= 0) {
      for (var currCol = leftStart; currCol <= rightEnd; currCol++) {
        liveCount += board.cellAt(previousRow, currCol) ? 1 : 0;
      }
    }

    for (var currCol = leftStart; currCol <= rightEnd; currCol++) {
      liveCount += col != currCol && board.cellAt(row, currCol) ? 1 : 0;
    }

    final var nextRow = row + 1;
    if (nextRow < board.getRows()) {
      for (var currCol = leftStart; currCol <= rightEnd; currCol++) {
        liveCount += board.cellAt(nextRow, currCol) ? 1 : 0;
      }
    }

    return liveCount;
  }

  @Override
  public Board currentBoard() {
    return new Board(this.board);
  }
}