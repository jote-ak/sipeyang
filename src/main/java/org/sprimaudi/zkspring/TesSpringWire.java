package org.sprimaudi.zkspring;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/19/12
 * Time: 9:29 AM
 * To change this template use File | Settings | File Templates.
 */
@Component(value = "tsw")
public class TesSpringWire {
    public String tes() {
        return "from wired " + UUID.randomUUID().toString();
    }
}
