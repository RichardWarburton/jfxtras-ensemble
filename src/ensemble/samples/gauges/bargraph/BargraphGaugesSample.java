/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ensemble.samples.gauges.bargraph;

import ensemble.Sample;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import jfxtras.labs.scene.control.gauge.Led;
import jfxtras.labs.scene.control.gauge.LedBargraph;

/**
 * LedBargraph.
 *
 * @see jxftras.labs.scene.control.gauge.LedBargraph
 */
public class BargraphGaugesSample extends Sample {
    private static final Random  RND          = new Random();
    private static final long    DATA_PERIOD  = 500000000l;
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