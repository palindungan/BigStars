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

        String id_pertemuan = dataobj.getString("id_pertemuan");
        String hari_pertemuan = dataobj.getString("hari_pertemuan");
        String waktu_mulai = dataobj.getString("waktu_mulai");
        String waktu_berakhir = dataobj.getString("waktu_berakhir");
        String lokasi_mulai_la = dataobj.getString("lokasi_mulai_la");
        String lokasi_mulai_lo = dataobj.getString("lokasi_mulai_lo");
        String lokasi_berakhir_la = dataobj.getString("lokasi_berakhir_la");
        String lokasi_berakhir_lo = dataobj.getString("lokasi_berakhir_lo");
        String deskripsi = dataobj.getString("deskripsi");
        String harga_fee = dataobj.getString("harga_fee");
        String harga_spp = dataobj.getString("harga_spp");
        String status_fee = dataobj.getString("status_fee");
        String status_spp = dataobj.getString("status_spp");
        String status_konfirmasi = dataobj.getString("status_konfirmasi");
        String status_pertemuan = dataobj.getString("status_pertemuan");

        String nama_pengajar = dataobj.getString("nama_pengajar");

        String id_kelas_pertemuan = dataobj.getString("id_kelas_pertemuan");
        String hari_kelas_pertemuan = dataobj.getString("hari_kelas_pertemuan");
        String jam_mulai = dataobj.getString("jam_mulai");
        String jam_berakhir = dataobj.getString("jam_berakhir");

        String id_mata_pelajaran = dataobj.getString("id_mata_pelajaran");
        String nama_mata_pelajaran = dataobj.getString("nama_mata_pelajaran");

        holder.tvNamaMataPelajaran.setText("");
        holder.tvJadwalKelasPertemuan.setText("");
        holder.tvWaktuMulai.setText("");
        holder.tvWaktuBerakhir.setText("");
        holder.tvStatusPertemuan.setText("");
        holder.tvStatusFee.setText("");
        holder.tvStatusSpp.setText("");
        holder.tvStatusKonfirmasi.setText("");

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