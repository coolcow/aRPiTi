/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.coolcow.arpiti.backend.Backend;
import org.coolcow.arpiti.backend.Player;
import org.coolcow.arpiti.backend.rptline.interfaces.PlayerProvider;

/**
 *
 * @author jruiz
 */
public class HiveRptLine extends AbstractRptLine implements PlayerProvider {

    private static final Logger LOG = Logger.getLogger(HiveRptLine.class);
    
    private boolean typeWrite = false;    
    
    private Player player;
    private int unknownValue1;
    private int unknownValue2;

    public HiveRptLine() {
        super();
    }

    public boolean isTypeWrite() {
        return typeWrite;
    }

    protected void setTypeWrite(final boolean typeWrite) {
        this.typeWrite = typeWrite;
    }

    public Player getPlayer() {
        return player;
    }

    protected void setPlayer(final Player player) {
        if (player != null) {
            Backend.getInstance().updatePlayer(player);
        }
        this.player = player;
    }

    public int getUnknownValue1() {
        return unknownValue1;
    }

    protected void setUnknownValue1(final int unknownValue1) {
        this.unknownValue1 = unknownValue1;
    }

    public int getUnknownValue2() {
        return unknownValue2;
    }

    protected void setUnknownValue2(final int unknownValue2) {
        this.unknownValue2 = unknownValue2;
    }
        
    @Override
    public boolean parseLine(String line) {
        if (!super.parseLine(line)) {
            return false;
        }

        final String rawContent = getRawContent();
        final Matcher writeMatcher = Pattern.compile("^WRITE: \"CHILD:(\\d+):(\\w+):(\\d+):(\\d+):\"$").matcher(rawContent);
        
        if (writeMatcher.matches()) {
            setTypeWrite(true);
            
            final String unknownValue1String = writeMatcher.group(1);
            final String playerIdentifierString = writeMatcher.group(2);
            final String hiveIdString = writeMatcher.group(3);
            final String unknownValue2String = writeMatcher.group(4);

            final Player player = new Player();
            player.setIdentifier(playerIdentifierString);            
            if (hiveIdString != null) {
                try {
                    player.setHiveId(Integer.parseInt(hiveIdString));
                } catch (final NumberFormatException exception) {
                    LOG.warn("Error while parsing hiveIdentifier." + hiveIdString, exception);
                }                
            }
            
            setPlayer(player);            
            try {
                setUnknownValue1(Integer.parseInt(unknownValue1String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue1. Set to -1. The source String was: " + unknownValue1String, exception);
                setUnknownValue1(-1);
            }
            try {
                setUnknownValue2(Integer.parseInt(unknownValue2String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue2. Set to -1. The source String was: " + unknownValue2String, exception);
                setUnknownValue2(-1);
            }
        } 
        
        return true;
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
