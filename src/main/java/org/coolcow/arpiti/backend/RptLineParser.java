/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.coolcow.arpiti.backend.entities.Coordinate;
import org.coolcow.arpiti.backend.entities.Item;
import org.coolcow.arpiti.backend.entities.Player;
import org.coolcow.arpiti.backend.rptline.AbstractRptLine;
import org.coolcow.arpiti.backend.rptline.CleanupRptLine;
import org.coolcow.arpiti.backend.rptline.DefaultRptLine;
import org.coolcow.arpiti.backend.rptline.DisconnectStartIRptLine;
import org.coolcow.arpiti.backend.rptline.DwDebugFpsRptLine;
import org.coolcow.arpiti.backend.rptline.ErrorRptLine;
import org.coolcow.arpiti.backend.rptline.HiveRptLine;
import org.coolcow.arpiti.backend.rptline.LocalityEventRptLine;
import org.coolcow.arpiti.backend.rptline.LoginAttemptRptLine;
import org.coolcow.arpiti.backend.rptline.LoginLoadedRptLine;
import org.coolcow.arpiti.backend.rptline.LoginPublishingRptLine;
import org.coolcow.arpiti.backend.rptline.ObjRptLine;
import org.coolcow.arpiti.backend.rptline.PDeathRptLine;
import org.coolcow.arpiti.backend.rptline.ReadWriteRptLine;
import org.coolcow.arpiti.backend.rptline.RptLine;
import org.coolcow.arpiti.backend.rptline.SecondHandZombieInitializedRptLine;
import org.coolcow.arpiti.backend.rptline.StartingLoginRptLine;
import org.coolcow.arpiti.backend.rptline.WriteRptLine;

/**
 *
 * @author jruiz
 */
public class RptLineParser {

    private static final Logger LOG = Logger.getLogger(RptLineParser.class);

    public static AbstractRptLine parseLine(final int number, final String rawLine) {
        if (rawLine.trim().length() == 0) {
            return null;
        }
        AbstractRptLine.Type type = null;
        Date date;
        String rawContent;
        AbstractRptLine rptLine = new DefaultRptLine();

        final Matcher timeMatcher = Pattern.compile("^(\\d{1,2}:\\d{2}:\\d{2}) (.*)$").matcher(rawLine.trim());

        if (timeMatcher.matches()) {
            final String dateString = timeMatcher.group(1);
            final String contentString = timeMatcher.group(2);

            try {
                date = new SimpleDateFormat(Backend.INPUT_DATE_FORMAT).parse(dateString);
            } catch (final ParseException exception) {
                LOG.warn("Error while parsing date. Date is set to null. The source String was: " + dateString, exception);
                date = null;
            }
            rawContent = contentString;
        } else {
            date = null;
            rawContent = rawLine;
        }

        final String content;

        final Matcher localityEventMatcher = Pattern.compile("^\"(" + RptLine.Type.LOCALITY_EVENT + ")\"$").matcher(rawContent.trim());
        final Matcher defaultTypeMatcher = Pattern.compile("^\"(.*?): (.*)\"$").matcher(rawContent.trim());
        if (localityEventMatcher.matches()) {
            rptLine = new LocalityEventRptLine();
            type = RptLine.Type.LOCALITY_EVENT;
            content = "";
        } else if (defaultTypeMatcher.matches()) {
            final String typeString = defaultTypeMatcher.group(1).trim();
            final String contentString = defaultTypeMatcher.group(2);

            content = contentString;
            switch (typeString) {
                case "LOGIN ATTEMPT": {
                    type = RptLine.Type.LOGIN_ATTEMPT;
                    rptLine = new LoginAttemptRptLine();
                }
                break;
                case "LOGIN LOADED": {
                    type = RptLine.Type.LOGIN_LOADED;
                    rptLine = new LoginLoadedRptLine();
                }
                break;
                case "LOGIN PUBLISHING": {
                    type = RptLine.Type.LOGIN_PUBLISHING;
                    rptLine = new LoginPublishingRptLine();
                }
                break;
                case "STARTING LOGIN": {
                    type = RptLine.Type.STARTING_LOGIN;
                    rptLine = new StartingLoginRptLine();
                }
                break;
                case "DISCONNECT START (i)": {
                    type = RptLine.Type.DISCONNECT_START_I;
                    rptLine = new DisconnectStartIRptLine();
                }
                break;
                case "DW_DEBUG FPS": {
                    type = RptLine.Type.DW_DEBUG_FPS;
                    rptLine = new DwDebugFpsRptLine();
                }
                break;
                case "OBJ": {
                    type = RptLine.Type.OBJ;
                    rptLine = new ObjRptLine();
                }
                break;
                case "ERROR": {
                    type = RptLine.Type.ERROR;
                    rptLine = new ErrorRptLine();
                }
                break;
                case "Second Hand Zombie Initialized": {
                    type = RptLine.Type.SECOND_HAND_ZOMBIE_INITIALIZED;
                    rptLine = new SecondHandZombieInitializedRptLine();
                }
                break;
                case "PDEATH": {
                    type = RptLine.Type.PDEATH;
                    rptLine = new PDeathRptLine();
                }
                break;
                case "WRITE": {
                    type = RptLine.Type.WRITE;
                    rptLine = new WriteRptLine();
                }
                break;
                case "CLEANUP": {
                    type = RptLine.Type.CLEANUP;
                    rptLine = new CleanupRptLine();
                }
                break;
                case "DELETE": {
                    type = RptLine.Type.DELETE;
                }
                break;
                case "HIVE": {
                    type = RptLine.Type.HIVE;
                    rptLine = new HiveRptLine();
                }
                break;
                case "READ/WRITE": {
                    type = RptLine.Type.READ_WRITE;
                    rptLine = new ReadWriteRptLine();
                }
                break;
                default: {
                }
            }
        } else {
            type = null;
            content = rawContent;
        }
        rptLine.setRawLine(rawLine);
        rptLine.setNumber(number);
        rptLine.setTime(date);
        rptLine.setType(type);
        rptLine.setRawContent(content);
        parseContent(rptLine);
        return rptLine;
    }

    private static void parseContent(final AbstractRptLine rptLine) {
        LOG.fatal("fuck!");
    }

    private static void parseContent(final DefaultRptLine rptLine) {
    }

    private static void parseContent(final CleanupRptLine rptLine) {
        final String rawContent = rptLine.getRawContent();

        final Matcher zombieMatcher = Pattern.compile("^DELETE UNCONTROLLED ZOMBIE: (\\w+) OF: (.+)$").matcher(rawContent);
        final Matcher objectMatcher = Pattern.compile("^DELETED A \"(\\w+)\"$").matcher(rawContent);
        final Matcher initializeMatcher = Pattern.compile("^INITIALIZING CLEANUP SCRIPT$").matcher(rawContent);

        if (zombieMatcher.matches()) {
            rptLine.setIsZombie(true);

            final String zombieTypeString = zombieMatcher.group(1);
            final String zombieIdentifierString = zombieMatcher.group(2);

            rptLine.setZombieType(zombieTypeString);
            rptLine.setZombieIdentifier(zombieIdentifierString);
        } else if (objectMatcher.matches()) {
            rptLine.setIsObject(true);

            final String zombieTypeString = objectMatcher.group(1);

            rptLine.setObjectType(zombieTypeString);
        } else if (initializeMatcher.matches()) {
            rptLine.setIsInitialize(true);
        }
    }

    private static void parseContent(final DisconnectStartIRptLine rptLine) {
        final String rawContent = rptLine.getRawContent();
        final Matcher matcherA = Pattern.compile("^(.+) \\(\"(\\d+)\"\\) Object: (. .-.-.):(\\d+) \\((.+)\\)( REMOTE)?$").matcher(rawContent);
        final Matcher matcherB = Pattern.compile("^(.+) \\(\"(\\d+)\"\\) Object: (. .-.-.):(\\d+)( REMOTE)?$").matcher(rawContent);
        final Matcher matcherC = Pattern.compile("^(.+) \\(\"(\\d+)\"\\) Object: <NULL-object>$").matcher(rawContent);
        final Matcher matcherD = Pattern.compile("^(.+) \\(\"(\\d+)\"\\) Object: (\\w+)# (\\d+): (.+) REMOTE?$").matcher(rawContent);

        if (matcherA.matches()) {
            rptLine.setTypeA(true);

            final String playerNameString = matcherA.group(1);
            final String playerIdentifierString = matcherA.group(2);
            final String unknownValue1String = matcherA.group(3);
            final String unknownValue2String = matcherA.group(4);
            final String playerName2String = matcherA.group(5);

            rptLine.setPlayerIdentifier(playerIdentifierString);
            rptLine.setPlayerName(playerNameString);

            rptLine.setUnknownValue1(unknownValue1String);
            try {
                rptLine.setUnknownValue2(Integer.parseInt(unknownValue2String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue2. Set to -1. The source String was: " + unknownValue2String, exception);
                rptLine.setUnknownValue2(-1);
            }
            rptLine.setPlayerName2(playerName2String);

        } else if (matcherB.matches()) {
            rptLine.setTypeB(true);

            final String playerNameString = matcherB.group(1);
            final String playerIdentifierString = matcherB.group(2);
            final String unknownValue1String = matcherB.group(3);
            final String unknownValue2String = matcherB.group(4);

            final Player player = new Player();
            player.setIdentifier(playerIdentifierString);
            player.setName(playerNameString);

            rptLine.setPlayerIdentifier(playerIdentifierString);
            rptLine.setPlayerName(playerNameString);

            rptLine.setUnknownValue1(unknownValue1String);
            try {
                rptLine.setUnknownValue2(Integer.parseInt(unknownValue2String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue2. Set to -1. The source String was: " + unknownValue2String, exception);
                rptLine.setUnknownValue2(-1);
            }

        } else if (matcherC.matches()) {
            rptLine.setTypeC(true);

            final String playerNameString = matcherC.group(1);
            final String playerIdentifierString = matcherC.group(2);

            final Player player = new Player();
            player.setIdentifier(playerIdentifierString);
            player.setName(playerNameString);

            rptLine.setPlayerIdentifier(playerIdentifierString);
            rptLine.setPlayerName(playerNameString);

            rptLine.setUnknownValue1(null);
            rptLine.setUnknownValue2(-1);
        } else if (matcherD.matches()) {
            rptLine.setTypeD(true);

            final String playerNameString = matcherD.group(1);
            final String playerIdentifierString = matcherD.group(2);
            final String unknownValue3String = matcherD.group(3);
            final String unknownValue4String = matcherD.group(4);
            final String playerSkinString = matcherD.group(5);

            final Player player = new Player();
            player.setIdentifier(playerIdentifierString);
            player.setName(playerNameString);
            player.setSkinName(playerSkinString);

            rptLine.setPlayerIdentifier(playerIdentifierString);
            rptLine.setPlayerName(playerNameString);

            rptLine.setUnknownValue3(unknownValue3String);
            try {
                rptLine.setUnknownValue4(Integer.parseInt(unknownValue4String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue4. Set to -1. The source String was: " + unknownValue4String, exception);
                rptLine.setUnknownValue4(-1);
            }
        }
    }

    private static void parseContent(final DwDebugFpsRptLine rptLine) {
        final String rawContent = rptLine.getRawContent();
        final String fpsString = rawContent;

        try {
            rptLine.setFps(Double.parseDouble(fpsString));
        } catch (final NumberFormatException exception) {
            LOG.warn("Error while parsing fps. Set to -1. The source String was: " + fpsString, exception);
            rptLine.setFps(-1);
        }
    }

    private static void parseContent(final ErrorRptLine rptLine) {
        final String rawContent = rptLine.getRawContent();
        rptLine.setMessage(rawContent);
    }

    private static void parseContent(final HiveRptLine rptLine) {
        final String rawContent = rptLine.getRawContent();
        final Matcher writeMatcher = Pattern.compile("^WRITE: \"CHILD:(\\d+):(\\w+):(\\d+):(\\d+):\"$").matcher(rawContent);

        if (writeMatcher.matches()) {
            rptLine.setTypeWrite(true);

            final String unknownValue1String = writeMatcher.group(1);
            final String playerIdentifierString = writeMatcher.group(2);
            final String hiveIdString = writeMatcher.group(3);
            final String unknownValue2String = writeMatcher.group(4);

            rptLine.setPlayerIdentifier(playerIdentifierString);
            if (hiveIdString != null) {
                try {
                    rptLine.setHiveId(Integer.parseInt(hiveIdString));
                } catch (final NumberFormatException exception) {
                    LOG.warn("Error while parsing hiveIdentifier." + hiveIdString, exception);
                    rptLine.setHiveId(-1);
                }
            }

            try {
                rptLine.setUnknownValue1(Integer.parseInt(unknownValue1String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue1. Set to -1. The source String was: " + unknownValue1String, exception);
                rptLine.setUnknownValue1(-1);
            }
            try {
                rptLine.setUnknownValue2(Integer.parseInt(unknownValue2String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue2. Set to -1. The source String was: " + unknownValue2String, exception);
                rptLine.setUnknownValue2(-1);
            }
        }
    }

    private static void parseContent(final LocalityEventRptLine rptLine) {
    }

    private static void parseContent(final LoginAttemptRptLine rptLine) {
        final String rawContent = rptLine.getRawContent();
        final Matcher matcher = Pattern.compile("\"(.*)\" (.*)").matcher(rawContent);

        if (matcher.matches()) {
            final String playerIdentifierString = matcher.group(1);
            final String playerNameString = matcher.group(2);

            rptLine.setPlayerIdentifier(playerIdentifierString);
            rptLine.setPlayerName(playerNameString);
        }
    }

    private static void parseContent(final LoginLoadedRptLine rptLine) {
        final String rawContent = rptLine.getRawContent();
        final Matcher matcher = Pattern.compile("^(. .-.-.):(\\d+) \\((.*)\\) REMOTE Type: (.*)$").matcher(rawContent);

        if (matcher.matches()) {
            final String unknownValue1String = matcher.group(1);
            final String unknownValue2String = matcher.group(2);
            final String playerNameString = matcher.group(3);
            final String skinNameString = matcher.group(4);

            rptLine.setPlayerName(playerNameString);
            rptLine.setSkinName(skinNameString);

            rptLine.setUnknownValue1(unknownValue1String);
            try {
                rptLine.setUnknownValue2(Integer.parseInt(unknownValue2String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue2. Set to -1. The source String was: " + unknownValue2String, exception);
                rptLine.setUnknownValue2(-1);
            }
        }
    }

    private static void parseContent(final LoginPublishingRptLine rptLine) {
        final String rawContent = rptLine.getRawContent();
        final Matcher matcher = Pattern.compile("^(.) (.-.-.):(\\d+) \\((.*)\\) REMOTE Type: (.*)$").matcher(rawContent);

        if (matcher.matches()) {
            final String unknownValue1String = matcher.group(1);
            final String unknownValue2String = matcher.group(3);
            final String playerNameString = matcher.group(4);
            final String skinNameString = matcher.group(5);

            rptLine.setSkinName(skinNameString);
            rptLine.setPlayerName(playerNameString);

            rptLine.setUnknownValue1(unknownValue1String);
            try {
                rptLine.setUnknownValue2(Integer.parseInt(unknownValue2String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue2. Set to -1. The source String was: " + unknownValue2String, exception);
                rptLine.setUnknownValue2(-1);
            }
        }
    }

    private static void parseContent(final ObjRptLine rptLine) {
        final String rawContent = rptLine.getRawContent();
        final Matcher matcher = Pattern.compile("^\"(\\d+)\"(.*)$").matcher(rawContent);

        if (matcher.matches()) {
            final String objecIdString = matcher.group(1);
            final String objecNameString = matcher.group(2);

            try {
                rptLine.setObjectId(Integer.parseInt(objecIdString));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing objecId. Set to -1. The source String was: " + objecIdString, exception);
                rptLine.setObjectId(-1);
            }
            rptLine.setObjectName(objecNameString);
        }
    }

    private static void parseContent(final PDeathRptLine rptLine) {
        final String rawContent = rptLine.getRawContent();
        final Matcher matcher = Pattern.compile("^Player Died (\\d+)$").matcher(rawContent);

        if (matcher.matches()) {
            final String playerIdentifierString = matcher.group(1);

            rptLine.setPlayerIdentifier(playerIdentifierString);
        }
    }

    private static void parseContent(final ReadWriteRptLine rptLine) {
        final String floatingPointRegex = "[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?";
        final String coordinateRegex = "\\[(" + floatingPointRegex + "),(" + floatingPointRegex + "),(" + floatingPointRegex + ")\\]";

        final String rawContent = rptLine.getRawContent();
        final Matcher matcherBackpack = Pattern.compile("^\\['(\\w+)',(true|false),'(\\d+)',(\\[((\\d+),(" + coordinateRegex + "))?\\],(.*?),\\[(\\d+),(\\d+),(\\d+)\\],)?\"?(\\w+)\"?,(.*)\\]$").matcher(rawContent);
        final Matcher matcherC = Pattern.compile("^\\['(\\w+)',\\[(\\d+),(\\d+),(\\d+),(\\d+),(\\d+)\\]]$").matcher(rawContent);

        if (matcherBackpack.matches()) {
            rptLine.setTypeGear(true);

            final String unknownValue1String = matcherBackpack.group(1);
            final String unknownValue2String = matcherBackpack.group(2);
            final String hiveIdString = matcherBackpack.group(3);
            final String unknownValue3String = matcherBackpack.group(6);
            final String coordinateString = matcherBackpack.group(7);
            final String gearString = matcherBackpack.group(14);
            final String unknownValue4String = matcherBackpack.group(15);
            final String unknownValue5String = matcherBackpack.group(16);
            final String unknownValue6String = matcherBackpack.group(17);
            final String skinNameString = matcherBackpack.group(18);
            final String versionNumberString = matcherBackpack.group(19);

            try {
                rptLine.setHiveId(Integer.parseInt(hiveIdString));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing hiveIdentifier. The source String was: " + hiveIdString, exception);
                rptLine.setHiveId(-1);
            }
            rptLine.setSkinName(skinNameString);

            rptLine.setUnknownValue1(unknownValue1String);
            rptLine.setUnknownValue2(Boolean.parseBoolean(unknownValue2String));
            try {
                rptLine.setUnknownValue3(Integer.parseInt(unknownValue3String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue3. Set to -1. The source String was: " + unknownValue3String, exception);
                rptLine.setUnknownValue3(-1);
            }
            if (coordinateString != null) {
                rptLine.setCoordinate(Coordinate.parseCoordinate(coordinateString));
            } else {
                rptLine.setCoordinate(null);
            }
            if (gearString != null) {
                final Matcher matcherGear = Pattern.compile("^\\[\\[((\"\\w+\",?)*)\\],\\[((\\[\"\\w+\",\\d+\\],?|\"\\w+\",?)*)\\]\\],\\[\"(\\w+)\",\\[\\[((\"\\w+\",?)*)\\],\\[((\\d+,?)*)\\]\\],\\[\\[((\"\\w+\",?)*)\\],\\[((\\d+,?)*)\\]\\]\\]$").matcher(gearString);

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
                    rptLine.setToolbelt(toolbelt);

                    final List<Item> inventory = new ArrayList<>();
                    final Matcher inventoryMatcher = Pattern.compile("\\[(\"\\w+\",\\d+)\\]|(\"\\w+\")").matcher(inventoryItemsString);
                    while (inventoryMatcher.find()) {
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
                    rptLine.setInventory(inventory);

                    rptLine.setBackpackType(backpackTypeString);

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
                    rptLine.setBackpack(backpack);
                }
            }
            try {
                rptLine.setUnknownValue4(Integer.parseInt(unknownValue4String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue4. Set to -1. The source String was: " + unknownValue4String, exception);
                rptLine.setUnknownValue4(-1);
            }
            try {
                rptLine.setUnknownValue5(Integer.parseInt(unknownValue5String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue5. Set to -1. The source String was: " + unknownValue5String, exception);
                rptLine.setUnknownValue5(-1);
            }
            try {
                rptLine.setUnknownValue6(Integer.parseInt(unknownValue6String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue6. Set to -1. The source String was: " + unknownValue6String, exception);
                rptLine.setUnknownValue6(-1);
            }
            try {
                rptLine.setVersionNumber(Double.parseDouble(versionNumberString));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing versionNumber. Set to -1. The source String was: " + versionNumberString, exception);
                rptLine.setVersionNumber(-1);
            }
        } else if (matcherC.matches()) {
            rptLine.setTypeDate(true);

            final String unknownValue1String = matcherC.group(1);
            final String yearString = matcherC.group(2);
            final String monthString = matcherC.group(3);
            final String dayString = matcherC.group(4);
            final String hourString = matcherC.group(5);
            final String minuteString = matcherC.group(6);

            rptLine.setUnknownValue1(unknownValue1String);
            try {
                final Date date = new GregorianCalendar(
                        Integer.parseInt(yearString),
                        Integer.parseInt(monthString),
                        Integer.parseInt(dayString),
                        Integer.parseInt(hourString),
                        Integer.parseInt(minuteString)).getTime();
                rptLine.setDate(date);
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing Date. Set to null.", exception);
                rptLine.setDate(null);
            }
        }
    }

    private static void parseContent(final SecondHandZombieInitializedRptLine rptLine) {
        final String rawContent = rptLine.getRawContent();
        final Matcher matcher = Pattern.compile("^\\[\\[(.*?),(.*?),(.*?)\\],(.*?),(.*?)\\]$").matcher(rawContent);

        if (matcher.matches()) {
            final String xString = matcher.group(1);
            final String yString = matcher.group(2);
            final String zString = matcher.group(3);
            final String identifierString = matcher.group(4);
            final String unknownValueString = matcher.group(5);

            try {
                final double x = Double.parseDouble(xString);
                final double y = Double.parseDouble(yString);
                final double z = Double.parseDouble(zString);
                rptLine.setCoordinate(new Coordinate(x, y, z));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing coordinate. Set to null.", exception);
                rptLine.setCoordinate(null);
            }
            rptLine.setIdentifier(identifierString);
            try {
                rptLine.setUnknownValue(Boolean.parseBoolean(unknownValueString));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue. Set to false. The source String was: " + unknownValueString, exception);
                rptLine.setUnknownValue(false);
            }
        }
    }

    private static void parseContent(final StartingLoginRptLine rptLine) {
        final String rawContent = rptLine.getRawContent();
        final Matcher matcher = Pattern.compile("^\\[\"(\\d+)\",(. .-.-.):(\\d+) \\((.+)\\) REMOTE\\]$").matcher(rawContent);

        if (matcher.matches()) {
            final String playerIdentifierString = matcher.group(1);
            final String unknownValue1String = matcher.group(2);
            final String unknownValue2String = matcher.group(3);
            final String playerNameString = matcher.group(4);

            rptLine.setPlayerIdentifier(playerIdentifierString);
            rptLine.setPlayerName(playerNameString);
            rptLine.setUnknownValue1(unknownValue1String);
            try {
                rptLine.setUnknownValue2(Integer.parseInt(unknownValue2String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue2. Set to -1. The source String was: " + unknownValue2String, exception);
                rptLine.setUnknownValue2(-1);
            }
        }
    }

    private static void parseContent(final WriteRptLine rptLine) {
        final String rawContent = rptLine.getRawContent();
        final Matcher matcher = Pattern.compile("^\\[\\'(\\w+)\\'\\]$").matcher(rawContent);

        if (matcher.matches()) {
            final String unknownValueString = matcher.group(1);

            rptLine.setUnknownValue(unknownValueString);
        }
    }
}
