package DAL;

import DTO.DTRestriction;
import DTO.DTRestrictionAPI;
import IDAL.IRestrictionData;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Restriction;
import entities.VaccinationPlan;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Singleton
public class RestrictionData implements IRestrictionData {

  @PersistenceContext(name = "backend")
  private EntityManager data;

  ModelMapper modelMapper = new ModelMapper();

  @Override
  public Long saveRestriction(DTRestriction dtRestriction) {
    Restriction restriction = modelMapper.map(dtRestriction, Restriction.class);
    if (restriction.getId() == null) {
      data.persist(restriction);
    } else {
      restriction.setCreateDate(data.find(Restriction.class, restriction.getId()).getCreateDate());
      data.merge(restriction);
    }
    data.flush();
    return restriction.getId();
  }

  @Override
  public DTRestriction getRestrictionById(Long id) {
    DTRestriction dtRestriction = data.find(Restriction.class, id).getDTRestriction();
    return dtRestriction;
  }

  @Override
  public void deleteRestriction(Long id) {
    Restriction restriction = data.find(Restriction.class, id);
    restriction.setDeleteDate(new Date());
    data.merge(restriction);
  }

  @Override
  public List<DTRestriction> listRestrictions() {
    List<Restriction> restrictionList = data.createQuery("select r from Restriction r where r.deleteDate is null").getResultList();
    List<DTRestriction> dtRestrictionsList = new ArrayList<DTRestriction>();
    restrictionList.forEach(
      restriction -> {
        DTRestriction dtRestriction = restriction.getDTRestriction();
        dtRestrictionsList.add(dtRestriction);
      }
    );
    return dtRestrictionsList;
  }

  @Override
  public List<DTRestriction> getRestrictionsByPlan(Long idVP) {
    Query query = data.createQuery("select r from Restriction r where r.deleteDate is null");
    List<Restriction> restrictionList = query.getResultList();
    List<DTRestriction> dtRestrictionList = new ArrayList<>();

    if (restrictionList.size() > 0) {
      for (Restriction rest : restrictionList) {
        if (rest.getVaccinationPlans().size() > 0) {
          List<VaccinationPlan> vacPlanList = rest.getVaccinationPlans();
          for (VaccinationPlan vacPlan : vacPlanList) {
            //if (vacPlan.getId() == idVP) dtRestrictionList.add(rest.getDTRestriction());
            if (vacPlan.getId().equals(idVP)) dtRestrictionList.add(rest.getDTRestriction());
          }
        }
      }
    }
    return dtRestrictionList;
  }

  @Override
  public List<DTRestriction> getRestrictionByData(Long idDS) {
    Query query = data.createQuery("select r from Restriction r where r.deleteDate is null");
    List<Restriction> restrictionList = query.getResultList();
    List<DTRestriction> dtRestrictionList = new ArrayList<>();

    if (restrictionList.size() > 0) {
      for (Restriction rest : restrictionList) {
        if (rest.getDataSource() != null) {
          //if (rest.getDataSource().getId() == idDS) dtRestrictionList.add(rest.getDTRestriction());
          if (rest.getDataSource().getId().equals(idDS)) dtRestrictionList.add(rest.getDTRestriction());
        }
      }
    }
    return dtRestrictionList;
  }

  //////////////////////////////////////

  @Override
  public List<DTRestriction> getRestrictionsByVaccinationPlan(Long vaccinationPlanId) {
    VaccinationPlan vaccinationPlan1 = data.find(VaccinationPlan.class, vaccinationPlanId);
    List<Restriction> listRestrictions = vaccinationPlan1.getRestriction();
    List<DTRestriction> dtRestrictionList = new ArrayList<>();
    DTRestriction dtr = new DTRestriction();

    if (listRestrictions.size() > 0) {
      for (Restriction rest : listRestrictions) {
        dtr = rest.getDTRestriction();
        dtr.setVaccinationPlans(new ArrayList<>());
        dtRestrictionList.add(dtr);
      }
    }

    return dtRestrictionList;
  }

  @Override
  public String callAgeRestrictionApi(int ci, Long vaccinationPlan) {
    String responseService = "";
    VaccinationPlan vaccinationPlan1 = data.find(VaccinationPlan.class, vaccinationPlan);
    List<Restriction> listRestrictions = vaccinationPlan1.getRestriction();

    JSONObject jsonObject = new JSONObject();

    for (Restriction restriction : listRestrictions) {
      String URL = restriction.getDataSource().getUrl();
      String dtRestrictionAPIJson = "";
      DTRestrictionAPI dtRestrictionAPI = new DTRestrictionAPI();
      dtRestrictionAPI.setElementName(restriction.getElementName());
      dtRestrictionAPI.setLogicOperator(restriction.getLogicOperator().name());
      dtRestrictionAPI.setValue(ci);

      try {
        ObjectMapper dtRestrictionAPIMapp = new ObjectMapper();
        dtRestrictionAPIJson = dtRestrictionAPIMapp.writeValueAsString(dtRestrictionAPI);
      } catch (IOException e) {
        e.printStackTrace();
      }

      try {
        URL url = new URL(URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
          byte[] input = dtRestrictionAPIJson.getBytes("utf-8");
          os.write(input, 0, input.length);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
          StringBuilder response = new StringBuilder();
          String responseLine = null;
          while ((responseLine = br.readLine()) != null) {
            response.append(responseLine.trim());
          }
          jsonObject.put(restriction.getElementName(), response.toString());
          responseService = (responseService + (restriction.getElementName() + ":" + response.toString()) + "\n");
        }
      } catch (ProtocolException e) {
        e.printStackTrace();
      } catch (MalformedURLException e) {
        e.printStackTrace();
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    if (responseService != null) {
      //return responseService;
      return jsonObject.toString();
    }

    return "error";
  }

  public void setData(EntityManager data) {
    this.data = data;
  }

  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }
}
