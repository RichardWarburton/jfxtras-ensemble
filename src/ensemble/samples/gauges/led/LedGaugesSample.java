/*
 * Copyright (c) 2012, JFXtras
 *   All rights reserved.
 *
 *   Redistribution and use in source and binary forms, with or without
 *   modification, are permitted provided that the following conditions are met:
 *       * Redistributions of source code must retain the above copyright
 *         notice, this list of conditions and the following disclaimer.
 *       * Redistributions in binary form must reproduce the above copyright
 *         notice, this list of conditions and the following disclaimer in the
 *         documentation and/or other materials provided with the distribution.
 *       * Neither the name of the <organization> nor the
 *         names of its contributors may be used to endorse or promote products
 *         derived from this software without specific prior written permission.
 *
 *   THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *   ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *   WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *   DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 *   DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *   (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *   LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *   ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *   (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *   SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package ensemble.samples.gauges.led;

import ensemble.Sample;
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import jfxtras.labs.scene.control.gauge.Led;
import jfxtras.labs.scene.control.gauge.LedBuilder;


/**
 * Led gauges.
 *
 * @see jxftras.labs.scene.control.gauge.Led
 */
public class LedGaugesSample extends Sample {
    private static final Random       RND          = new Random();
    private static final long         DATA_PERIOD  = 100000000l;
    private static final int          AMOUNT       = 10;
    private ArrayList<ArrayList<Led>> rows         = new ArrayList<ArrayList<Led>>(AMOUNT);

    private long                      lastDataCall = 0;

    private final AnimationTimer      TIMER        = new AnimationTimer() {
        @Override
        public void handle(long l) {
            long currentNanoTime = System.nanoTime();
            if (currentNanoTime > lastDataCall + DATA_PERIOD) {
                for (int row = 0 ; row < AMOUNT ; row++) {
                    for (int col = 0 ; col < AMOUNT ; col++) {
                        rows.get(row).get(col).setOn(RND.nextInt(2) == 1);
                    }
                }
                lastDataCall = System.nanoTime();
            }
        }
    };


    public LedGaugesSample() {
        super(600, 600);

        // Layout
        StackPane stack = new StackPane();
        Rectangle background = new Rectangle(600, 600);
        background.setFill(Color.rgb(30, 30, 30));
        stack.getChildren().add(background);

        final GridPane pane = new GridPane();
        pane.setPadding(new Insets(5));
        //pane.setHgap(2);
        //pane.setVgap(2);
        pane.setAlignment(Pos.TOP_CENTER);

        // Create some controls
        for (int row = 0 ; row < AMOUNT ; row++) {
            ArrayList<Led> column = new ArrayList<Led>(AMOUNT);
            for (int col = 0 ; col < AMOUNT ; col++) {
                int green = RND.nextInt(255);
                int blue = RND.nextInt(128) + 128;
                Led led = new LedBuilder().create().color(Color.rgb(0, green, blue)).build();
                column.add(led);
                pane.add(led, col, row);
            }
            rows.add(column);
        }
        stack.getChildren().add(pane);

        getChildren().add(stack);
    }

    @Override
    public void play() {
        TIMER.start();
    }

    @Override
    public void stop() {
        TIMER.stop();
    }
}
