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
package ensemble.samples.gauges.trafficlight;

import ensemble.Sample;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import jfxtras.labs.scene.control.gauge.Trafficlight;

/**
 * Trafficlight.
 *
 * @see jxftras.labs.scene.control.gauge.Trafficlight
 */
public class TrafficLightGaugesSample extends Sample {
    private static final Random  RND          = new Random();
    private static final long    DATA_PERIOD  = 2000000000l;
    private long                 lastDataCall = 0;
    private Trafficlight         trafficLight;
    private final AnimationTimer TIMER        = new AnimationTimer() {
        @Override
        public void handle(long l) {
            long currentNanoTime = System.nanoTime();
            if (currentNanoTime > lastDataCall + DATA_PERIOD) {
                int v = RND.nextInt(4);
                switch(v) {
                    case 0:
                        trafficLight.setRedOn(true);
                        trafficLight.setYellowOn(false);
                        trafficLight.setYellowBlinking(false);
                        trafficLight.setGreenOn(false);
                        break;
                    case 1:
                        trafficLight.setRedOn(false);
                        trafficLight.setYellowOn(true);
                        trafficLight.setYellowBlinking(false);
                        trafficLight.setGreenOn(false);
                        break;
                    case 2:
                        trafficLight.setRedOn(false);
                        trafficLight.setYellowOn(false);
                        trafficLight.setYellowBlinking(false);
                        trafficLight.setGreenOn(true);
                        break;
                    case 3:
                        trafficLight.setRedOn(false);
                        trafficLight.setYellowOn(false);
                        trafficLight.setYellowBlinking(true);
                        trafficLight.setGreenOn(false);
                        break;
                }
                lastDataCall = System.nanoTime();
            }
        }
    };

    public TrafficLightGaugesSample() {
        super(600, 600);

        // Create some controls
        trafficLight = new Trafficlight();

        // Layout
        final GridPane pane = new GridPane();
        pane.setPadding(new Insets(5));
        pane.setHgap(5);
        pane.setVgap(5);
        pane.setAlignment(Pos.TOP_CENTER);

        // Add controls to the layout
        pane.add(trafficLight, 1, 1);

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