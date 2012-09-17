/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.coolcow.arpiti.backend.Backend;
import org.coolcow.arpiti.backend.Coordinate;
import org.coolcow.arpiti.backend.Item;
import org.coolcow.arpiti.backend.Player;
import org.coolcow.arpiti.backend.rptline.interfaces.CoordinateProvider;
import org.coolcow.arpiti.backend.rptline.interfaces.ItemProvider;
import org.coolcow.arpiti.backend.rptline.interfaces.PlayerProvider;

/**
 *
 * @author jruiz
 */
public class ReadWriteRptLine extends AbstractRptLine implements CoordinateProvider, ItemProvider, PlayerProvider {

    private static final Logger LOG = Logger.getLogger(ReadWriteRptLine.class);
    
    private boolean typeGear = false;
    private boolean typeDate = false;
    private Player player;
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

    protected void setTypeGear(final boolean typeGear) {
        this.typeGear = typeGear;
    }

    public boolean isTypeDate() {
        return typeDate;
    }

    protected void setTypeDate(final boolean typeDate) {
        this.typeDate = typeDate;
    }

    public Player getPlayer() {
        return player;
    }

    protected void setPlayer(final Player player) {
        this.player = player;
    }

    public double getVersionNumber() {
        return versionNumber;
    }

    protected void setVersionNumber(final double versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getUnknownValue1() {
        return unknownValue1;
    }

    protected void setUnknownValue1(final String unknownValue1) {
        this.unknownValue1 = unknownValue1;
    }

    public boolean isUnknownValue2() {
        return unknownValue2;
    }

    protected void setUnknownValue2(final boolean unknownValue2) {
        this.unknownValue2 = unknownValue2;
    }

    public int getUnknownValue3() {
        return unknownValue3;
    }

    protected void setUnknownValue3(final int unknownValue3) {
        this.unknownValue3 = unknownValue3;
    }

    public int getUnknownValue4() {
        return unknownValue4;
    }

    protected void setUnknownValue4(final int unknownValue4) {
        this.unknownValue4 = unknownValue4;
    }

    public int getUnknownValue5() {
        return unknownValue5;
    }

    protected void setUnknownValue5(final int unknownValue5) {
        this.unknownValue5 = unknownValue5;
    }

    public int getUnknownValue6() {
        return unknownValue6;
    }

    protected void setUnknownValue6(final int unknownValue6) {
        this.unknownValue6 = unknownValue6;
    }

    public String getBackpackType() {
        return backpackType;
    }

    protected void setBackpackType(final String backpackType) {
        this.backpackType = backpackType;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    protected void setCoordinate(final Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Date getDate() {
        return date;
    }
    
    protected void setDate(final Date date) {
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
    
    @Override
    public boolean parseLine(final String line) {
        if (!super.parseLine(line)) {
            return false;
        }

        final String floatingPointRegex = "[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?";        
        final String coordinateRegex = "\\[(" + floatingPointRegex + "),(" + floatingPointRegex + "),(" + floatingPointRegex + ")\\]";
        
        final String rawContent = getRawContent();
        final Matcher matcherBackpack = Pattern.compile("^\\['(\\w+)',(true|false),'(\\d+)',(\\[((\\d+),(" + coordinateRegex + "))?\\],(.*?),\\[(\\d+),(\\d+),(\\d+)\\],)?\"?(\\w+)\"?,(.*)\\]$").matcher(rawContent);
        final Matcher matcherC = Pattern.compile("^\\['(\\w+)',\\[(\\d+),(\\d+),(\\d+),(\\d+),(\\d+)\\]]$").matcher(rawContent);
        
        if (matcherBackpack.matches()) {
            setTypeGear(true);        
            
            final String unknownValue1String = matcherBackpack.group(1);
            final String unknownValue2String = matcherBackpack.group(2);
            final String hiveIdentifierString = matcherBackpack.group(3);
            final String unknownValue3String = matcherBackpack.group(6);
            final String coordinateString = matcherBackpack.group(7);
            final String gearString = matcherBackpack.group(14);
            final String unknownValue4String = matcherBackpack.group(15);
            final String unknownValue5String = matcherBackpack.group(16);
            final String unknownValue6String = matcherBackpack.group(17);            
            final String skinNameString = matcherBackpack.group(18);
            final String versionNumberString = matcherBackpack.group(19);
            
            final Player player = new Player();
            try {
                player.setHiveId(Integer.parseInt(hiveIdentifierString));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing hiveIdentifier. The source String was: " + hiveIdentifierString, exception);
                setUnknownValue3(-1);
            }
            player.setSkinName(skinNameString);            
            setPlayer(player);
            
            setUnknownValue1(unknownValue1String);
            setUnknownValue2(Boolean.parseBoolean(unknownValue2String));
            try {
                setUnknownValue3(Integer.parseInt(unknownValue3String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue3. Set to -1. The source String was: " + unknownValue3String, exception);
                setUnknownValue3(-1);
            }
            if (coordinateString != null) {
                setCoordinate(Coordinate.parseCoordinate(coordinateString));
            } else {
                setCoordinate(null);
            }
            if (gearString != null) {
                parseGear(gearString);            
            }
            try {
                setUnknownValue4(Integer.parseInt(unknownValue4String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue4. Set to -1. The source String was: " + unknownValue4String, exception);
                setUnknownValue4(-1);
            }
            try {
                setUnknownValue5(Integer.parseInt(unknownValue5String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue5. Set to -1. The source String was: " + unknownValue5String, exception);
                setUnknownValue5(-1);
            }
            try {
                setUnknownValue6(Integer.parseInt(unknownValue6String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue6. Set to -1. The source String was: " + unknownValue6String, exception);
                setUnknownValue6(-1);
            }
            try {
                setVersionNumber(Double.parseDouble(versionNumberString));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing versionNumber. Set to -1. The source String was: " + versionNumberString, exception);
                setVersionNumber(-1);
            }
            
            return true;
        } else if (matcherC.matches()) {
            setTypeDate(true);
            
            final String unknownValue1String = matcherC.group(1);
            final String yearString = matcherC.group(2);
            final String monthString = matcherC.group(3);
            final String dayString = matcherC.group(4);
            final String hourString = matcherC.group(5);
            final String minuteString = matcherC.group(6);
            
            setUnknownValue1(unknownValue1String);
            try {
                final Date date =  new GregorianCalendar(
                    Integer.parseInt(yearString), 
                    Integer.parseInt(monthString), 
                    Integer.parseInt(dayString), 
                    Integer.parseInt(hourString), 
                    Integer.parseInt(minuteString)
                    ).getTime();
                setDate(date);
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing Date. Set to null.", exception);
                setDate(null);
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
            final String inventoryItemsString = matcherGear.group(3);
            final String backpackTypeString = matcherGear.group(5);
            final String backpackWeaponsString = matcherGear.group(6);
            final String backpackWeaponNumbersString = matcherGear.group(8);
            final String backpackItemsString = matcherGear.group(10);
            final String backpackItemNumbersString = matcherGear.group(12);

            final List<Item> toolbelt = new ArrayList<>();
            
            final String[] toolbeltItems = toolbeltItemsString.split(",");
            if (toolbeltItems != null) {
                for (final String item : toolbeltItems) {
                    toolbelt.add(new Item(item.replaceAll("^\"|\"$", "")));
                }
            }            
            setToolbelt(toolbelt);

            final List<Item> inventory = new ArrayList<>();
            final Matcher inventoryMatcher = Pattern.compile("\\[(\"\\w+\",\\d+)\\]|(\"\\w+\")").matcher(inventoryItemsString);
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
                    inventory.add(new Item(itemType, rounds));
                }
            }            
            setInventory(inventory);

            setBackpackType(backpackTypeString);

            final List<Item> backpack = new ArrayList<>();
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
            setBackpack(backpack);            
        }        
    }        

    @Override
    public Collection<Coordinate> getCoordinates() {
        final Collection<Coordinate> coll = new ArrayList<>();
        if (coordinate != null) {
            coll.add(coordinate);
        }
        return coll;
    }

    @Override
    public Collection<Item> getItems() {
        final Collection<Item> coll = new ArrayList<>();
        coll.addAll(toolbelt);
        coll.addAll(inventory);
        coll.addAll(backpack);
        return coll;
    }

    @Override
    public Collection<Player> getPlayers() {
        final Collection<Player> coll = new ArrayList<>();
        if (player != null) {
            coll.add(player);
        }
        return coll;
    }
}
