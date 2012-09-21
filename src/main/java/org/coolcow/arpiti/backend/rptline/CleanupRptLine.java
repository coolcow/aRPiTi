/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline;

/**
 *
 * @author jruiz
 */
public class CleanupRptLine extends AbstractRptLine {

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

    public void setIsZombie(boolean isZombie) {
        this.isZombie = isZombie;
    }

    public String getZombieIdentifier() {
        return zombieIdentifier;
    }

    public void setZombieIdentifier(String zombieIdentifier) {
        this.zombieIdentifier = zombieIdentifier;
    }

    public String getZombieType() {
        return zombieType;
    }

    public void setZombieType(String zombieType) {
        this.zombieType = zombieType;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public boolean isIsInitialize() {
        return isInitialize;
    }

    public void setIsInitialize(boolean isInitialize) {
        this.isInitialize = isInitialize;
    }

    public boolean isIsObject() {
        return isObject;
    }

    public void setIsObject(boolean isObject) {
        this.isObject = isObject;
    }

}
