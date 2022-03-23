package com.logistics.prod.Interfaces;

import com.logistics.prod.Entities.Partner;
import net.bytebuddy.asm.Advice;
import org.w3c.dom.Document;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IPartnerController {

    public void savePartner(Partner partner);
    Document parseXmlFile(String in);
    public void receivePending(Long id);
    public void changePackageStatusTransit (Long idPackage);
    public void changePackageStatusDelivered (Long idPackage);
    public String getChangeStatus();
    public void sendChangedStatus ();
    public List<Partner> getPartners();
}
