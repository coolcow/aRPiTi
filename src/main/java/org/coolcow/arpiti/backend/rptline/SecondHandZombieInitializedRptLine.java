/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline;

import org.coolcow.arpiti.backend.entities.Coordinate;

/**
 *
 * @author jruiz
 */
public class SecondHandZombieInitializedRptLine extends AbstractRptLine {

    private Coordinate coordinate;
    private String identifier;
    private boolean unknownValue;
            
    public SecondHandZombieInitializedRptLine() {
        super();
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public boolean isUnknownValue() {
        return unknownValue;
    }

    public void setUnknownValue(boolean unknownValue) {
        this.unknownValue = unknownValue;
    }        

}
