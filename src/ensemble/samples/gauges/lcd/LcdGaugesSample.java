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

package ensemble.samples.gauges.lcd;

import ensemble.Sample;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import jfxtras.labs.scene.control.gauge.Lcd;
import jfxtras.labs.scene.control.gauge.LcdDesign;
import jfxtras.labs.scene.control.gauge.StyleModel;
import jfxtras.labs.scene.control.gauge.StyleModelBuilder;

/**
 * Lcd gauges.
 *
 * @see jxftras.labs.scene.control.gauge.Lcd
 */
public class LcdGaugesSample extends Sample {
    private static final Random  RND          = new Random();
    private static final long    DATA_PERIOD  = 2500000000l;

    private Lcd                  lcd1;
    private Lcd                  lcd2;
    private Lcd                  lcd3;
    private Lcd                  lcd4;

    private long                 lastDataCall = 0;

    private final AnimationTimer TIMER        = new AnimationTimer() {
        @Override
        public void handle(long l) {
            long currentNanoTime = System.nanoTime();
            if (currentNanoTime > lastDataCall + DATA_PERIOD) {
                lcd1.setValue(RND.nextDouble() * 100);
                lcd2.setValue(RND.nextDouble() * 100);
                lcd3.setValue(RND.nextDouble() * 100);
                lcd4.setValue(RND.nextDouble() * 100);
                lastDataCall = System.nanoTime();
            }
        }
    };

    // Create some controls
    private StyleModel STYLE_MODEL_1 = new StyleModelBuilder().create()
        .lcdDesign(LcdDesign.STANDARD_GREEN)
        .lcdDigitalFontEnabled(true)
        .lcdUnitStringVisible(true)
        .lcdThresholdVisible(true)
        .build();

    private StyleModel STYLE_MODEL_2 = new StyleModelBuilder().create()
        .lcdDesign(LcdDesign.DARK_BLUE)
        .lcdDecimals(3)
        .lcdNumberSystemVisible(true)
        .build();

    private StyleModel STYLE_MODEL_3 = new StyleModelBuilder().create()
        .lcdDesign(LcdDesign.DARK_AMBER)
        .lcdDecimals(3)
        .lcdDigitalFontEnabled(true)
        .build();

    public LcdGaugesSample() {
        super(600, 600);

        // Create some controls
        lcd1 = new Lcd(STYLE_MODEL_1);
        lcd1.setThreshold(40);
        //lcd1.setBargraphVisible(true);
        lcd1.setLcdMinMeasuredValueDecimals(3);
        lcd1.setLcdMaxMeasuredValueDecimals(3);
        lcd1.setPrefSize(250, 70);

        lcd2 = new Lcd(STYLE_MODEL_2);
        lcd2.setThreshold(30);
        lcd2.setPrefSize(250, 70);

        lcd3 = new Lcd(STYLE_MODEL_3);
        lcd3.setThreshold(50);
        lcd3.setPrefSize(250, 70);

        lcd4 = new Lcd();
        lcd4.setLcdDesign(LcdDesign.DARK_GREEN);
        lcd4.setThreshold(50);
        lcd4.setPrefSize(250, 70);

        // Layout
        final GridPane pane = new GridPane();
        pane.setPadding(new Insets(5));
        pane.setHgap(20);
        pane.setVgap(20);
        pane.setAlignment(Pos.TOP_CENTER);

        // Add controls to the layout
        pane.add(lcd1, 1, 1);
        pane.add(lcd2, 2, 1);
        pane.add(lcd3, 1, 2);
        pane.add(lcd4, 2, 2);

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
