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
package ensemble.samples.gauges.indicator;

import ensemble.Sample;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import jfxtras.labs.scene.control.gauge.SimpleIndicator;

/**
 * SimpleIndicator.
 *
 * @see jxftras.labs.scene.control.gauge.SimpleIndicator
 */
public class IndicatorGaugesSample extends Sample {
    private static final Random  RND          = new Random();
    private static final Color[] STATE_COLORS = {
        Color.rgb(180, 180, 180),
        Color.rgb(180, 0, 0),
        Color.rgb(180, 180, 0),
        Color.rgb(0, 180, 0),
        Color.rgb(0, 0, 180)
    };
    private static final String[] STATE_TEXTS = {
        "System offline",
        "Mission critical",
        "Warning",
        "All systems nominal",
        "Undefined"
    };
    private static final long    DATA_PERIOD  = 2000000000l;
    private long                 lastDataCall = 0;
    private SimpleIndicator      indicator1;
    private SimpleIndicator      indicator2;
    private SimpleIndicator      indicator3;
    private SimpleIndicator      indicator4;
    private Text                 statusText1;
    private Text                 statusText2;
    private Text                 statusText3;
    private Text                 statusText4;

    private final AnimationTimer TIMER        = new AnimationTimer() {
        @Override
        public void handle(long l) {
            long currentNanoTime = System.nanoTime();
            if (currentNanoTime > lastDataCall + DATA_PERIOD) {
                int state = RND.nextInt(5);
                indicator1.setInnerColor(STATE_COLORS[state].brighter());
                indicator1.setOuterColor(STATE_COLORS[state].darker());
                statusText1.setText(STATE_TEXTS[state]);

                state = RND.nextInt(5);
                indicator2.setInnerColor(STATE_COLORS[state].brighter());
                indicator2.setOuterColor(STATE_COLORS[state].darker());
                statusText2.setText(STATE_TEXTS[state]);

                state = RND.nextInt(5);
                indicator3.setInnerColor(STATE_COLORS[state].brighter());
                indicator3.setOuterColor(STATE_COLORS[state].darker());
                statusText3.setText(STATE_TEXTS[state]);

                state = RND.nextInt(5);
                indicator4.setInnerColor(STATE_COLORS[state].brighter());
                indicator4.setOuterColor(STATE_COLORS[state].darker());
                statusText4.setText(STATE_TEXTS[state]);

                lastDataCall = System.nanoTime();
            }
        }
    };

    public IndicatorGaugesSample() {
        super(600, 600);

        // Create some controls
        indicator1 = new SimpleIndicator();
        indicator1.setInnerColor(STATE_COLORS[0].brighter());
        indicator1.setOuterColor(STATE_COLORS[0].darker());
        indicator1.setGlowVisible(false);

        statusText1 = new Text("system offline");

        indicator2 = new SimpleIndicator();
        indicator2.setInnerColor(STATE_COLORS[0].brighter());
        indicator2.setOuterColor(STATE_COLORS[0].darker());
        indicator2.setGlowVisible(false);

        statusText2 = new Text("system offline");

        indicator3 = new SimpleIndicator();
        indicator3.setInnerColor(STATE_COLORS[0].brighter());
        indicator3.setOuterColor(STATE_COLORS[0].darker());
        indicator3.setGlowVisible(false);

        statusText3 = new Text("system offline");

        indicator4 = new SimpleIndicator();
        indicator4.setInnerColor(STATE_COLORS[0].brighter());
        indicator4.setOuterColor(STATE_COLORS[0].darker());
        indicator4.setGlowVisible(false);

        statusText4 = new Text("system offline");

        // Layout
        final GridPane pane = new GridPane();
        pane.setPadding(new Insets(5));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setAlignment(Pos.TOP_CENTER);

        // Add controls to the layout
        pane.add(indicator1, 1, 1);
        pane.add(statusText1, 2, 1);
        pane.add(indicator2, 1, 2);
        pane.add(statusText2, 2, 2);
        pane.add(indicator3, 1, 3);
        pane.add(statusText3, 2, 3);
        pane.add(indicator4, 1, 4);
        pane.add(statusText4, 2, 4);

        getChildren().add(pane);
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