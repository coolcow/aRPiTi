/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.arpiti.backend.rptline;

import java.util.Date;

/**
 *
 * @author jruiz
 */
public interface RptLine {

    public enum Type {

        SECOND_HAND_ZOMBIE_INITIALIZED {
            @Override
            public String toString() {
                return "Second Hand Zombie Initialized";
            }
        }, DW_DEBUG_FPS {
            @Override
            public String toString() {
                return "DW_DEBUG FPS";
            }
        }, LOCALITY_EVENT {
            @Override
            public String toString() {
                return "Locality Event";
            }
        }, WRITE, READ_WRITE {
            @Override
            public String toString() {
                return "READ/WRITE";
            }
        }, STARTING_LOGIN {
            @Override
            public String toString() {
                return "STARTING LOGIN";
            }
        }, LOGIN_LOADED {
            @Override
            public String toString() {
                return "LOGIN LOADED";
            }
        }, LOGIN_PUBLISHING {
            @Override
            public String toString() {
                return "LOGIN PUBLISHING";
            }
        }, LOGIN_ATTEMPT {
            @Override
            public String toString() {
                return "LOGIN ATTEMPT";
            }
        }, DISCONNECT_START_I {
            @Override
            public String toString() {
                return "DISCONNECT START (i)";
            }
        }, PDEATH, DELETE, ERROR, OBJ, HIVE, CLEANUP
    };

    Date getDate();

    int getNumber();

    Type getType();

    String getRawLine();

    String getRawContent();

    boolean parseLine(final String line);
}
