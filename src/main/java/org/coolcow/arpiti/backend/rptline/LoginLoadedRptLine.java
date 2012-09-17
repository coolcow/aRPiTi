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
public class LoginLoadedRptLine extends AbstractRptLine implements PlayerProvider {

    private static final Logger LOG = Logger.getLogger(LoginLoadedRptLine.class);
        
    private Player player;
    private String unknownValue1;
    private int unknownValue2;

    public LoginLoadedRptLine() {
        super();
    }

    public String getUnknownValue1() {
        return unknownValue1;
    }

    protected void setUnknownValue1(final String unknownValue1) {
        this.unknownValue1 = unknownValue1;
    }

    public int getUnknownValue2() {
        return unknownValue2;
    }

    protected void setUnknownValue2(final int unknownValue2) {
        this.unknownValue2 = unknownValue2;
    }

    public Player getPlayer() {
        return player;
    }

    protected void setPlayer(final Player player) {
        this.player = player;
    }

    @Override
    public boolean parseLine(final String line) {
        if (!super.parseLine(line)) {
            return false;
        }

        final String rawContent = getRawContent();
        final Matcher matcher = Pattern.compile("^(. .-.-.):(\\d+) \\((.*)\\) REMOTE Type: (.*)$").matcher(rawContent);
        
        if (matcher.matches()) {            
            final String unknownValue1String = matcher.group(1);
            final String unknownValue2String = matcher.group(2);
            final String playerNameString = matcher.group(3);
            final String skinNameString = matcher.group(4);
            
            final Player player = new Player();
            player.setName(playerNameString);
            player.setSkinName(skinNameString);
            Backend.getInstance().updatePlayer(player);
            setPlayer(player);
            
            setUnknownValue1(unknownValue1String);
            try {
                setUnknownValue2(Integer.parseInt(unknownValue2String));
            } catch (final NumberFormatException exception) {
                LOG.warn("Error while parsing unknownValue2. Set to -1. The source String was: " + unknownValue2String, exception);
                setUnknownValue2(-1);
            }

            return true;
        } else {
            return false;
        }
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
