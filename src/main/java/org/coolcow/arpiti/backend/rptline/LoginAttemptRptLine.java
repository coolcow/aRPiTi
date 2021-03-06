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
public class LoginAttemptRptLine extends AbstractRptLine implements PlayerProvider {

    private static final Logger LOG = Logger.getLogger(LoginAttemptRptLine.class);
    
    private Player player;

    public LoginAttemptRptLine() {
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
        final Matcher matcher = Pattern.compile("\"(.*)\" (.*)").matcher(rawContent);
        
        if (matcher.matches()) {
            final String playerIdentifierString = matcher.group(1);
            final String playerNameString = matcher.group(2);
            
            final Player player = new Player();
            player.setIdentifier(playerIdentifierString);
            player.setName(playerNameString);            
            Backend.getInstance().updatePlayer(player);
            setPlayer(player);
            
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
