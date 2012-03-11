/*
 * Copyright (c) 2008, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle Corporation nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package ensemble.samples.controls;

import ensemble.Sample;
import java.util.GregorianCalendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Callback;
import jfxtras.labs.scene.control.*;
import jfxtras.labs.util.StringConverterFactory;

/**
 * CalendarTextField and CalendarPicker.
 *
 * @see jfxtras.labs.scene.control.CalendarTextField
 */
public class SpinnerSample1 extends Sample {

    public SpinnerSample1() {
        super(300, 300);

        GridPane lGridPane = new GridPane();
        lGridPane.setVgap(5.0);
        ColumnConstraints column0 = new ColumnConstraints(10, 10, Double.MAX_VALUE);
        column0.setHgrow(Priority.ALWAYS);
        lGridPane.getColumnConstraints().addAll(column0);
        int lRowIdx = 0;
        
        // simple cyclic spinner on an array of strings
        {
            Spinner<String> lSpinner = new Spinner<String>("first", "second", "third")
                                    .withCyclic(true)
                                    ;
            lSpinner.setPrefWidth(100.0);
            lGridPane.add(lSpinner, 1, lRowIdx++);
        }
        
        // editable cycle spinner
        {
            final ObservableList<String> lObservableList = FXCollections.observableArrayList("a", "b", "c", "d", "e");
            Spinner<String> lXSpinner = new Spinner<String>( lObservableList )
                    .withCyclic(true)
                    .withEditable(true)
                    .withStringConverter(StringConverterFactory.forString())
                    .withAddCallback(new Callback<String, Integer>()
                    {					
                            @Override
                            public Integer call(String text)
                            {
                                    lObservableList.add(text);
                                    return lObservableList.size() - 1; // notify spinner the value is appended at the end
                            }
                    })
                    ;
            lGridPane.add(lXSpinner, 1, lRowIdx++);
        }
        
        // integer spinner
        {
            Spinner<Integer> lXSpinner = new Spinner<Integer>(new SpinnerIntegerList(10, 110));
            lGridPane.add(lXSpinner, 1, lRowIdx++);
        }

        getChildren().add(lGridPane);
    }
}
