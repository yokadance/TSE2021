package uy.vacunas.ui.Certification;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joanzapata.pdfview.PDFView;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uy.vacunas.MainActivity;
import uy.vacunas.MyInterface;
import uy.vacunas.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CertificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CertificationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int PERMISSION_REQUEST_CODE = 200;
    private OnFragmentInteractionListener mListener;
    private Button cert;
    private String name = "-";
    private String surname="-";
    private String ci;
    int pageHeight = 1120;
    int pagewidth = 792;
    Bitmap bmp, scaledbmp, header, planVacNacional, scaledBmpHeader, scaledBmpPnV;
    private PDFView pdfView;
    //private static String baseUrl = "http://192.168.13.121:8080/VacunasUYG16-api/api/";
    private static String baseUrl = "https://vacunasuyg16.web.elasticloud.uy/VacunasUYG16-api/api/";
    //private List<VaccineVo> vaccineList2 = new ArrayList<>();


    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;

    RecyclerView recycleVaccines;
    ArrayList<VaccineVo> vaccineList;
    List<VaccineAct> vaccineActList;

    public CertificationFragment() {
        // Required empty public constructor
    }


    public static CertificationFragment newInstance(String param1, String param2) {
        CertificationFragment fragment = new CertificationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista=inflater.inflate(R.layout.fragment_certification, container, false);
        // Inflate the layout for this fragment

        cert = (Button) vista.findViewById(R.id.cert);
        cert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // here you set what you want to do when user clicks your button,
                AlertDialog.Builder builderD= new AlertDialog.Builder(getContext());
                builderD.setTitle("Certificado de vacunacion");
                builderD.setMessage("Quieres ver tu certificado de vacunas?");
                builderD.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.body2);
                        planVacNacional= BitmapFactory.decodeResource(getResources(), R.drawable.esquema_vacunas);
                        header = BitmapFactory.decodeResource(getResources(), R.drawable.header_cert);
                        scaledBmpHeader = Bitmap.createScaledBitmap(header, 792, 130,false);
                        scaledBmpPnV = Bitmap.createScaledBitmap(planVacNacional, 797,440,false);
                        scaledbmp = Bitmap.createScaledBitmap(bmp, 140, 140, false);
                        generatePDF();
                    }
                });
                builderD.setNegativeButton("Cancelar", null);
                AlertDialog dialog = builderD.create();
                dialog.show();
            }
        });

        name = MainActivity.nameS;
        surname = MainActivity.surnameS;
        ci = MainActivity.ciS;
        TextView m_resultHolder = (TextView) getActivity().findViewById(R.id.nameNavbar);
        m_resultHolder.setText(name + " " + surname);

        vaccineList = new ArrayList<>();
        vaccineActList = new ArrayList<VaccineAct>();

        recycleVaccines = vista.findViewById(R.id.recyclerId);
        recycleVaccines.setLayoutManager(new LinearLayoutManager(getContext()));

        VaccineCertAdapter adapter = new VaccineCertAdapter(vaccineList);

        Retrofit retrofit= new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        MyInterface i = retrofit.create(MyInterface.class);
        Call<List<VaccineAct>> response = i.getVaccineActs(ci);
        retrofit2.Callback<List<VaccineAct>> callback = new Callback<List<VaccineAct>>() {
            @Override
            public void onResponse(Call<List<VaccineAct>> call, Response<List<VaccineAct>> response) {

                vaccineActList = response.body();
                if(response.isSuccessful()) {
                    for (int i = 0; i < vaccineActList.size(); i++) {
                        vaccineList.add(new VaccineVo(vaccineActList.get(i).getVaccine(), (String) vaccineActList.get(i).getDisease(), vaccineActList.get(i).getVaccinationActDate(), R.drawable.ic_vaccine));
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<VaccineAct>> call, Throwable t) {

            }
        };

        response.enqueue(callback);


        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        recycleVaccines.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return vista ;

        }



/*    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
*/

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void generatePDF() {
        // creating an object variable
        // for our PDF document.
        PdfDocument pdfDocument = new PdfDocument();

        // two variables for paint "paint" is used
        // for drawing shapes and we will use "title"
        // for adding text in our PDF file.
        Paint paint = new Paint();
        Paint title = new Paint();
        Paint txt = new Paint();

        // we are adding page info to our PDF file
        // in which we will be passing our pageWidth,
        // pageHeight and number of pages and after that
        // we are calling it to create our PDF.
        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(pagewidth, pageHeight, 1).create();

        // below line is used for setting
        // start page for our PDF file.
        PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);

        // creating a variable for canvas
        // from our page of PDF.
        Canvas canvas = myPage.getCanvas();

        // below line is used to draw our image on our PDF file.
        // the first parameter of our drawbitmap method is
        // our bitmap
        // second parameter is position from left
        // third parameter is position from top and last
        // one is our variable for paint.
        canvas.drawBitmap(scaledBmpHeader, 0, 0, paint);
        canvas.drawBitmap(scaledBmpPnV, 0,131, paint);

        // below line is used for adding typeface for
        // our text which we will be adding in our PDF file.
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        // below line is used for setting text size
        // which we will be displaying in our PDF file.
        title.setTextSize(18);

        // below line is sued for setting color
        // of our text inside our PDF file.
        title.setColor(ContextCompat.getColor(getContext(), R.color.black));

        // below line is used to draw text in our PDF file.
        // the first parameter is our text, second parameter
        // is position from start, third parameter is position from top
        // and then we are passing our variable of paint which is title.
        //canvas.drawText("Certificado de vacunacion .", 209, 441, title);
        //canvas.drawText("VACUNAS UY", 209, 443, title);

        // similarly we are creating another text and in this
        // we are aligning this text to center of our PDF file.
        title.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
        title.setColor(ContextCompat.getColor(getContext(), R.color.azzurro));
        title.setTextSize(24);
        txt.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        txt.setColor(ContextCompat.getColor(getContext(), R.color.black));
        txt.setTextSize(15);


        // below line is used for setting
        // our text to center of PDF.
        title.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("Nombre:                         " + name + " " + surname , 30, 655, title);
        canvas.drawText("Documento:                  " + ci, 30, 680, title);
        canvas.drawText("Fecha de nacimiento:  " + "28/03/1980", 30, 705,title);
        final RectF rect2 = new RectF();
        paint.setStyle(Paint.Style.STROKE);
        rect2.set(10, 600, 782, 730);
        canvas.drawRoundRect(rect2, 10, 10, paint);


        int renglon =770;
        for(int i =0; i<vaccineList.size(); i++){

            canvas.drawText("√ " + " Vacuna " + vaccineList.get(i).getVaccineName() + ",  inmuniza contra "+ vaccineActList.get(i).getDisease().toString() +", fecha de vacuna " + vaccineList.get(i).getDate(), 30, renglon, txt);
            renglon= renglon + 30;
        }
       // canvas.drawText("SU CERTIFICADO ESTA AQUI."+ vaccineList.get(recycleVaccines.getChildAdapterPosition(v)).getVaccinePlan(), 209, 250, title);
        final RectF rect = new RectF();
        paint.setStyle(Paint.Style.STROKE);
        rect.set(10, 740, 782, renglon+30);
        canvas.drawRoundRect(rect, 10, 10, paint);        // after adding all attributes to our
        // PDF file we will be finishing our page.
        pdfDocument.finishPage(myPage);

        // below line is used to set the name of
        // our PDF file and its path.
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
        Date date = new Date();

        String name = "vacunasUY_Certificado.pdf";
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), dateFormat.format(date)+name);

        try {
            // after creating a file name we will
            // write our PDF file to that location.
            pdfDocument.writeTo(new FileOutputStream(file));



            // below line is to print toast message
            // on completion of PDF generation.
            Toast.makeText(getContext(), "Se ha guardado en tu dispositivo el certificado.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            // below line is used
            // to handle error
            e.printStackTrace();
        }
        // after storing our pdf to that
        // location we are closing our PDF file.
        pdfDocument.close();

    }


   /* @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                // after requesting permissions we are showing
                // users a toast message of permission granted.
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (writeStorage && readStorage) {
                    Toast.makeText(getContext(), "Permission Granted..", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Permission Denined.", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }*/







}