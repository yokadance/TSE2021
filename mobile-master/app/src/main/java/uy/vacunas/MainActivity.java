package uy.vacunas;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uy.vacunas.ui.Certification.CertificationFragment;
import uy.vacunas.ui.Certification.VaccineAct;
import uy.vacunas.ui.Certification.VaccineVo;

public class MainActivity extends AppCompatActivity implements  CertificationFragment.OnFragmentInteractionListener {

    private AppBarConfiguration mAppBarConfiguration;
    public static   String nameS;
    public static   String surnameS;
    public static   String ciS;
    public static   String emailS;
    public static   String token;
    private static final String TAG = "MainActivity";
    //private static String baseUrl = "http://192.168.13.121:8080/VacunasUYG16-api/api/";
    private static String baseUrl = "http://vacunasuyg16.web.elasticloud.uy/VacunasUYG16-api/api/";
    private List <VaccineAct>vaccineActList = new ArrayList<>();
    private List <VaccineVo>vaccineList2 = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SETEO DE BUNDLE PARA ACCEDER DESDE OTROS FRAGMENTS A LOS VARIABLES GLOBALES





        Bundle bundle = getIntent().getExtras();
        nameS = bundle.getString("name");
        //nameS="PEPe";
        surnameS= bundle.getString("surname");
        //surnameS="peresz";
        ciS= bundle.getString("ci");
        //ciS="25446760";
        emailS= bundle.getString("email");
        //emailS="tets";

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_vaccinators, R.id.nav_certifications)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        TextView txtProfileName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nameNavbar);
        txtProfileName.setText(nameS + " " + surnameS);
        TextView txtProfileMail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.mailNavbar);
        txtProfileMail.setText("C.I: "+ ciS);


        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }else{
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults){
        switch (requestCode){
            case 1: {
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                    }
                }else{
                    Toast.makeText(this, "No podrá usa el servicio de ubicación", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }




        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        token = task.getResult();

                        Retrofit retrofit= new Retrofit.Builder().baseUrl("http://vacunasuyg16.web.elasticloud.uy/VacunasUYG16-api/api/").addConverterFactory(GsonConverterFactory.create()).build();
                        MyInterface i = retrofit.create(MyInterface.class);
                        Call<Void> resp = i.setToken(ciS, token);
                        retrofit2.Callback<Void> callback = new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if(response.isSuccessful()){
                                  ///  Toast.makeText(MainActivity.this, "Se inserto token", Toast.LENGTH_SHORT).show();
                                }else{
                                 //   Toast.makeText(MainActivity.this, "no se inserto token", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        };
                        resp.enqueue(callback);

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);
                      //  Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }

                });



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                finishAffinity();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity(); // or finish();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }




}