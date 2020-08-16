package com.its.bigstarsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.its.bigstarsapp.Activities.Data.KelasPertemuan.List.DataKelasPertemuanListActivity;
import com.its.bigstarsapp.Activities.Data.KelasPertemuan.List.view.IDataKelasPertemuanListView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.KelasPertemuan;
import com.its.bigstarsapp.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterDataKelasPertemuanList extends RecyclerView.Adapter<AdapterDataKelasPertemuanList.DataKelasPertemuanListViewHolder> {

    Context context;
    ArrayList<KelasPertemuan> dataModelArrayList;

    private static ClickListener clickListener;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    String statusActivity;

    public AdapterDataKelasPertemuanList(Context context, ArrayList<KelasPertemuan> dataModelArrayList) {
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
    public AdapterDataKelasPertemuanList.DataKelasPertemuanListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_data_kelas_pertemuan_list, parent, false);
        return new DataKelasPertemuanListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataKelasPertemuanList.DataKelasPertemuanListViewHolder holder, int position) {

        if (statusActivity.equals("listPengajar->view->editKelasPertemuan")) {
            holder.ivDelete.setVisibility(View.VISIBLE);
        }

        String kode = dataModelArrayList.get(position).getId_kelas_pertemuan();
        String nama = dataModelArrayList.get(position).getNama_pelajaran();
        String jumlah_murid = dataModelArrayList.get(position).getJumlah_murid();

        String setNamaPelajaran = dataModelArrayList.get(position).getNama_pelajaran() + " (" + jumlah_murid + " Murid)";
        String setHari = "Hari : " + dataModelArrayList.get(position).getHari();
        String setJam = "Jam : " + dataModelArrayList.get(position).getJam_mulai() + " - " + dataModelArrayList.get(position).getJam_berakhir();

        holder.tvNamaPelajaran.setText(setNamaPelajaran);
        holder.tvHari.setText(setHari);
        holder.tvJam.setText(setJam);
        holder.ivDelete.setOnClickListener(v -> {
            IDataKelasPertemuanListView dataKelasListView = (DataKelasPertemuanListActivity) context;
            dataKelasListView.showDialogDelete(
                    "" + kode,
                    "" + nama);
        });

    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public static class DataKelasPertemuanListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView tvNamaPelajaran, tvHari, tvJam;
        protected ImageView ivDelete;

        public DataKelasPertemuanListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaPelajaran = itemView.findViewById(R.id.tv_nama_pelajaran);
            tvHari = itemView.findViewById(R.id.tv_hari);
            tvJam = itemView.findViewById(R.id.tv_jam);
            ivDelete = itemView.findViewById(R.id.iv_delete);

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
        AdapterDataKelasPertemuanList.clickListener = clickListener;
    }

}
