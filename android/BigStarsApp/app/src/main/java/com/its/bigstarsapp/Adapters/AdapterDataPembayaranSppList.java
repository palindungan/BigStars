package com.its.bigstarsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.BayarSpp;
import com.its.bigstarsapp.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterDataPembayaranSppList extends RecyclerView.Adapter<AdapterDataPembayaranSppList.DataPembayaranSppListViewHolder> {
    Context context;
    ArrayList<BayarSpp> dataModelArrayList;

    private static ClickListener clickListener;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    String statusActivity;

    public AdapterDataPembayaranSppList(Context context, ArrayList<BayarSpp> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;

        globalMessage = new GlobalMessage();
        globalProcess = new GlobalProcess(context);
        globalVariable = new GlobalVariable();
        sessionManager = new SessionManager(context);

        statusActivity = sessionManager.getStatusActivity();
    }

    @NonNull
    @Override
    public AdapterDataPembayaranSppList.DataPembayaranSppListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_data_pembayaran_list, parent, false);
        return new DataPembayaranSppListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataPembayaranSppList.DataPembayaranSppListViewHolder holder, int position) {
        String nama_pengajar = (position + 1) + "." + " Nama Wali Murid : " + dataModelArrayList.get(position).getNama_wali_murid();
        String waktu = "Waktu : " + dataModelArrayList.get(position).getWaktu();
        String total_pertemuan = "Total Pertemuan : " + dataModelArrayList.get(position).getTotal_pertemuan();
        String total_harga_spp = "Total Harga : " + dataModelArrayList.get(position).getTotal_harga_spp();

        holder.tvNama.setText(nama_pengajar);
        holder.tvWaktu.setText(waktu);
        holder.tvTotalPertemuan.setText(total_pertemuan);
        holder.tvTotalHarga.setText(total_harga_spp);
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public static class DataPembayaranSppListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView tvNama, tvWaktu, tvTotalPertemuan, tvTotalHarga;

        public DataPembayaranSppListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tv_nama);
            tvWaktu = itemView.findViewById(R.id.tv_waktu);
            tvTotalPertemuan = itemView.findViewById(R.id.tv_total_pertemuan);
            tvTotalHarga = itemView.findViewById(R.id.tv_total_harga);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getAdapterPosition());
        }
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        AdapterDataPembayaranSppList.clickListener = clickListener;
    }
}
