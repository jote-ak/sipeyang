/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sprimaudi.zkspring.repository;

import org.sprimaudi.zkspring.filter.Fentry;

import java.util.List;

/**
 * @author User
 */
public interface FilterRepository<B> {

    public Iterable<B> filter(List<Fentry> filterItem);
}
