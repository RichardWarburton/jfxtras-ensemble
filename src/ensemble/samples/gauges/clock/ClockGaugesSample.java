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
package ensemble.samples.gauges.clock;

import ensemble.Sample;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import jfxtras.labs.scene.control.gauge.Clock;
import jfxtras.labs.scene.control.gauge.ClockBuilder;


/**
 * Clock.
 *
 * @see jxftras.labs.scene.control.gauge.Clock
 */
public class ClockGaugesSample  extends Sample {

    public ClockGaugesSample() {
        super(600, 600);

        // Create some controls
        Clock clockPst  = new ClockBuilder().create().timeZone("US/Pacific").daylightSavingTime(false).running(true).build();
        clockPst.setPrefSize(120, 120);
        Clock clockEst  = new ClockBuilder().create().timeZone("EST").daylightSavingTime(true).running(true).build();
        clockEst.setPrefSize(120, 120);
        Clock clockCet  = new ClockBuilder().create().timeZone("CET").daylightSavingTime(false).running(true).build();
        clockCet.setPrefSize(120, 120);
        Clock clockNz = new ClockBuilder().create().timeZone("NZ").daylightSavingTime(false).running(true).build();
        clockNz.setPrefSize(120, 120);

        Text pst = new Text("San Francisco");
        pst.setFill(Color.WHITE);
        Text est = new Text("New York");
        est.setFill(Color.WHITE);
        Text cet = new Text("Berlin");
        cet.setFill(Color.WHITE);
        Text nzst = new Text("Wellington");
        nzst.setFill(Color.WHITE);

        StackPane stack = new StackPane();
        Rectangle background = new Rectangle(600, 600);
        background.setFill(Color.rgb(30, 30, 30));
        stack.getChildren().add(background);

        // Layout
        final GridPane pane = new GridPane();
        pane.setPadding(new Insets(5));
        pane.setHgap(5);
        pane.setVgap(5);
        pane.setAlignment(Pos.TOP_CENTER);

        // Add controls to the layout
        pane.add(clockPst, 1, 1);
        pane.add(pst, 1, 2);
        GridPane.setHalignment(pst, HPos.CENTER);
        pane.add(clockEst, 2, 1);
        pane.add(est, 2, 2);
        GridPane.setHalignment(est, HPos.CENTER);
        pane.add(clockCet, 3, 1);
        pane.add(cet, 3, 2);
        GridPane.setHalignment(cet, HPos.CENTER);
        pane.add(clockNz, 4, 1);
        pane.add(nzst, 4, 2);
        GridPane.setHalignment(nzst, HPos.CENTER);

        stack.getChildren().add(pane);

        getChildren().add(stack);
    }

    @Override
    public void play() {

    }

    @Override
    public void stop() {

    }
}