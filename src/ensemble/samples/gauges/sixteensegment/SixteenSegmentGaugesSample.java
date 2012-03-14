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
package ensemble.samples.gauges.sixteensegment;

import ensemble.Sample;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import jfxtras.labs.scene.control.gauge.SixteenSegment;



/**
 * SixteenSegment.
 *
 * @see jxftras.labs.scene.control.gauge.SixteenSegment
 */
public class SixteenSegmentGaugesSample extends Sample {
    private static final Color[] COLORS        = {
        Color.rgb(255, 50, 0),
        Color.rgb(119, 221, 1),
        Color.rgb(77, 171, 219),
        Color.YELLOW,
        Color.rgb(227, 0, 74),
        Color.LIGHTBLUE,
        Color.rgb(255, 126, 18),
        Color.LIGHTSTEELBLUE,
        Color.CYAN,
        Color.DARKSEAGREEN,
        Color.WHITE
    };
    private static final String[] WORDS        = {
        "JFXTRAS",
        "Java FX",
        "JAVA ROCKS",
        "ABCDEFGHIJ",
        "KLMNOPQRST",
        "UVWXYZ*+-/",
        "0123456789"
    };
    private List<SixteenSegment> segments      = new ArrayList<SixteenSegment>(10);
    private static final Random  RND           = new Random();
    private static final long    INTERVAL      = 2000000000l;
    private long                 lastTimerCall = 0;
    private final AnimationTimer TIMER         = new AnimationTimer() {
        @Override
        public void handle(long l) {
            long currentNanoTime = System.nanoTime();
            if (currentNanoTime > lastTimerCall + INTERVAL) {
                Color color = COLORS[RND.nextInt(11)];
                String word = WORDS[RND.nextInt(7)];
                int counter = 0;
                for (SixteenSegment segment : segments) {
                    segment.setColor(color);
                    if (counter < word.length()) {
                        segment.setCharacter(word.charAt(counter));
                    } else {
                        segment.setCharacter(" ");
                    }
                    counter++;
                }
                lastTimerCall = System.nanoTime();
            }
        }
    };

    public SixteenSegmentGaugesSample() {
        super(600, 600);

        // Layout
        final GridPane pane = new GridPane();
        pane.setPadding(new Insets(5));
        pane.setHgap(0);
        pane.setVgap(5);
        pane.setAlignment(Pos.TOP_CENTER);

        final StackPane stack = new StackPane();
        Rectangle background = new Rectangle(600, 600);
        background.setFill(Color.rgb(30, 30, 30));
        stack.getChildren().add(background);

        // Create some controls and add them to the layout
        for (int i = 0 ; i < 10 ; i++) {
            SixteenSegment segment = new SixteenSegment();
            segment.setPrefSize(50, 100);
            segments.add(segment);
            pane.add(segment, i, 1);
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