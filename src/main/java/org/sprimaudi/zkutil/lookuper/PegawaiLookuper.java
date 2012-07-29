package org.sprimaudi.zkutil.lookuper;

import com.djbc.utilities.StringUtil;
import org.sprimaudi.zkspring.entity.Pegawai;
import org.sprimaudi.zkspring.repository.PegawaiRepository;
import org.sprimaudi.zkutil.lookup.LookupColumn;
import org.sprimaudi.zkutil.lookup.LookupUtil;
import org.springframework.stereotype.Component;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jote
 * Date: 7/28/12
 * Time: 10:39 AM
 * To change this template use File | Settings | File Templates.
 */
@Component(value = "pegawaiLookuper")
public class PegawaiLookuper extends LookupUtil<Pegawai, Long> {
    @Inject
    PegawaiRepository pegawaiRepository;

    @Override
    public void rendering(Listitem listitem, Pegawai pegawai, int i) throws Exception {
        new Listcell(StringUtil.nvl(pegawai.getNama())).setParent(listitem);
        new Listcell(StringUtil.nvl(pegawai.getNip())).setParent(listitem);
        new Listcell(StringUtil.nvl(
                (pegawai.getPangkat() != null) ? pegawai.getPangkat().getNama() : null)
        ).setParent(listitem);
        new Listcell(StringUtil.nvl(
                (pegawai.getJabatan() != null) ? pegawai.getJabatan().getNama() : null)
        ).setParent(listitem);
        new Listcell(StringUtil.nvl(
                (pegawai.getUnit() != null) ? pegawai.getUnit().getNama() : null)
        ).setParent(listitem);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getDisplayer(Pegawai data) {
        return data.getNama();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Pegawai getById(Long id) {
        return pegawaiRepository.findOne(id);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Long getKey(Pegawai data) {
        return data.getId();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Pegawai> getModel(String lookupParams) {
        return pegawaiRepository.lookupNipNama(lookupParams);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public LookupColumn[] getColumns() {
        return new LookupColumn[]{
                new LookupColumn("Nama", "40%"),
                new LookupColumn("NIP", "20%"),
                new LookupColumn("Pangkat", "10%"),
                new LookupColumn("Jabatan", "10%"),
                new LookupColumn("Unit", "20%")
        };  //To change body of implemented methods use File | Settings | File Templates.
    }


}
