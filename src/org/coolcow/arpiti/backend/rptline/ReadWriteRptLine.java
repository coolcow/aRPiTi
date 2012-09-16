/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

/**
 *
 * @author jruiz
 */
public class ReadWriteRptLine extends AbstractRptLine {

    private static final Logger LOG = Logger.getLogger(ReadWriteRptLine.class);
    
    private boolean typeA = false;
    private boolean typeB = false;
    private int playerId;
    private String skinName;
    private String unknownValue1;
    private boolean unknownValue2;
    private int unknownValue3;
    private double unknownValue4;
    private double unknownValue5;
    private double unknownValue6;
    private double versionNumber;
    private int unknownValue7;
    private int unknownValue8;
    private int unknownValue9;
    
    private List<Item> toolbelt = new ArrayList<>();
    private List<Item> chest = new ArrayList<>();
    private List<Item> backpack = new ArrayList<>();
    
    private String backpackType;
            
    public ReadWriteRptLine() {
        super();
    }

    public boolean isTypeA() {
        return typeA;
    }

    protected void setTypeA(boolean typeA) {
        this.typeA = typeA;
    }

    public boolean isTypeB() {
        return typeB;
    }

    protected void setTypeB(boolean typeB) {
        this.typeB = typeB;
    }

    public int getPlayerId() {
        return playerId;
    }

    protected void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getSkinName() {
        return skinName;
    }

    protected void setSkinName(String skinName) {
        this.skinName = skinName;
    }

    public double getVersionNumber() {
        return versionNumber;
    }

    protected void setVersionNumber(double versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getUnknownValue1() {
        return unknownValue1;
    }

    protected void setUnknownValue1(String unknownValue1) {
        this.unknownValue1 = unknownValue1;
    }

    public boolean isUnknownValue2() {
        return unknownValue2;
    }

    protected void setUnknownValue2(boolean unknownValue2) {
        this.unknownValue2 = unknownValue2;
    }

    protected void setUnknownValue3(int unknownValue3) {
        this.unknownValue3 = unknownValue3;
    }

    public double getUnknownValue4() {
        return unknownValue4;
    }

    protected void setUnknownValue4(double unknownValue4) {
        this.unknownValue4 = unknownValue4;
    }

    public double getUnknownValue5() {
        return unknownValue5;
    }

    protected void setUnknownValue5(double unknownValue5) {
        this.unknownValue5 = unknownValue5;
    }

    public double getUnknownValue6() {
        return unknownValue6;
    }

    protected void setUnknownValue6(double unknownValue6) {
        this.unknownValue6 = unknownValue6;
    }
    
    public int getUnknownValue7() {
        return unknownValue7;
    }

    protected void setUnknownValue7(int unknownValue7) {
        this.unknownValue7 = unknownValue7;
    }

    public int getUnknownValue8() {
        return unknownValue8;
    }

    protected void setUnknownValue8(int unknownValue8) {
        this.unknownValue8 = unknownValue8;
    }

    public int getUnknownValue9() {
        return unknownValue9;
    }

    protected void setUnknownValue9(int unknownValue9) {
        this.unknownValue9 = unknownValue9;
    }

    public int getUnknownValue3() {
        return unknownValue3;
    }

    @Override
    public boolean parseLine(String line) {
        if (!super.parseLine(line)) {
            return false;
        }

        final String rawContent = getRawContent();
        final Matcher matcherA = Pattern.compile("^\\['(\\w+)',(true|false),'(\\d+)',(\\w+),(.*)\\]$").matcher(rawContent);
        final Matcher matcherB = Pattern.compile("^\\['(\\w+)',(true|false),'(\\d+)',\\[(\\d+),\\[(.+?),(.+?),(.+?)\\]\\],(.*?),\\[(\\d+),(\\d+),(\\d+)\\],\"(\\w+)\",(.*)\\]$").matcher(rawContent);
        
        if (matcherA.matches()) {
            setTypeA(true);
            
            final String unknownValue1String = matcherA.group(1);
            final String unknownValue2String = matcherA.group(2);
            final String playerIdString = matcherA.group(3);
            final String skinNameString = matcherA.group(4);
            final String versionNumberString = matcherA.group(5);
                    
            setUnknownValue1(unknownValue1String);
            setUnknownValue2(Boolean.parseBoolean(unknownValue2String));
            try {
                setPlayerId(Integer.parseInt(playerIdString));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing playerId. Set to -1. The source String was: " + playerIdString, exception);
                setPlayerId(-1);
            }
            setSkinName(skinNameString);
            try {
                setVersionNumber(Double.parseDouble(versionNumberString));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing versionNumber. Set to -1. The source String was: " + versionNumberString, exception);
                setVersionNumber(-1);
            }
            
            return true;
        } else if (matcherB.matches()) {
            setTypeB(true);
            
            final String unknownValue1String = matcherB.group(1);
            final String unknownValue2String = matcherB.group(2);
            final String playerIdString = matcherB.group(3);
            final String unknownValue3String = matcherB.group(4);
            final String unknownValue4String = matcherB.group(5);
            final String unknownValue5String = matcherB.group(6);
            final String unknownValue6String = matcherB.group(7);
            final String unknownValue7String = matcherB.group(9);
            final String unknownValue8String = matcherB.group(10);
            final String unknownValue9String = matcherB.group(11);
            final String skinNameString = matcherB.group(12);
            final String versionNumberString = matcherB.group(13);
            
            setUnknownValue1(unknownValue1String);
            setUnknownValue2(Boolean.parseBoolean(unknownValue2String));
            try {
                setPlayerId(Integer.parseInt(playerIdString));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing playerId. Set to -1. The source String was: " + playerIdString, exception);
                setPlayerId(-1);
            }
            try {
                setUnknownValue3(Integer.parseInt(unknownValue3String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue3. Set to -1. The source String was: " + unknownValue3String, exception);
                setUnknownValue3(-1);
            }
            try {
                setUnknownValue4(Double.parseDouble(unknownValue4String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue4. Set to -1. The source String was: " + unknownValue4String, exception);
                setUnknownValue4(-1);
            }
            try {
                setUnknownValue5(Double.parseDouble(unknownValue5String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue5. Set to -1. The source String was: " + unknownValue5String, exception);
                setUnknownValue5(-1);
            }
            try {
                setUnknownValue6(Double.parseDouble(unknownValue6String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue6. Set to -1. The source String was: " + unknownValue6String, exception);
                setUnknownValue6(-1);
            }
            parseGear(matcherB.group(8));            
            try {
                setUnknownValue7(Integer.parseInt(unknownValue7String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue7. Set to -1. The source String was: " + unknownValue7String, exception);
                setUnknownValue7(-1);
            }
            try {
                setUnknownValue8(Integer.parseInt(unknownValue8String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue8. Set to -1. The source String was: " + unknownValue8String, exception);
                setUnknownValue8(-1);
            }
            try {
                setUnknownValue9(Integer.parseInt(unknownValue9String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue9. Set to -1. The source String was: " + unknownValue9String, exception);
                setUnknownValue9(-1);
            }
            setSkinName(skinNameString);
            try {
                setVersionNumber(Double.parseDouble(versionNumberString));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing versionNumber. Set to -1. The source String was: " + versionNumberString, exception);
                setVersionNumber(-1);
            }
            
            return true;
        } else {
            return false;
        }
    }
    
    private void parseGear(final String string) {
        final Matcher matcherGear = Pattern.compile("^\\[\\[((\"\\w+\",?)*)\\],\\[((\\[\"\\w+\",\\d+\\],?|\"\\w+\",?)*)\\]\\],\\[\"(\\w+)\",\\[\\[((\"\\w+\",?)*)\\],\\[((\\d+,?)*)\\]\\],\\[\\[((\"\\w+\",?)*)\\],\\[((\\d+,?)*)\\]\\]\\]$").matcher(string);
        
        if (matcherGear.matches()) {
            final String toolbeltItemsString = matcherGear.group(1);
            final String chestItemsString = matcherGear.group(3);
            final String backpackTypeString = matcherGear.group(5);
            final String backpackWeaponsString = matcherGear.group(6);
            final String backpackWeaponNumbersString = matcherGear.group(8);
            final String backpackItemsString = matcherGear.group(10);
            final String backpackItemNumbersString = matcherGear.group(12);

            toolbelt.clear();
            chest.clear();
            backpack.clear();
            
            final String[] toolbeltItems = toolbeltItemsString.split(",");
            if (toolbeltItems != null) {
                for (final String item : toolbeltItems) {
                    toolbelt.add(new Item(item.replaceAll("^\"|\"$", "")));
                }
            }

            final Matcher inventoryMatcher = Pattern.compile("\\[(\"\\w+\",\\d+)\\]|(\"\\w+\")").matcher(chestItemsString);
            while(inventoryMatcher.find()) {                
                final String itemString = inventoryMatcher.group(0).replaceAll("^\\[|\\]$", "");
                
                final String[] splitItem = itemString.split(",");

                if (splitItem != null) {
                    final String itemType;
                    final int rounds;
                    if (splitItem.length > 1) {
                        itemType = splitItem[0].replaceAll("^\"|\"$", "");
                        rounds = Integer.parseInt(splitItem[1]);
                    } else {
                        itemType = itemString.replaceAll("^\"|\"$", "");
                        rounds = -1;
                    }
                    chest.add(new Magazin(itemType, rounds));
                }
            }        

            backpackType = backpackTypeString;

            if (backpackWeaponsString.length() > 0) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("backpackWeaponsString:" + backpackWeaponsString);
                    LOG.debug("backpackWeaponNumbersString: " + backpackWeaponNumbersString);
                }
                final String[] backpackWeapons = backpackWeaponsString.split(",");
                final String[] backpackWeaponNumbers = backpackWeaponNumbersString.split(",");      
                if (backpackWeapons != null) {
                    for (int index = 0; index < backpackWeapons.length; index++) {
                        int numOf;
                        try {
                            final String numberString = backpackWeaponNumbers[index];
                            if (LOG.isDebugEnabled()) {
                                LOG.debug("number[" + index + "]: " + numberString);
                            }
                            numOf = Integer.parseInt(numberString);
                        } catch (final Exception exception) {
                            LOG.warn("Error while holding numberString. Set to 1.", exception);
                            numOf = 1;
                        }
                        final String itemType = backpackWeapons[index].replaceAll("^\"|\"$", "");
                        if (LOG.isDebugEnabled()) {
                            LOG.debug("item[" + index + "]: " + itemType);
                        }
                        for (int num = 0; num < numOf; num++) {
                            backpack.add(new Item(itemType));
                        }
                    }
                }
            }

            if (backpackItemsString.length() > 0) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("backpackItemsString:" + backpackItemsString);
                    LOG.debug("backpackItemNumbersString: " + backpackItemNumbersString);
                }
                final String[] backpackItems = backpackItemsString.split(",");
                final String[] backpackItemsNumbers = backpackItemNumbersString.split(",");
                for (int index = 0; index < backpackItems.length; index++) {
                    int numOf;
                    try {
                        final String numberString = backpackItemsNumbers[index];
                        if (LOG.isDebugEnabled()) {
                            LOG.debug("number[" + index + "]: " + numberString);
                        }
                        numOf = Integer.parseInt(numberString);
                    } catch (final Exception exception) {
                        LOG.warn("Error while holding numberString. Set to 1.", exception);
                        numOf = 1;
                    }
                    final String itemType = backpackItems[index].replaceAll("^\"|\"$", "");
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("item[" + index + "]: " + itemType);
                    }
                    for (int num = 0; num < numOf; num++) {
                        backpack.add(new Item(itemType));
                    }
                }
            }
        }
    }
    
    class Item {
        
        private String type;
        
        public Item(String type) {
            this.type = type;
        }
        
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
        
    }
    
    class Magazin extends Item {
        private int rounds;

        public Magazin(String type, int rounds) {
            super(type);
            this.rounds = rounds;
        }

        public int getRounds() {
            return rounds;
        }

        public void setRounds(int rounds) {
            this.rounds = rounds;
        }

        @Override
        public String toString() {
            return (rounds < 0)? getType() + " (" + Integer.toString(rounds) + ")" : getType();
        }
                
    }
}
