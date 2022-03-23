package uy.vacunas.ui.Certification;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import uy.vacunas.R;


public class VaccineCertAdapter extends RecyclerView.Adapter<VaccineCertAdapter.VaccineCertAdapterViewHolder> implements  View.OnClickListener{

    List<VaccineVo> vaccineList;
    private View.OnClickListener listener;

    public VaccineCertAdapter(List<VaccineVo> vaccineList) {
        this.vaccineList=vaccineList;
    }

    @Override
    public VaccineCertAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        view.setOnClickListener(this);
        return new VaccineCertAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VaccineCertAdapterViewHolder holder, int position) {
        holder.txtName.setText("Vacuna " + vaccineList.get(position).getVaccineName());
        holder.txtPlaname.setText("Enfermedad: " + vaccineList.get(position).getVaccinePlan());
        holder.txtDate.setText("Fecha: "+ vaccineList.get(position).getDate());
        holder.foto.setImageResource(R.drawable.ic_vaccine);
    }

    @Override
    public int getItemCount() {
        return vaccineList.size();
    }

   @Override
    public void onClick(View v) {
        if(listener!= null){
            listener.onClick(v);
        }
    }


   public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public class VaccineCertAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtPlaname, txtDate;
        ImageView foto;

        public VaccineCertAdapterViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.idName);
            txtPlaname = (TextView) itemView.findViewById(R.id.idNamePlan);
            txtDate = (TextView) itemView.findViewById(R.id.idDate);
            foto= (ImageView) itemView.findViewById(R.id.idImg);
        }
    }
}