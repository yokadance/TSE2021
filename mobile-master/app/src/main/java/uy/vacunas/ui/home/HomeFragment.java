package uy.vacunas.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.AsyncListUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uy.vacunas.MainActivity;
import uy.vacunas.MyAdapter;
import uy.vacunas.MyInterface;
import uy.vacunas.R;



public class HomeFragment extends Fragment {

    private ListView vaccinationListView;
//    private VaccinationPlanAdapter myAdapter;
    private String name="-";
    private String surname="-";
    private String ci;
    private static TextView m_resultHolder;



   // private HomeViewModel homeViewModel;

/*
    public static HomeFragment newInstance(Bundle arguments){
        HomeFragment f = new HomeFragment();
        if(arguments != null){
            f.setArguments(arguments);
        }
        return f;
    }*/


  /*  public void HomeFragment(){

    }
*/

    @Override
    public void onCreate(Bundle savedInsateState) {
        super.onCreate(savedInsateState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        name = MainActivity.nameS;
        surname = MainActivity.surnameS;
        ci = MainActivity.ciS;
        if(m_resultHolder != null) {
            m_resultHolder = (TextView) getActivity().findViewById(R.id.nameNavbar);
            m_resultHolder.setText(name + " " + surname);
        }
       return root;
    }



    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
