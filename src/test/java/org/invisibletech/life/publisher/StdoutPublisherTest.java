package org.invisibletech.life.publisher;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class StdoutPublisherTest {

  @Test
  void renderBoardToStdout() {
    final var captureStdOut = new ByteArrayOutputStream();
    final var oldOut = System.out;

    System.setOut(new PrintStream(captureStdOut));

    new StdoutPublisher().render(new boolean[][] {
        {
            true, false
        }, {
            false, true
        }
    });

    System.setOut(oldOut);

    assertEquals("\n<=========>\n@*\n*@\n", captureStdOut.toString());
  }

}
