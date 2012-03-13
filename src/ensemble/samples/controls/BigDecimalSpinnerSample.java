/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ensemble.samples.controls;

import ensemble.Sample;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import jfxtras.labs.scene.control.NumberSpinner;

/**
 * A spinner control that holds arbitrary formatted BigDecimal numbers. Three
 * different styling examples are provided.
 *
 * @see jfxtras.labs.scene.control.NumberSpinner
 * @see jfxtras.labs.scene.control.NumberTextField
 * @see jfxtras.labs.scene.control.NumberLabel
 */
public class BigDecimalSpinnerSample extends Sample {

    public BigDecimalSpinnerSample() {
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(10, 10, 10, 10));
        final NumberSpinner defaultSpinner = new NumberSpinner();
        final NumberSpinner decimalFormat = new NumberSpinner(BigDecimal.ZERO, new BigDecimal("0.05"), new DecimalFormat("#,##0.00"));
        final NumberSpinner percent = new NumberSpinner(BigDecimal.ZERO, new BigDecimal("0.01"), NumberFormat.getPercentInstance());
        final NumberSpinner localizedCurrency = new NumberSpinner(BigDecimal.ZERO, new BigDecimal("0.01"), NumberFormat.getCurrencyInstance(Locale.UK));
        root.addRow(1, new Label("default"), defaultSpinner);
        root.addRow(2, new Label("custom decimal format"), decimalFormat);
        root.addRow(3, new Label("percent"), percent);
        root.addRow(4, new Label("localized currency"), localizedCurrency);

        // TODO: Fix unwanted resizing behaviour.
        final ChoiceBox styles = new ChoiceBox(FXCollections.observableArrayList("squared", "rounded", "thommis"));
        styles.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                System.out.println("index" + arg2.intValue());
                Scene scene = styles.getScene();
                String pathSquared = NumberSpinner.class.getResource("NumberSpinnerSquared.css").toExternalForm();
                String pathRounded = NumberSpinner.class.getResource("NumberSpinnerRounded.css").toExternalForm();
                String pathThommis = NumberSpinner.class.getResource("NumberSpinnerThommis.css").toExternalForm();
                scene.getStylesheets().removeAll(pathSquared, pathRounded, pathThommis);
                if (arg2.intValue() == 0) {
                    scene.getStylesheets().add(pathSquared);
                }
                if (arg2.intValue() == 1) {
                    scene.getStylesheets().add(pathRounded);
                }
                if (arg2.intValue() == 2) {
                    scene.getStylesheets().add(pathThommis);
                }
            }
        });

        root.addRow(5, new Label("change css"), styles);



        getChildren().add(root);
    }
}
