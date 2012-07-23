package org.sprimaudi.zkutil.lookup;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/23/12
 * Time: 6:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class LookupColumn {
    private String label;
    private String width;

    public LookupColumn() {
    }

    public LookupColumn(String label, String width) {
        this.width = width;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
}
