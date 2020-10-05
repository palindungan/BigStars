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
import com.its.bigstarsapp.Models.Pertemuan;
import com.its.bigstarsapp.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterDataPertemuanList extends RecyclerView.Adapter<AdapterDataPertemuanList.AdapterDataPertemuanListViewHolder> {
    Context context;
    ArrayList<Pertemuan> dataModelArrayList;

    private static ClickListener clickListener;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    String statusActivity;

    public AdapterDataPertemuanList(Context context, ArrayList<Pertemuan> dataModelArrayList) {
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
    public AdapterDataPertemuanList.AdapterDataPertemuanListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_data_pertemuan, parent, false);
        return new AdapterDataPertemuanListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataPertemuanListViewHolder holder, int position) {

        String id_pertemuan = dataModelArrayList.get(position).getId_pertemuan();
        String hari_pertemuan = dataModelArrayList.get(position).getHari_pertemuan();
        String waktu_mulai = dataModelArrayList.get(position).getWaktu_mulai();
        String waktu_berakhir = dataModelArrayList.get(position).getWaktu_berakhir();
        String lokasi_mulai_la = dataModelArrayList.get(position).getLokasi_mulai_la();
        String lokasi_mulai_lo = dataModelArrayList.get(position).getLokasi_mulai_lo();
        String lokasi_berakhir_la = dataModelArrayList.get(position).getLokasi_berakhir_la();
        String lokasi_berakhir_lo = dataModelArrayList.get(position).getLokasi_berakhir_lo();
        String deskripsi = dataModelArrayList.get(position).getDeskripsi();
        String harga_fee = dataModelArrayList.get(position).getHarga_fee();
        String harga_spp = dataModelArrayList.get(position).getHarga_spp();
        String status_fee = dataModelArrayList.get(position).getStatus_fee();
        String status_spp = dataModelArrayList.get(position).getStatus_spp();
        String status_konfirmasi = dataModelArrayList.get(position).getStatus_konfirmasi();
        String status_pertemuan = dataModelArrayList.get(position).getStatus_pertemuan();
        String nama_pengajar = dataModelArrayList.get(position).getNama_pengajar();
        String id_kelas_pertemuan = dataModelArrayList.get(position).getId_kelas_pertemuan();
        String hari_kelas_pertemuan = dataModelArrayList.get(position).getHari_kelas_pertemuan();
        String jam_mulai = dataModelArrayList.get(position).getJam_mulai();
        String jam_berakhir = dataModelArrayList.get(position).getJam_berakhir();
        String id_mata_pelajaran = dataModelArrayList.get(position).getId_mata_pelajaran();
        String nama_mata_pelajaran = dataModelArrayList.get(position).getNama_mata_pelajaran();

        String setJadwalKelasPertemuan = "(" + hari_kelas_pertemuan + ", " + jam_mulai + " - " + jam_berakhir + ")";
        String setWaktuMulai = "Dimulai : " + hari_pertemuan + ", " + waktu_mulai;
        String setWaktuBerakhir = "Berakhir : " + hari_pertemuan + ", " + waktu_berakhir;
        String setStatusPertemuan = "Status Pertemuan : " + status_pertemuan;
        String setStatusFee = "FEE : " + status_fee;
        String setStatusSpp = "SPP : " + status_spp;
        String setStatusKonfirmasi = "Konfirmasi : " + status_konfirmasi;

        holder.tvNamaMataPelajaran.setText(nama_mata_pelajaran);
        holder.tvJadwalKelasPertemuan.setText(setJadwalKelasPertemuan);
        holder.tvWaktuMulai.setText(setWaktuMulai);
        holder.tvWaktuBerakhir.setText(setWaktuBerakhir);
        holder.tvStatusPertemuan.setText(setStatusPertemuan);
        holder.tvStatusFee.setText(setStatusFee);
        holder.tvStatusSpp.setText(setStatusSpp);
        holder.tvStatusKonfirmasi.setText(setStatusKonfirmasi);

        if (waktu_mulai.equals(waktu_berakhir)) {
            holder.tvWaktuBerakhir.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public static class AdapterDataPertemuanListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView tvNamaMataPelajaran, tvJadwalKelasPertemuan, tvWaktuMulai,
                tvWaktuBerakhir, tvStatusPertemuan, tvStatusFee, tvStatusSpp, tvStatusKonfirmasi;

        public AdapterDataPertemuanListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaMataPelajaran = itemView.findViewById(R.id.tv_nama_mata_pelajaran);
            tvJadwalKelasPertemuan = itemView.findViewById(R.id.tv_jadwal_kelas_pertemuan);
            tvWaktuMulai = itemView.findViewById(R.id.tv_waktu_mulai);
            tvWaktuBerakhir = itemView.findViewById(R.id.tv_waktu_berakhir);
            tvStatusPertemuan = itemView.findViewById(R.id.tv_status_pertemuan);
            tvStatusFee = itemView.findViewById(R.id.tv_status_fee);
            tvStatusSpp = itemView.findViewById(R.id.tv_status_spp);
            tvStatusKonfirmasi = itemView.findViewById(R.id.tv_status_konfirmasi);

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
        AdapterDataPertemuanList.clickListener = clickListener;
    }
}