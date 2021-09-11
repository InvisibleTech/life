package org.invisibletech.life.board;

/**
 * Defines the interface for generating a starting board of given dimensions. As
 * to what that means is pretty open. Since there is only one method this is
 * also defined as a {@link FunctionalInterface} to support using lambdas.
 */
@FunctionalInterface
public interface BoardFactory {
  CellMat createBoard(int height, int width);
}
