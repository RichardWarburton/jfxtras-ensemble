/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ensemble.samples.gauges.stepindicator;

import ensemble.Sample;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import jfxtras.labs.scene.control.gauge.StepIndicator;
import jfxtras.labs.scene.control.gauge.StepIndicatorBuilder;


/**
 *
 * @author hansolo
 */
public class StepIndicatorGaugesSample extends Sample {
    private static final Color[] COLORS = {
        Color.rgb(138, 205, 250),
        Color.rgb(139, 253, 159),
        Color.rgb(238, 118, 118),
        Color.rgb(156, 142, 255),
        Color.rgb(253, 255, 131)
    };
    private static final Random  RND      = new Random();
    private static final long    PERIOD   = 3000000000l;
    private long                 lastCall = 0;
    final StepIndicator          STEPS    = new StepIndicatorBuilder().create()
                                                                      .noOfSteps(6)
                                                                      .build();
    private final AnimationTimer TIMER    = new AnimationTimer() {
        @Override
        public void handle(long l) {
            long currentNanoTime = System.nanoTime();
            if (currentNanoTime > lastCall + PERIOD) {
                int index = RND.nextInt(5);
                STEPS.setColor(COLORS[index]);
                lastCall = System.nanoTime();
            }
        }
    };

    public StepIndicatorGaugesSample() {
        super(600, 600);

        STEPS.setOnStepEvent(new EventHandler<StepIndicator.StepEvent>() {
            @Override
            public void handle(StepIndicator.StepEvent stepEvent) {
                STEPS.setCurrentStep(stepEvent.getIndex());
            }
        });

        Button back = new Button("back");
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                STEPS.back();
            }
        });

        Button next = new Button("next");
        next.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                STEPS.next();
            }
        });

        // Layout
        final GridPane pane = new GridPane();
        pane.setPadding(new Insets(5));
        pane.setHgap(20);
        pane.setVgap(20);
        pane.setAlignment(Pos.TOP_CENTER);

        // Add controls to the layout
        pane.add(STEPS, 1, 1);
        GridPane.setColumnSpan(STEPS, 2);
        pane.add(back, 1, 2);
        pane.add(next, 2, 2);
        GridPane.setHalignment(next, HPos.RIGHT);

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
