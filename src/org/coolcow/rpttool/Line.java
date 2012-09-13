/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.coolcow.rpttool;

import java.util.Date;

/**
 *
 * @author jruiz
 */
public class Line {
    
    private int number;
    private Date date;
    private String content;

    public Line(int number, Date date, String content) {
        this.number = number;
        this.date = date;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
}
