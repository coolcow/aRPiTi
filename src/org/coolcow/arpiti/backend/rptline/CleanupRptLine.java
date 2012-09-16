/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

/**
 *
 * @author jruiz
 */
public class CleanupRptLine extends AbstractRptLine {

    private static final Logger LOG = Logger.getLogger(CleanupRptLine.class);
        
    private boolean isZombie = false;
    private boolean isInitialize = false;
    private boolean isObject = false;
    private String zombieIdentifier;
    private String zombieType;
    private String objectType;
    
    public CleanupRptLine() {
        super();
    }

    public boolean isIsZombie() {
        return isZombie;
    }

    protected void setIsZombie(boolean isZombie) {
        this.isZombie = isZombie;
    }

    public String getZombieIdentifier() {
        return zombieIdentifier;
    }

    protected void setZombieIdentifier(String zombieIdentifier) {
        this.zombieIdentifier = zombieIdentifier;
    }

    public String getZombieType() {
        return zombieType;
    }

    protected void setZombieType(String zombieType) {
        this.zombieType = zombieType;
    }

    public String getObjectType() {
        return objectType;
    }

    protected void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public boolean isIsInitialize() {
        return isInitialize;
    }

    protected void setIsInitialize(boolean isInitialize) {
        this.isInitialize = isInitialize;
    }

    public boolean isIsObject() {
        return isObject;
    }

    protected void setIsObject(boolean isObject) {
        this.isObject = isObject;
    }

    @Override
    public boolean parseLine(String line) {
        if (!super.parseLine(line)) {
            return false;
        }

        final String rawContent = getRawContent();
        final Matcher zombieMatcher = Pattern.compile("^DELETE UNCONTROLLED ZOMBIE: (\\w+) OF: (.+)$").matcher(rawContent);
        final Matcher objectMatcher = Pattern.compile("^DELETED A \"(\\w+)\"$").matcher(rawContent);
        final Matcher initializeMatcher = Pattern.compile("^INITIALIZING CLEANUP SCRIPT$").matcher(rawContent);        
        
        if (zombieMatcher.matches()) {
            setIsZombie(true);
            
            final String zombieTypeString = zombieMatcher.group(1);
            final String zombieIdentifierString = zombieMatcher.group(2);
            
            setZombieType(zombieTypeString);
            setZombieIdentifier(zombieIdentifierString);
            
            return true;
        } else if (objectMatcher.matches()) {
            setIsObject(true);
            
            final String zombieTypeString = objectMatcher.group(1);
            
            setObjectType(zombieTypeString);
            
            return true;
        } else if (initializeMatcher.matches()) {
            setIsInitialize(true);
            
            return true;
        } else {
            return false;
        }
    }
}
