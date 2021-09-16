package org.invisibletech.life;

import org.invisibletech.life.game.Game;
import org.invisibletech.life.publisher.BoardPublisher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * A Game Of Life application using a loop of finite iterations. The main
 * purpose is to provide a simple way to a run some iterations of the game.
 * 
 * Use a brief sleep to make the output a little more like an animation.
 */
public class BasicLife {
  public static void main(final String[] args) throws InterruptedException {
    try (final ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
        "classpath:spring/basic_application_context.xml");) {
      final var game = context.getBean(Game.class);
      final var publisher = context.getBean(BoardPublisher.class);
      publisher.render(game.currentBoard());
      for (var i = 0; i < 30; i++) {
        Thread.sleep(1000);
        publisher.render(game.computeNextBoard());
      }
    }
  }
}
