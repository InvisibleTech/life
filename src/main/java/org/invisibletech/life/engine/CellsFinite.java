package org.invisibletech.life.engine;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.IntStream;

import org.invisibletech.life.board.BoardFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * Represents a game board for the Game Of Life. It supports the computation of
 * the next board and returning a safe copy of the state. See method
 * documentation for details.
 *
 * This board is finite and cells beyond the border are treated as dead.
 *
 * @author johnferguson
 *
 */
public final class CellsFinite implements CellsEngine {
  private static final Logger LOGGER = LoggerFactory.getLogger(CellsFinite.class);

  private static final Function<boolean[][], String> DUMP_BOARD = (b) -> Arrays
      .toString(Arrays.stream(b).map(Arrays::toString).toArray());

  private final int width;
  private final int height;

  private boolean[][] board;

  @Autowired
  CellsFinite(@Value("${life.board.width:5}") final int width, @Value("${life.board.height:5}") final int height,
      final BoardFactory boardFactory) {
    LOGGER.info("Creating board with dimensions {} x {}", height, width);
    this.width = width;
    this.height = height;
    board = boardFactory.createBoard(height, width);
  }

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
   * @return a safe copy of the current board state.
   *
   */
  @Override
  public boolean[][] computeNextBoard() {
    final var nextBoard = new boolean[height][width];
    LOGGER.debug("Compute next board for {}", DUMP_BOARD.apply(board));

    for (var row = 0; row < height; row++) {
      for (var col = 0; col < width; col++) {
        final var livingNeighbors = countNeighborhood(row, col);
        LOGGER.debug("Living neighbors for [{},{}] == {}", row, col, livingNeighbors);

        if (board[row][col]) {
          nextBoard[row][col] = livingNeighbors == 2 || livingNeighbors == 3;
        } else {
          nextBoard[row][col] = livingNeighbors == 3;
        }
      }
    }

    LOGGER.debug("Next board {}", DUMP_BOARD.apply(nextBoard));

    board = nextBoard;

    // return safe copy of board to avoid giving accidental access to internal state
    return currentBoard();
  }

  /**
   * Determine current liveliness of the neighborhood of a given cell at (y, x).
   * All we do here is count. Some logic needs to account for a cell being near an
   * edge and having no neighbors.
   *
   * @param row which row is the center of the neighborhood
   * @param col which column is the of the neighborhood
   * @return count of living neighbors
   */
  private int countNeighborhood(final int row, final int col) {
    var liveCount = 0;

    final var previousRow = row - 1;
    final var leftStart = Math.max(0, col - 1);
    final var rightEnd = Math.min(col + 1, width - 1);

    if (previousRow >= 0) {
      for (var currCol = leftStart; currCol <= rightEnd; currCol++) {
        liveCount += board[previousRow][currCol] ? 1 : 0;
      }
    }

    for (var currCol = leftStart; currCol <= rightEnd; currCol++) {
      liveCount += board[row][currCol] && col != currCol ? 1 : 0;
    }

    final var nextRow = row + 1;
    if (nextRow < height) {
      for (var currCol = leftStart; currCol <= rightEnd; currCol++) {
        liveCount += board[nextRow][currCol] ? 1 : 0;
      }
    }

    return liveCount;
  }

  @Override
  public boolean[][] currentBoard() {
    final var safeCopy = new boolean[height][width];

    IntStream.range(0, height).forEach(r -> System.arraycopy(board[r], 0, safeCopy[r], 0, width));

    return safeCopy;
  }
}