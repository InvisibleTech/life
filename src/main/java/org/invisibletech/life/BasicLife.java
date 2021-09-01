package org.invisibletech.life;

import org.invisibletech.life.engine.CellsEngine;
import org.invisibletech.life.publisher.BoardPublisher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * A Non-concurrent Game of Life.
 * 
 * @author johnferguson
 *
 */
public class BasicLife {
  public static void main(final String[] args) {
    try (final ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
        "classpath:spring/basic_application_context.xml");) {
      final var cellScape = context.getBean(CellsEngine.class);
      final var publisher = context.getBean(BoardPublisher.class);
      publisher.render(cellScape.currentBoard());
      for (var i = 0; i < 30; i++) {
        publisher.render(cellScape.computeNextBoard());
      }
    }
  }
}
