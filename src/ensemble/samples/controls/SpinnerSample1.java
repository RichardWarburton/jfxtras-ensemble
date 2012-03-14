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
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import jfxtras.labs.scene.control.*;
import jfxtras.labs.util.StringConverterFactory;

/**
 * The basic forms of the spinner: list, editable list, integer
 * The way they are constructed (using "with") probably will be replaced with a builder.
 *
 * @see jfxtras.labs.scene.control.Spinner
 */
public class SpinnerSample1 extends Sample {

    public SpinnerSample1() {
        super(300, 300);

        VBox lVBox = new VBox(3.0);
        
        // simple cyclic spinner on an array of strings
        {
            Spinner<String> lSpinner = new Spinner<String>("first", "second", "third")
                                    .withCyclic(true)
                                    ;
            lVBox.getChildren().add(lSpinner);
        }
        
        // editable cycle spinner
        {
            final ObservableList<String> lObservableList = FXCollections.observableArrayList("a", "b", "c", "d", "e");
            Spinner<String> lSpinner = new Spinner<String>( lObservableList )
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
            lVBox.getChildren().add(lSpinner);
        }
        
        // integer spinner
        {
            Spinner<Integer> lSpinner = new Spinner<Integer>(new SpinnerIntegerList(10, 110));
            lVBox.getChildren().add(lSpinner);
        }

        getChildren().add(lVBox);
    }
    
        
    protected Spinner shallowClone(Spinner s1)
    {
        Spinner s2 = new Spinner(s1.getItems());
        s2.setEditable(s1.isEditable());
        s2.setCyclic(s1.isCyclic());
        s2.setStringConverter(s1.getStringConverter());
        s2.setAddCallback(s1.getAddCallback());
        return s2;
    }

}
