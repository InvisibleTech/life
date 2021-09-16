package org.invisibletech.life.board;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * The core ADT of it all, the cell matrix. The design intention here is to
 * group operations that belong to the ADT here.
 */
public class Board {
  private final Boolean[][] cells;
  private final int rows;
  private final int cols;

  /**
   * Create a new Board from given one. Deep copy mutable data.
   *
   * @param src source to copy
   */
  public Board(final Board src) {
    this(src.cells);
  }

  /**
   * Attempt to create a rectangular matrix from array of arrays. If you give it
   * something where not all rows are the same size you get an exception.
   *
   * @param matrix source matrix for dimensions and data.
   * @throws IndexOutOfBoundsException if source matrix is not regular
   */
  public Board(final Boolean[][] matrix) {
    this.rows = matrix.length;
    this.cols = matrix.length > 0 ? matrix[0].length : 0;
    this.cells = new Boolean[this.rows][this.cols];
    IntStream.range(0, getRows()).forEach(r -> System.arraycopy(matrix[r], 0, this.cells[r], 0, getCols()));
  }

  public Boolean cellAt(final int row, final int col) {
    return cells[row][col];
  }

  public void setCell(final int row, final int col, final boolean value) {
    cells[row][col] = value;
  }

  public int getRows() {
    return rows;
  }

  public int getCols() {
    return cols;
  }

  /**
   * This method affords access to the data without letting it escape and be
   * abused. The array streams are safe and the Booleans are immutable.
   *
   * @return a stream of streams to represent the matrix.
   */
  public Stream<Stream<Boolean>> stream() {
    return Arrays.stream(cells).map(row -> Arrays.stream(row));
  }

  /**
   * @return a deep copy of cell state as a matrix of booleans.
   */
  public Boolean[][] matrixOf() {
    return stream().map(row -> row.toArray(Boolean[]::new)).toArray(Boolean[][]::new);
  }

  @Override
  public int hashCode() {
    final var prime = 31;
    var result = 1;
    result = prime * result + Arrays.deepHashCode(cells);
    result = prime * result + Objects.hash(cols, rows);
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final var other = (Board) obj;
    return Arrays.deepEquals(cells, other.cells) && cols == other.cols && rows == other.rows;
  }

  @Override
  public String toString() {
    return Arrays.toString(Arrays.stream(cells).map(Arrays::toString).toArray());
  }
}
