package org.invisibletech.life.publisher;

import org.invisibletech.life.board.Board;

/**
 * Publishing a board, at minimum means, writing it somewhere. Since there is
 * only one method this is also defined as a {@link FunctionalInterface} to
 * support using lambdas.
 */
@FunctionalInterface
public interface BoardPublisher {
  void render(Board board);
}
