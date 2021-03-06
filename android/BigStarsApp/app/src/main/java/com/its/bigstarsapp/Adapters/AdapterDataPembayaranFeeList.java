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
import com.its.bigstarsapp.Models.BayarFee;
import com.its.bigstarsapp.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterDataPembayaranFeeList extends RecyclerView.Adapter<AdapterDataPembayaranFeeList.DataPembayaranFeeListViewHolder> {

    Context context;
    ArrayList<BayarFee> dataModelArrayList;

    private static ClickListener clickListener;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    String statusActivity;

    public AdapterDataPembayaranFeeList(Context context, ArrayList<BayarFee> dataModelArrayList) {
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
    public AdapterDataPembayaranFeeList.DataPembayaranFeeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_data_pembayaran_list, parent, false);
        return new DataPembayaranFeeListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataPembayaranFeeList.DataPembayaranFeeListViewHolder holder, int position) {
        String nama_pengajar = (position + 1) + "." + " Nama Pengajar : " + dataModelArrayList.get(position).getNama_pengajar();
        String waktu = "Waktu : " + dataModelArrayList.get(position).getWaktu();
        String total_pertemuan = "Total Pertemuan : " + dataModelArrayList.get(position).getTotal_pertemuan();
        String total_harga_fee = "Total Harga : " + dataModelArrayList.get(position).getTotal_harga_fee();

        holder.tvNama.setText(nama_pengajar);
        holder.tvWaktu.setText(waktu);
        holder.tvTotalPertemuan.setText(total_pertemuan);
        holder.tvTotalHarga.setText(total_harga_fee);
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public static class DataPembayaranFeeListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView tvNama, tvWaktu, tvTotalPertemuan, tvTotalHarga;

        public DataPembayaranFeeListViewHolder(@NonNull View itemView) {
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
        AdapterDataPembayaranFeeList.clickListener = clickListener;
    }
}
