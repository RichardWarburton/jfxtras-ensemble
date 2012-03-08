/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ensemble.samples.gauges.flipchar;

import ensemble.Sample;
import java.util.Calendar;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import jfxtras.labs.scene.control.gauge.FlipChar;

/**
 * Trafficlight.
 *
 * @see jxftras.labs.scene.control.gauge.Trafficlight
 */
public class FlipCharGaugesSample extends Sample {
    private static final Random  RND          = new Random();
    private static final long    TIME_PERIOD  = 1000000000l;
    private long                 lastTimeCall = 0;
    private FlipChar             flip1;
    private FlipChar             flip2;
    private FlipChar             flip3;
    private FlipChar             flip4;
    private FlipChar             flip5;
    private FlipChar             flip6;
    private final AnimationTimer TIMER        = new AnimationTimer() {
        @Override
        public void handle(long l) {
            long currentNanoTime = System.nanoTime();
            if (currentNanoTime > lastTimeCall + TIME_PERIOD) {
                setClock();
                lastTimeCall = System.nanoTime();
            }
        }
    };

    public FlipCharGaugesSample() {
        super(600, 600);

        // Create some controls
        flip1 = new FlipChar();
        flip1.setPrefSize(50, 100);
        flip1.setType(FlipChar.Type.TIME);

        flip2 = new FlipChar();
        flip2.setPrefSize(50, 100);

        flip3 = new FlipChar();
        flip3.setPrefSize(50, 100);
        flip3.setType(FlipChar.Type.TIME);

        flip4 = new FlipChar();
        flip4.setPrefSize(50, 100);

        flip5 = new FlipChar();
        flip5.setPrefSize(50, 100);
        flip5.setType(FlipChar.Type.TIME);
        flip5.setColor(Color.rgb(220, 20, 0));

        flip6 = new FlipChar();
        flip6.setPrefSize(50, 100);
        flip6.setColor(Color.rgb(220, 20, 0));

        // Layout
        final GridPane pane = new GridPane();
        pane.setPadding(new Insets(5));
        pane.setHgap(5);
        pane.setVgap(5);
        pane.setAlignment(Pos.TOP_CENTER);

        // Add controls to the layout
        pane.add(flip1, 1, 1);
        pane.add(flip2, 2, 1);
        pane.add(flip3, 6, 1);
        pane.add(flip4, 7, 1);
        pane.add(flip5, 11, 1);
        pane.add(flip6, 12, 1);

        getChildren().add(pane);
    }

    private void setClock() {
        Calendar cal = Calendar.getInstance();
        int hh = cal.get(Calendar.HOUR_OF_DAY);
        int mm = cal.get(Calendar.MINUTE);
        int ss = cal.get(Calendar.SECOND);

        // Hours
        if (hh < 10) {
            flip1.setCharacter("0");
            flip2.setCharacter(Integer.toString(hh));
        } else {
            flip1.setCharacter(Integer.toString(hh).substring(0, 1));
            flip2.setCharacter(Integer.toString(hh).substring(1, 2));
        }

        // Minutes
        if (mm < 10) {
            flip3.setCharacter("0");
            flip4.setCharacter(Integer.toString(mm));
        } else {
            flip3.setCharacter(Integer.toString(mm).substring(0, 1));
            flip4.setCharacter(Integer.toString(mm).substring(1, 2));
        }

        // Seconds
        if (ss < 10) {
            flip5.setCharacter("0");
            flip6.setCharacter(Integer.toString(ss));
        } else {
            flip5.setCharacter(Integer.toString(ss).substring(0, 1));
            flip6.setCharacter(Integer.toString(ss).substring(1, 2));
        }
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