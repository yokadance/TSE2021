package uy.vacunas.ui.VaccinationCenter;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uy.vacunas.MyAdapter;
import uy.vacunas.MyInterface;
import uy.vacunas.R;

public class VaccinatorsFragment extends Fragment {

    private ArrayList<LatLng> locationArrayList = new ArrayList<LatLng>();
    //private static String baseUrl = "http://192.168.13.121:8080/VacunasUYG16-api/api/";
    private static String baseUrl = "https://vacunasuyg16.web.elasticloud.uy/VacunasUYG16-api/api/";
    private List<VaccinationCenters> vaccinationCenters = new ArrayList<VaccinationCenters>();
    private GoogleMap gMap;
    private LatLng myLatLng;
    private LatLng position, position1;
    private float lat, lan, lat1, lan1;
    private Marker mCurrLocationMar;
    LocationRequest mLocationRequest;

    static double PI_RAD = Math.PI / 180.0;


    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        getData();
        View view = inflater.inflate(R.layout.fragment_vaccinator_centers_maps, container, false);
        //inicio
        LatLng v1 = new LatLng(-34.907958546918636, -56.188465204047944);

        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map);
        FusedLocationProviderClient client;
        client = LocationServices.getFusedLocationProviderClient(getActivity());

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {
                getData();
                gMap=googleMap;
                getData();
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    //PERISOS DE ACCESO A UBICACION
                    getCurrentLocation();
                    googleMap.setMyLocationEnabled(true);
                    googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                    for (int i = 0; i < vaccinationCenters.size(); i++) {
                        lat = Float.parseFloat(vaccinationCenters.get(i).getLatitude());
                        lan= Float.parseFloat(vaccinationCenters.get(i).getLongitude());
                        position = new LatLng(lat,lan );
                        googleMap.addMarker(new MarkerOptions().position(position).title(vaccinationCenters.get(i).getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

                    }
                    float zoomLevel =19.0f; //up to 21
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(v1, zoomLevel));

                }else{

                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
                }

            }


            private void getCurrentLocation() {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Task<Location> task = client.getLastLocation();
                    task.addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if(location !=null){
                                supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                                    @Override
                                    public void onMapReady(GoogleMap googleMap) {
                                        getData();
                                       for (int h = 0; h < vaccinationCenters.size(); h++) {
                                            // below line is use to add marker to each location of our array list.
                                            lat1 = Float.parseFloat(vaccinationCenters.get(h).getLatitude());
                                            lan1= Float.parseFloat(vaccinationCenters.get(h).getLongitude());
                                            position1 = new LatLng(lat1,lan1 );
                                            //googleMap.addMarker(new MarkerOptions().position(position1).title("Nombre").snippet("Descripcion").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                                            googleMap.addMarker(new MarkerOptions().position(position1).title(vaccinationCenters.get(h).getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

                                        }

                                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,14));

                                        Circle circle = googleMap.addCircle(new CircleOptions()
                                                .center(new LatLng(location.getLatitude(), location.getLongitude()))
                                                .radius(1000)
                                                .strokeColor(Color.TRANSPARENT)
                                                .strokeWidth(1)
                                                .fillColor(0x220000FF));

                                        drawCircle(new LatLng(location.getLatitude(), location.getLongitude()));


                                    }
                                });
                            }
                        }
                    });
                }


            }


        });

        return view;

    }


    private void drawCircle(LatLng point) {

        // Instantiating CircleOptions to draw a circle around the marker
        CircleOptions circleOptions = new CircleOptions();
        // Specifying the center of the circle
        circleOptions.center(point);
        // Radius of the circle
        circleOptions.radius(100);
        // Border color of the circle
        circleOptions.strokeColor(Color.BLACK);
        // Fill color of the circle
        circleOptions.fillColor(0x30ff0000);
        // Border width of the circle
        circleOptions.strokeWidth(2);
        // Adding the circle to the GoogleMap
        gMap.addCircle(circleOptions);


    }

    public void getData(){

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        MyInterface i = retrofit.create(MyInterface.class);
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
        Call<List<VaccinationCenters>> call = MyAdapter.myinterfaceManagerService(gsonConverterFactory).getVaccinationCenter();

        // Set up progress before call
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Cargando vacunatorios....");
        progressDoalog.setTitle("Aguarde por favor");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // show it
        progressDoalog.show();

        call.enqueue(new Callback<List<VaccinationCenters>>() {
            @Override
            public void onResponse(Call<List<VaccinationCenters>> call, Response<List<VaccinationCenters>> response) {
                if(response.isSuccessful()){
                    vaccinationCenters=response.body();
                    if(vaccinationCenters.size()>0) {
                        progressDoalog.dismiss();

                    }
                }
            }

            @Override
            public void onFailure(Call<List<VaccinationCenters>> call, Throwable t) {

            }
        });
    }





}



