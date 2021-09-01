package org.invisibletech.life.board;

@FunctionalInterface
public interface BoardFactory {
  boolean[][] createBoard(int height, int width);
}
