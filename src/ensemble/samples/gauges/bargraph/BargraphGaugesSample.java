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
package ensemble.samples.gauges.bargraph;

import ensemble.Sample;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import jfxtras.labs.scene.control.gauge.Led;
import jfxtras.labs.scene.control.gauge.LedBargraph;

/**
 * LedBargraph.
 *
 * @see jxftras.labs.scene.control.gauge.LedBargraph
 */
public class BargraphGaugesSample extends Sample {
    private static final Random  RND          = new Random();
    private static final long    DATA_PERIOD  = 100000000l;
    private long                 lastDataCall = 0;
    private LedBargraph          bargraph1;
    private LedBargraph          bargraph2;
    private LedBargraph          bargraph3;
    private LedBargraph          bargraph4;

    private final AnimationTimer TIMER        = new AnimationTimer() {
        @Override
        public void handle(long l) {
            long currentNanoTime = System.nanoTime();
            if (currentNanoTime > lastDataCall + DATA_PERIOD) {
                bargraph1.setValue(RND.nextDouble());
                bargraph2.setValue(RND.nextDouble());
                bargraph3.setValue(RND.nextDouble());
                bargraph4.setValue(RND.nextDouble());
                lastDataCall = System.nanoTime();
            }
        }
    };

    public BargraphGaugesSample() {
        super(600, 600);

        StackPane stack = new StackPane();
        Rectangle background = new Rectangle(600, 600);
        background.setFill(Color.rgb(30, 30, 30));
        stack.getChildren().add(background);

        // Create some controls
        bargraph1 = new LedBargraph();
        bargraph1.setOrientation(Orientation.VERTICAL);
        bargraph1.setLedType(Led.Type.VERTICAL);
        bargraph1.setPeakValueVisible(true);

        bargraph2 = new LedBargraph();
        bargraph2.setOrientation(Orientation.HORIZONTAL);
        bargraph2.setLedType(Led.Type.HORIZONTAL);
        bargraph2.setPeakValueVisible(true);

        bargraph3 = new LedBargraph();
        bargraph3.setOrientation(Orientation.HORIZONTAL);
        bargraph3.setLedType(Led.Type.ROUND);
        bargraph3.setPeakValueVisible(true);

        bargraph4 = new LedBargraph();
        bargraph4.setOrientation(Orientation.VERTICAL);
        bargraph4.setLedType(Led.Type.SQUARE);
        bargraph4.setPeakValueVisible(true);

        // Layout
        final GridPane pane = new GridPane();
        pane.setPadding(new Insets(5));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setAlignment(Pos.TOP_CENTER);

        // Add controls to the layout
        pane.add(bargraph1, 1, 1);
        GridPane.setRowSpan(bargraph1, 2);
        pane.add(bargraph2, 2, 1);
        pane.add(bargraph3, 2, 2);
        pane.add(bargraph4, 3, 1);
        GridPane.setRowSpan(bargraph4, 2);

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