package uy.vacunas;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import uy.vacunas.ui.Certification.VaccineAct;
import uy.vacunas.ui.VaccinationCenter.VaccinationCenters;


public interface MyInterface {

    @POST("login/checkLoginGubUY/{jwt}")
    Call <ResponseBody> getJWTresult(@Path("jwt") String jwt);

    @GET("vaccionationCenter/listVaccinationsCenters")
    public Call<List<VaccinationCenters>> getVaccinationCenter();

    @GET("VaccinationAct/vaccinationActByCi/{ci}")
    Call<List<VaccineAct>> getVaccineActs(@Path("ci") String ci);

    @PUT("citizen/setToken/{ci}/{token}")
    public Call<Void> setToken(@Path("ci") String ci, @Path("token") String token);

}
