/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sprimaudi.zkspring.filter;

import java.util.Date;

/**
 * Filter Entry = Fentry
 *
 * @author User
 */
public class Fentry {

    private String alias;
    private String stringValue;
    private Date dateValue;
    private Number numberValue;
    private boolean range;
    private boolean endRange;
    private String type;

    public Fentry() {
    }

    public Fentry(String alias, String stringValue, Date dateValue, Number numberValue, boolean range, boolean endRange, String type) {
        this.alias = alias;
        this.stringValue = stringValue;
        this.dateValue = dateValue;
        this.numberValue = numberValue;
        this.range = range;
        this.endRange = endRange;
        this.type = type;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public boolean isEndRange() {
        return endRange;
    }

    public void setEndRange(boolean endRange) {
        this.endRange = endRange;
    }

    public Number getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(Number numberValue) {
        this.numberValue = numberValue;
    }

    public boolean isRange() {
        return range;
    }

    public void setRange(boolean range) {
        this.range = range;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
