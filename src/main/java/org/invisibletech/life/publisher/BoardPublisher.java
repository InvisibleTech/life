package org.invisibletech.life.publisher;

import org.invisibletech.life.board.CellMat;

/**
 * Publishing a board minimally means writing it somewhere and this interface is
 * pretty minimal. At this time it has one function and so could be met using a
 * lambda. This may be more useful in tests, but for now seems like a good idea.
 */
@FunctionalInterface
public interface BoardPublisher {
  void render(CellMat cellMat);
}
