/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jruiz
 */
public class ReadWriteRptLine extends AbstractRptLine {

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
            setUnknownValue1(matcherA.group(1));
            setUnknownValue2(Boolean.parseBoolean(matcherA.group(2)));
            try {
                setPlayerId(Integer.parseInt(matcherA.group(3)));
            } catch (final NumberFormatException ex) {
                setPlayerId(-1);
            }
            setSkinName(matcherA.group(4));
            try {
                setVersionNumber(Double.parseDouble(matcherA.group(5)));
            } catch (final NumberFormatException ex) {
                setVersionNumber(-1);
            }
            return true;
        } else if (matcherB.matches()) {
            setTypeB(true);
            setUnknownValue1(matcherB.group(1));
            setUnknownValue2(Boolean.parseBoolean(matcherB.group(2)));
            try {
                setPlayerId(Integer.parseInt(matcherB.group(3)));
            } catch (final NumberFormatException ex) {
                setPlayerId(-1);
            }
            try {
                setUnknownValue3(Integer.parseInt(matcherB.group(4)));
            } catch (final NumberFormatException ex) {
                setUnknownValue3(-1);
            }
            try {
                setUnknownValue4(Double.parseDouble(matcherB.group(5)));
            } catch (final NumberFormatException ex) {
                setUnknownValue4(-1);
            }
            try {
                setUnknownValue5(Double.parseDouble(matcherB.group(6)));
            } catch (final NumberFormatException ex) {
                setUnknownValue5(-1);
            }
            try {
                setUnknownValue6(Double.parseDouble(matcherB.group(7)));
            } catch (final NumberFormatException ex) {
                setUnknownValue6(-1);
            }
            parseGear(matcherB.group(8));            
            try {
                setUnknownValue7(Integer.parseInt(matcherB.group(9)));
            } catch (final NumberFormatException ex) {
                setUnknownValue7(-1);
            }
            try {
                setUnknownValue8(Integer.parseInt(matcherB.group(10)));
            } catch (final NumberFormatException ex) {
                setUnknownValue8(-1);
            }
            try {
                setUnknownValue9(Integer.parseInt(matcherB.group(11)));
            } catch (final NumberFormatException ex) {
                setUnknownValue9(-1);
            }
            setSkinName(matcherB.group(12));
            try {
                setVersionNumber(Double.parseDouble(matcherB.group(13)));
            } catch (final NumberFormatException ex) {
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
            toolbelt.clear();
            try {
                final String[] toolbeltItems = matcherGear.group(1).split(",");
                for (final String item : toolbeltItems) {
                    toolbelt.add(new Item(item.replaceAll("^\"|\"$", "")));
                }
            } catch (final Exception ex) {
                ex.printStackTrace();
            }

            chest.clear();
            try {
                final Matcher inventoryMatcher = Pattern.compile("\\[(\"\\w+\",\\d+)\\]|(\"\\w+\")").matcher(matcherGear.group(3));
                System.out.println(matcherGear.group(3));
                while(inventoryMatcher.find()) {
                     final String itemRaw = inventoryMatcher.group(0).replaceAll("^\\[|\\]$", "");
                     System.out.println(itemRaw);
                     final String[] splitItem = itemRaw.split(",");

                     final String itemType;
                     final int rounds;
                     if (splitItem.length > 1) {
                         itemType = splitItem[0].replaceAll("^\"|\"$", "");
                         rounds = Integer.parseInt(splitItem[1]);
                     } else {
                         itemType = itemRaw.replaceAll("^\"|\"$", "");
                         rounds = -1;
                     }
                     chest.add(new Magazin(itemType, rounds));
                }        
            } catch (final Exception ex) {
                ex.printStackTrace();
            }

            backpackType = matcherGear.group(5);

            backpack.clear();
            try {
                final String[] backpackWeapons = matcherGear.group(6).split(",");
                final String[] backpackWeaponNumbers = matcherGear.group(8).split(",");        
                for (int index = 0; index < backpackWeapons.length; index++) {
                    final int numOf = Integer.parseInt(backpackWeaponNumbers[index]);
                    final String itemType = backpackWeapons[index].replaceAll("^\"|\"$", "");
                    for (int num = 0; num < numOf; num++) {
                        backpack.add(new Item(itemType));
                    }
                }

                final String[] backpackItems = matcherGear.group(10).split(",");
                final String[] backpackItemsNumbers = matcherGear.group(12).split(",");
                for (int index = 0; index < backpackItems.length; index++) {
                    final int numOf = Integer.parseInt(backpackItemsNumbers[index]);
                    final String itemType = backpackItems[index].replaceAll("^\"|\"$", "");
                    for (int num = 0; num < numOf; num++) {
                        backpack.add(new Item(itemType));
                    }
                }
            } catch (final Exception ex) {
                ex.printStackTrace();
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
