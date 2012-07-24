package org.sprimaudi.zkutil.lookuper;

import org.apache.commons.collections.CollectionUtils;
import org.sprimaudi.zkspring.entity.Unit;
import org.sprimaudi.zkspring.repository.UnitRepository;
import org.sprimaudi.zkutil.lookup.LookupColumn;
import org.sprimaudi.zkutil.lookup.LookupUtil;
import org.springframework.stereotype.Component;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: 7/23/12
 * Time: 8:46 PM
 * To change this template use File | Settings | File Templates.
 */
@Component(value = "unitLookuper")
public class UnitLookuper extends LookupUtil<Unit> {
    @Inject
    UnitRepository unitRepository;

    @Override
    public void render(Listitem listitem, Unit unit, int i) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.  ]
        new Listcell(unit.getKode()).setParent(listitem);
        new Listcell(unit.getNama()).setParent(listitem);
    }

    @Override
    public List<Unit> getModel(String lookupParams) {
        List<Unit> uns = new ArrayList<Unit>();
        CollectionUtils.addAll(uns, unitRepository.findAll().iterator());
        return uns;
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public LookupColumn[] getColumns() {
        return new LookupColumn[]{
                new LookupColumn("Kode", "35%"),
                new LookupColumn("Unit", "65%")
        };  //To change body of implemented methods use File | Settings | File Templates.
    }
}
