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
public class PDeathRptLine extends AbstractRptLine implements PlayerProvider {

    private static final Logger LOG = Logger.getLogger(PDeathRptLine.class);
    
    private Player player;
    
    public PDeathRptLine() {
        super();
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

    @Override
    public boolean parseLine(final String line) {
        if (!super.parseLine(line)) {
            return false;
        }

        final String rawContent = getRawContent();
        final Matcher matcher = Pattern.compile("^Player Died (\\d+)$").matcher(rawContent);
        
        if (matcher.matches()) {
            final String playerIdentifierString = matcher.group(1);
            
            final Player player = new Player();
            player.setIdentifier(playerIdentifierString);
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
