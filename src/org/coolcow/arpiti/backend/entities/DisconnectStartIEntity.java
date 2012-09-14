/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.entities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jruiz
 */
public class DisconnectStartIEntity extends AbstractEntity {
    
    private String playerName;
    private int playerId;
    private String unknownValue1;
    private int unknownValue2;
    private String playerName2;
    private int unknownValue3;
    private int unknownValue4;
    private String playerSkin;
    
    public DisconnectStartIEntity(final String content) {
        super(content);
        
        final Matcher matcherA = Pattern.compile("(.+) \\(\"(\\d+)\"\\) Object: (. .-.-.):(\\d+) \\((.+)\\)( REMOTE)?").matcher(content);
        final Matcher matcherB = Pattern.compile("(.+) \\(\"(\\d+)\"\\) Object: (. .-.-.):(\\d+)( REMOTE)?").matcher(content);
        final Matcher matcherC = Pattern.compile("(.+) \\(\"(\\d+)\"\\) Object: <NULL-object>").matcher(content);
        final Matcher matcherD = Pattern.compile("(.+) \\(\"(\\d+)\"\\) Object: (\\w+)# (\\d+): (.+) REMOTE?").matcher(content);
        
        if (matcherA.matches()) {
            playerName = matcherA.group(1);
            try {
                playerId = Integer.parseInt(matcherA.group(2));
            } catch (final NumberFormatException ex) {
                playerId = -1;
            }
            unknownValue1 = matcherA.group(3);
            try {
                unknownValue2 = Integer.parseInt(matcherA.group(4));
            } catch (final NumberFormatException ex) {
                unknownValue2 = -1;
            }
            playerName2 = matcherA.group(5);
        } else if (matcherB.matches())   {
            playerName = matcherB.group(1);
            try {
                playerId = Integer.parseInt(matcherB.group(2));
            } catch (final NumberFormatException ex) {
                playerId = -1;
            }
            unknownValue1 = matcherB.group(3);
            try {
                unknownValue2 = Integer.parseInt(matcherB.group(4));
            } catch (final NumberFormatException ex) {
                unknownValue2 = -1;
            }
        } else if (matcherC.matches())   {
            playerName = matcherC.group(1);
            try {
                playerId = Integer.parseInt(matcherC.group(2));
            } catch (final NumberFormatException ex) {
                playerId = -1;
            }            
            unknownValue1 = null;
            unknownValue2 = -1;
        } else if (matcherD.matches())   {
            playerName = matcherD.group(1);
            try {
                playerId = Integer.parseInt(matcherD.group(2));
            } catch (final NumberFormatException ex) {
                playerId = -1;
            }
            try {
                unknownValue3 = Integer.parseInt(matcherD.group(3));
            } catch (final NumberFormatException ex) {
                unknownValue3 = -1;
            }
            try {
                unknownValue4 = Integer.parseInt(matcherD.group(4));
            } catch (final NumberFormatException ex) {
                unknownValue4 = -1;
            }
            playerSkin = matcherD.group(5);
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getUnknownValue1() {
        return unknownValue1;
    }

    public void setUnknownValue1(String unknownValue1) {
        this.unknownValue1 = unknownValue1;
    }

    public int getUnknownValue2() {
        return unknownValue2;
    }

    public void setUnknownValue2(int unknownValue2) {
        this.unknownValue2 = unknownValue2;
    }

    public String getPlayerName2() {
        return playerName2;
    }

    public void setPlayerName2(String playerName2) {
        this.playerName2 = playerName2;
    }

    public int getUnknownValue3() {
        return unknownValue3;
    }

    public void setUnknownValue3(int unknownValue3) {
        this.unknownValue3 = unknownValue3;
    }

    public int getUnknownValue4() {
        return unknownValue4;
    }

    public void setUnknownValue4(int unknownValue4) {
        this.unknownValue4 = unknownValue4;
    }

    public String getPlayerSkin() {
        return playerSkin;
    }

    public void setPlayerSkin(String playerSkin) {
        this.playerSkin = playerSkin;
    }
    
}
