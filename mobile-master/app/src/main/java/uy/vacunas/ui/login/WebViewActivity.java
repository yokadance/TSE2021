package uy.vacunas.ui.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uy.vacunas.MainActivity;
import uy.vacunas.MyInterface;
import uy.vacunas.R;

import java.util.Map;


public class WebViewActivity extends Activity {

    private WebView webView;
    private String name=null;
    private String jwt=null;
    private boolean ret = false;
    private String userInfo=null;

    class MyJavaScriptInterface  {
        public String result= null;
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void processHTML(String html) {
            // process the html as needed by the app
            String a= html;
            jwt=html;

        }

        public  String getResult(){
            return this.result;
        }
    }



    @SuppressLint("JavascriptInterface")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
        webView.setWebContentsDebuggingEnabled(true);
       webView.setWebViewClient(new WebViewClient(){



            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (!url.contains("auth-testing"))
                {
                    MyJavaScriptInterface i = new MyJavaScriptInterface();
                    //webView.loadUrl("javascript:window.HTMLOUT.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
                    webView.loadUrl("javascript:window.HTMLOUT.processHTML(document.getElementsByTagName('pre')[0].innerHTML);");

                    try {
                        Thread.sleep(5);
                        i.getResult();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    jwtResult(jwt);
                    // Load and use views afterwards
                }
            }
        });
       webView.loadUrl("https://auth-testing.iduruguay.gub.uy/oidc/v1/authorize?scope=openid%20personal_info%20email&response_type=code&client_id=890192&redirect_uri=http://vacunasuyg16.web.elasticloud.uy/VacunasUYG16-api/api/logingubuy/loginmobile&client_secret=457d52f181bf11804a3365b49ae4d29a2e03bbabe74997a2f510b179");

    }



    public void jwtResult(String jsonwt) {

        Retrofit retrofit= new Retrofit.Builder().baseUrl("https://vacunasuyg16.web.elasticloud.uy/VacunasUYG16-api/api/").addConverterFactory(GsonConverterFactory.create()).build();
        MyInterface i = retrofit.create(MyInterface.class);
        Call<ResponseBody> resp = i.getJWTresult(jwt);

        resp.enqueue(new Callback<ResponseBody>() {

                         @SuppressLint("ResourceType")
                         @RequiresApi(api = Build.VERSION_CODES.O)
                         @Override
                         public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                             int responseCode = response.code();
                             StringBuffer messageBuffer = new StringBuffer();
                             messageBuffer.append(responseCode);

                             if (responseCode == 202 || response.isSuccessful()) {
                                 JWT jwebt = new JWT(jwt);
                                 Gson gson = new Gson();

                                 //JsonObject jsonUser = new JsonObject(UserInfo);
                                 //crear un json con los paramtros de userInfo para almacenar los datos que se decodifican
                                 Map<String, Claim> allClaims = jwebt.getClaims();
                                 Claim cName = jwebt.getClaim("firstName");
                                 String name= cName.asString();
                                 Claim cSurName = jwebt.getClaim("firstSurname");
                                 String surName= cSurName.asString();
                                 Claim cCi= jwebt.getClaim("ci");
                                 String ci = cCi.asString();


                                 User user= new User(name, surName, ci);

                                 Toast.makeText(getApplicationContext(), "Bienvenido "+ name +" " + surName, Toast.LENGTH_LONG).show();
                                 ret = true;

                                 Intent MainIntent = new Intent(WebViewActivity.this, MainActivity.class);
                                 MainIntent.putExtra("name", user.getName().toString());
                                 MainIntent.putExtra("surname" , user.getSurNmae().toString());
                                 MainIntent.putExtra("ci", user.getCi().toString());

                                 startActivity(MainIntent);
                                 setResult(RESULT_OK, MainIntent);

                                 webView.clearHistory();
                                 webView.destroy();

                             } else {
                                 Intent MainIntent = new Intent(WebViewActivity.this, LoginActivity.class);
                                 startActivity(MainIntent);
                                 setResult(RESULT_CANCELED, MainIntent);
                                 Toast.makeText(getApplicationContext(), "Ha ocurrido un error", Toast.LENGTH_LONG).show();
                             }



                         }

                         @Override
                         public void onFailure(Call<ResponseBody> call, Throwable t) {


                         }
                     }

        );


    }

}
