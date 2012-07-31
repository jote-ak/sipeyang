/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sprimaudi.zkspring.filter;

/**
 * Filterable Field = Fielderable
 *
 * @author User
 */
public class Fielderable<E> {

    private String alias;
    private E field;
    private String operation;

    public Fielderable() {
    }

    public Fielderable(String alias, E field, String operation) {
        this.alias = alias;
        this.field = field;
        this.operation = operation;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public E getField() {
        return field;
    }

    public void setField(E field) {
        this.field = field;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
