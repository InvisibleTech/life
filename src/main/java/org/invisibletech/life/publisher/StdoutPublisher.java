package org.invisibletech.life.publisher;

import java.util.stream.Collectors;

import org.invisibletech.life.board.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The method of rendering the cells is to use stdout. This is done by mapping
 * true to '@' and false to '*'.
 */
public class StdoutPublisher implements BoardPublisher {
  public static final Logger LOGGER = LoggerFactory.getLogger(StdoutPublisher.class);

  @Override
  public void render(final Board board) {
    LOGGER.debug("Rendering board {}", board);

    System.out.print("\n<=========>\n");
    System.out.print(board.stream().map(s -> s.map(c -> c ? "@" : "*").collect(Collectors.joining()))
        .collect(Collectors.joining("\n")));
    System.out.printf("\n");
  }

}
