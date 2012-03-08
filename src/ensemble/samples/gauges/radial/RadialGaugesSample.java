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

package ensemble.samples.gauges.radial;

import ensemble.Sample;
import javafx.animation.AnimationTimer;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import jfxtras.labs.scene.control.gauge.ColorDef;
import jfxtras.labs.scene.control.gauge.Gauge;
import jfxtras.labs.scene.control.gauge.GaugeModel;
import jfxtras.labs.scene.control.gauge.Lcd;
import jfxtras.labs.scene.control.gauge.LcdDesign;
import jfxtras.labs.scene.control.gauge.LedColor;
import jfxtras.labs.scene.control.gauge.Radial;
import jfxtras.labs.scene.control.gauge.RadialHalfN;
import jfxtras.labs.scene.control.gauge.RadialQuarterN;
import jfxtras.labs.scene.control.gauge.StyleModel;
import jfxtras.labs.scene.control.gauge.StyleModelBuilder;

import java.util.Random;

/**
 * Radial gauges.
 * 
 * @see jxftras.labs.scene.control.gauge.Radial
 * @see jxftras.labs.scene.control.gauge.RadialQuarterN
 * @see jxftras.labs.scene.control.gauge.RadialHalfN
 */
public class RadialGaugesSample extends Sample {
    private static final Random     RND           = new Random();
    private static final long       DATA_PERIOD   = 2500000000l;
    
    private GaugeModel              gaugeModel    = new GaugeModel();
    private long                    lastDataCall  = 0;
    
    private final AnimationTimer    TIMER         = new AnimationTimer() {
        @Override
        public void handle(long l) {
            long currentNanoTime = System.nanoTime();
            if (currentNanoTime > lastDataCall + DATA_PERIOD) {
                gaugeModel.setValue(RND.nextDouble() * 100);
                lastDataCall = System.nanoTime();
            }
        }
    };

    public RadialGaugesSample() {
        super(600, 600);
        
        // Create some controls
        StyleModel STYLE_MODEL_1 = new StyleModelBuilder().create()
            .frameDesign(Gauge.FrameDesign.STEEL)
            .backgroundDesign(Gauge.BackgroundDesign.DARK_GRAY)
            .tickLabelOrientation(Gauge.TicklabelOrientation.HORIZONTAL)
            .pointerType(Gauge.PointerType.TYPE14)
            .thresholdColor(Gauge.ThresholdColor.RED)
            .thresholdVisible(true)
            .valueColor(ColorDef.ORANGE)
            .ledColor(LedColor.RED)
            .lcdDesign(LcdDesign.STANDARD_GREEN)
            .lcdThresholdVisible(true)
            .build();
        
        Radial radial1 = new Radial(gaugeModel, STYLE_MODEL_1);
        radial1.setThreshold(40);
        radial1.setPrefSize(250, 250);

        StyleModel STYLE_MODEL_2 = new StyleModelBuilder().create()
            .frameDesign(Gauge.FrameDesign.STEEL)
            .backgroundDesign(Gauge.BackgroundDesign.BLACK)
            .tickLabelOrientation(Gauge.TicklabelOrientation.TANGENT)
            .bargraph(true)
            .thresholdColor(Gauge.ThresholdColor.RED)
            .thresholdVisible(true)
            .valueColor(ColorDef.BLUE)
            .ledColor(LedColor.CYAN)
            .build();
    
        RadialHalfN radial2 = new RadialHalfN(gaugeModel, STYLE_MODEL_2);
        radial2.setThreshold(30);
        radial2.setPrefSize(250, 250);

        StyleModel STYLE_MODEL_3 = new StyleModelBuilder().create()
            .frameDesign(Gauge.FrameDesign.BRASS)
            .backgroundDesign(Gauge.BackgroundDesign.WHITE)
            .knobColor(Gauge.KnobColor.BRASS)
            .knobDesign(Gauge.KnobDesign.METAL)
            .tickLabelOrientation(Gauge.TicklabelOrientation.NORMAL)
            .pointerType(Gauge.PointerType.TYPE9)
            .thresholdColor(Gauge.ThresholdColor.ORANGE)
            .thresholdVisible(true)
            .valueColor(ColorDef.ORANGE)
            .ledColor(LedColor.ORANGE)
            .lcdDesign(LcdDesign.DARK_AMBER)
            .lcdDecimals(3)
            .lcdDigitalFontEnabled(true)
            .build();
    
        RadialQuarterN radial3 = new RadialQuarterN(gaugeModel, STYLE_MODEL_3);
        radial3.setThreshold(50);
        radial3.setPrefSize(250, 250);

        RadialHalfN radial4 = new RadialHalfN(gaugeModel, STYLE_MODEL_3);
        radial4.setThreshold(50);
        radial4.setPrefSize(250, 250);

        // Layout
        final GridPane pane = new GridPane();
        pane.setPadding(new Insets(5));
        pane.setHgap(5);
        pane.setVgap(5);
        pane.setAlignment(Pos.TOP_CENTER);

        // Add controls to the layout
        pane.add(radial1, 1, 1);
        pane.add(radial2, 2, 1);
        pane.add(radial3, 1, 2);
        pane.add(radial4, 2, 2);

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
