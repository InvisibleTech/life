package org.invisibletech.life.publisher;

@FunctionalInterface
public interface BoardPublisher {
  void render(boolean[][] board);
}
