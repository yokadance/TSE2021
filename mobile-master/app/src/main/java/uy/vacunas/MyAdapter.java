package uy.vacunas;

import retrofit2.Converter;
import retrofit2.Retrofit;

public class MyAdapter {
        private static String baseUrl = "https://vacunasuyg16.web.elasticloud.uy/VacunasUYG16-api/api/";
       // private static String baseUrl = "http://192.168.13.121:8080/VacunasUYG16-api/api/";

    public static MyInterface myinterfaceManagerService(Converter.Factory converterFactory)
    {
        // Create retrofit builder.
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();

        // Set base url. All the @POST @GET url is relative to this url.
        retrofitBuilder.baseUrl(baseUrl);

        /* The converter factory can be GsonConverterFactory( Convert response text to json object. ),
           if the value is null then convert response text okhttp3.ResponseBody. */
        if(converterFactory != null ) {
            retrofitBuilder.addConverterFactory(converterFactory);
        }

        // Build the retrofit object.
        Retrofit retrofit = retrofitBuilder.build();

        //Create instance for user manager interface and return it.
        MyInterface myInterfaceManagerService = retrofit.create(MyInterface.class);
        return myInterfaceManagerService;
    }
}