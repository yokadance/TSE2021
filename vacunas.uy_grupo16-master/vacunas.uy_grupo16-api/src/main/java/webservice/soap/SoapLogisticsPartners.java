package webservice.soap;

import DTO.DTBatch;
import DTO.DTPackage;
import DTO.DTSchedule;
import DTO.DTSendPendingPackage;
import IController.IBatchController;
import IController.IPackageController;
import IController.IScheduleController;
import enumerations.PackageState;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService(portName = "logisticsPartners")
public class SoapLogisticsPartners {

    @EJB
    IPackageController iPackageController;

    @EJB
    IBatchController iBatchController;

    @WebResult(name = "partners", targetNamespace = "")
    @WebMethod(operationName = "sendPendings")
    public List<DTSendPendingPackage> sendScheduleById(Long id) {
        System.out.println(iPackageController.getPendingPackages(id).size());
        return iPackageController.getPendingPackages(id);

    }

    @WebMethod(operationName = "receiveChangeStatus")
    public void receiveChangeStatus(String list) {

        System.out.println(list);
        String str = list;
        str = str.replace("[", "");
        str = str.replace("]", "");
        str = str.replace(" ", "");
        str = str.replace(",", "-");

        String[] res = str.split("-", 0);

       for (int i = 0; i < res.length; i++) {


            System.out.println(res[i]);
            Long id = Long.parseLong(res[i]);
            System.out.println(res[i+1]);
            System.out.println(res[i+2]);
            String status = res[i+2];

            //DTPackage aPackage = iPackageController.getByIdPackage(id);
            if (status.equals("Delivered")){
                iPackageController.packageToDelivered(id);
            }
            if (status.equals("InTransit")){
                iPackageController.packageToInTransit(id);
            }

            i = i+2;
        }


    }


}
