/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.coolcow.arpiti.backend.entities.Coordinate;
import org.coolcow.arpiti.backend.entities.Item;

/**
 *
 * @author jruiz
 */
public class ReadWriteRptLine extends AbstractRptLine {

    private boolean typeGear = false;
    private boolean typeDate = false;
    private String skinName;
    private int hiveId;
    private String unknownValue1;
    private boolean unknownValue2;
    private int unknownValue3;
    private int unknownValue4;
    private int unknownValue5;
    private int unknownValue6;
    private double versionNumber;
    private Coordinate coordinate;    
    private List<Item> toolbelt;
    private List<Item> inventory = new ArrayList<>();
    private List<Item> backpack = new ArrayList<>();
    private Date date;
    
    private String backpackType;
            
    public ReadWriteRptLine() {
        super();
    }

    public boolean isTypeGear() {
        return typeGear;
    }

    public void setTypeGear(final boolean typeGear) {
        this.typeGear = typeGear;
    }

    public boolean isTypeDate() {
        return typeDate;
    }

    public void setTypeDate(final boolean typeDate) {
        this.typeDate = typeDate;
    }
     
    public String getSkinName() {
        return skinName;
    }

    public void setSkinName(final String skinName) {
        this.skinName = skinName;
    }

    public int getHiveId() {
        return hiveId;
    }

    public void setHiveId(final int hiveId) {
        this.hiveId = hiveId;
    }
    
    public double getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(final double versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getUnknownValue1() {
        return unknownValue1;
    }

    public void setUnknownValue1(final String unknownValue1) {
        this.unknownValue1 = unknownValue1;
    }

    public boolean isUnknownValue2() {
        return unknownValue2;
    }

    public void setUnknownValue2(final boolean unknownValue2) {
        this.unknownValue2 = unknownValue2;
    }

    public int getUnknownValue3() {
        return unknownValue3;
    }

    public void setUnknownValue3(final int unknownValue3) {
        this.unknownValue3 = unknownValue3;
    }

    public int getUnknownValue4() {
        return unknownValue4;
    }

    public void setUnknownValue4(final int unknownValue4) {
        this.unknownValue4 = unknownValue4;
    }

    public int getUnknownValue5() {
        return unknownValue5;
    }

    public void setUnknownValue5(final int unknownValue5) {
        this.unknownValue5 = unknownValue5;
    }

    public int getUnknownValue6() {
        return unknownValue6;
    }

    public void setUnknownValue6(final int unknownValue6) {
        this.unknownValue6 = unknownValue6;
    }

    public String getBackpackType() {
        return backpackType;
    }

    public void setBackpackType(final String backpackType) {
        this.backpackType = backpackType;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(final Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Date getDate() {
        return date;
    }
    
    public void setDate(final Date date) {
        this.date = date;
    }
    
    public List<Item> getToolbelt() {
        if (toolbelt == null) {
            return null;
        } else {
            return new ArrayList<>(toolbelt);
        }
    }

    public void setToolbelt(final List<Item> toolbelt) {
        if (toolbelt == null) {
            this.toolbelt = null;
        } else {
            this.toolbelt = new ArrayList<>(toolbelt);
        }
    }
    
    public List<Item> getInventory() {
        if (inventory == null) {
            return null;
        } else {
            return new ArrayList<>(inventory);
        }        
    }

    public void setInventory(final List<Item> inventory) {
        if (inventory == null) {
            this.inventory = null;
        } else {
            this.inventory = new ArrayList<>(inventory);
        }
    }

    public List<Item> getBackpack() {
        if (backpack == null) {
            return null;
        } else {
            return new ArrayList<>(backpack);
        }        
    }

    public void setBackpack(final List<Item> backpack) {
        if (backpack == null) {
            this.backpack = null;
        } else {
            this.backpack = new ArrayList<>(backpack);
        }
        this.backpack = new ArrayList<>(backpack);
    }
    
}
