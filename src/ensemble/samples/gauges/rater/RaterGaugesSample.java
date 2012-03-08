/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ensemble.samples.gauges.rater;

import ensemble.Sample;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import jfxtras.labs.scene.control.gauge.Rater;

/**
 * Rater.
 *
 * @see jxftras.labs.scene.control.gauge.Rater
 */
public class RaterGaugesSample extends Sample {
    private static final Color[] COLORS = {
        Color.RED,
        Color.LIME,
        Color.YELLOW,
        Color.ORANGE,
        Color.BLUE
    };
    private static final Random  RND          = new Random();
    private static final long    DATA_PERIOD  = 3000000000l;
    private long                 lastDataCall = 0;
    private Rater                rater;
    private final AnimationTimer TIMER        = new AnimationTimer() {
        @Override
        public void handle(long l) {
            long currentNanoTime = System.nanoTime();
            if (currentNanoTime > lastDataCall + DATA_PERIOD) {
                int index = RND.nextInt(5);
                rater.setDarkColor(COLORS[index].darker());
                rater.setBrightColor(COLORS[index].brighter());
                lastDataCall = System.nanoTime();
            }
        }
    };

    public RaterGaugesSample() {
        super(600, 600);

        // Create some controls
        rater = new Rater();

        // Layout
        final GridPane pane = new GridPane();
        pane.setPadding(new Insets(5));
        pane.setHgap(5);
        pane.setVgap(5);
        pane.setAlignment(Pos.TOP_CENTER);

        // Add controls to the layout
        pane.add(rater, 1, 1);

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