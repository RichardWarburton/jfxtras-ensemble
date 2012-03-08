/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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