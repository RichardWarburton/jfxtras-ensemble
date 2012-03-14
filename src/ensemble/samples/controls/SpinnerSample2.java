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
import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.util.Callback;
import jfxtras.labs.scene.control.*;
import jfxtras.labs.util.StringConverterFactory;

/**
 * The same spinners as Sample1, with arrows laid out in all kinds of different ways: horizontal or vertical direction, and leading, trailing or split positions.
 * (The vertical split layout also uses alignment CENTER.)
 *
 * @see jfxtras.labs.scene.control.Spinner
 */
public class SpinnerSample2 extends SpinnerSample1 {

    public SpinnerSample2() {
        super();

        // get what we have done in super, then clear
        VBox lInitialVBox = (VBox)getChildren().get(0);
        getChildren().clear();
        
        // create a new horizontal box
        HBox lHBox = new HBox(10.0);

        // column 1
        {
            // create a new horizontal box
            VBox lColumnVBox = new VBox(10.0);
            
            // TRAILING: add what we have done in super again
            lColumnVBox.getChildren().add(lInitialVBox);

            // LEADING
            {
                VBox lVBox = new VBox(3.0);
                for (Node lNode : lInitialVBox.getChildren())
                {
                    Spinner lSpinner = shallowClone((Spinner)lNode);
                    lSpinner.setArrowPosition(Spinner.ArrowPosition.LEADING);
                    lVBox.getChildren().add(lSpinner);
                }
                lColumnVBox.getChildren().add(lVBox);
            }

            // SPLIT
            {
                VBox lVBox = new VBox(3.0);
                for (Node lNode : lInitialVBox.getChildren())
                {
                    Spinner lSpinner = shallowClone((Spinner)lNode);
                    lSpinner.setArrowPosition(Spinner.ArrowPosition.SPLIT);
                    lVBox.getChildren().add(lSpinner);
                }
                lColumnVBox.getChildren().add(lVBox);
            }
            lHBox.getChildren().add(lColumnVBox);
        }
        
        // column 2
        {
            // create a new horizontal box
            VBox lColumnVBox = new VBox(10.0);
            
            // VERTICAL TRAILING
            {
                VBox lVBox = new VBox(3.0);
                for (Node lNode : lInitialVBox.getChildren())
                {
                    Spinner lSpinner = shallowClone((Spinner)lNode);
                    lSpinner.setArrowDirection(Spinner.ArrowDirection.VERTICAL);
                    lVBox.getChildren().add(lSpinner);
                }
                lColumnVBox.getChildren().add(lVBox);
            }
            // VERTICAL LEADING
            {
                VBox lVBox = new VBox(3.0);
                for (Node lNode : lInitialVBox.getChildren())
                {
                    Spinner lSpinner = shallowClone((Spinner)lNode);
                    lSpinner.setArrowDirection(Spinner.ArrowDirection.VERTICAL);
                    lSpinner.setArrowPosition(Spinner.ArrowPosition.LEADING);
                    lVBox.getChildren().add(lSpinner);
                }
                lColumnVBox.getChildren().add(lVBox);
            }
            // VERTICAL SPLIT
            {
                VBox lVBox = new VBox(3.0);
                for (Node lNode : lInitialVBox.getChildren())
                {
                    Spinner lSpinner = shallowClone((Spinner)lNode);
                    lSpinner.setArrowDirection(Spinner.ArrowDirection.VERTICAL);
                    lSpinner.setArrowPosition(Spinner.ArrowPosition.SPLIT);
                    lSpinner.setAlignment(Pos.CENTER);
                    lVBox.getChildren().add(lSpinner);
                }
                lColumnVBox.getChildren().add(lVBox);
            }
            lHBox.getChildren().add(lColumnVBox);
        }

        getChildren().add(lHBox);        
        //ScrollPane lScrollPane = new ScrollPane();
        //lScrollPane.setContent(lHBox);
        //getChildren().add(lScrollPane);        
    }
}
