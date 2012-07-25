package org.sprimaudi.zkspring.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: jote
 * Date: 7/24/12
 * Time: 10:44 PM
 * To change this template use File | Settings | File Templates.
 */
@Scope("prototype")
@Component(value = "mapper")
public class Mapper extends HashMap<String, Object> {
    public Mapper map(String key, Object val) {
        this.put(key, val);
        return this;
    }
}
