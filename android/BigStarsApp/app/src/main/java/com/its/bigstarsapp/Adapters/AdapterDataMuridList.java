package com.its.bigstarsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.its.bigstarsapp.Activities.Data.KelasPertemuan.Edit.DataKelasPertemuanEditActivity;
import com.its.bigstarsapp.Activities.Data.KelasPertemuan.Edit.view.IDataKelasPertemuanEditView;
import com.its.bigstarsapp.Activities.Data.Murid.List.DataMuridListActivity;
import com.its.bigstarsapp.Activities.Data.Murid.List.view.IDataMuridListView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.Murid;
import com.its.bigstarsapp.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterDataMuridList extends RecyclerView.Adapter<AdapterDataMuridList.DataMuridListViewHolder> {
    Context context;
    ArrayList<Murid> dataModelArrayList;

    private static ClickListener clickListener;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    String statusActivity;

    public AdapterDataMuridList(Context context, ArrayList<Murid> dataModelArrayList) {
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
    public AdapterDataMuridList.DataMuridListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_data_murid_list, parent, false);
        return new DataMuridListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataMuridList.DataMuridListViewHolder holder, int position) {

        if (statusActivity.equals("home->view->editMurid")) {
            holder.ivDelete.setVisibility(View.VISIBLE);
        } else if (statusActivity.equals("listPengajar->view->editKelasPertemuan")) {
            String id_kelas_pertemuan_detail = dataModelArrayList.get(position).getId_kelas_pertemuan_detail();
            if (!id_kelas_pertemuan_detail.equals("kosong")) {
                holder.ivDelete.setVisibility(View.VISIBLE);
            }
        }

        String kode = dataModelArrayList.get(position).getId_murid();
        String nama = dataModelArrayList.get(position).getNama();

        String setNama = "Nama : " + nama;
        String setNamaWaliMurid = "Wali Murid : " + dataModelArrayList.get(position).getNama_wali_murid();
        String setAlamat = "Alamat : " + dataModelArrayList.get(position).getAlamat();

        holder.txtNama.setText(setNama);
        holder.txtNamaWaliMurid.setText(setNamaWaliMurid);
        holder.txtAlamat.setText(setAlamat);
        holder.ivDelete.setOnClickListener(v -> {
            if (statusActivity.equals("home->view->editMurid")) {
                IDataMuridListView dataMuridListView = (DataMuridListActivity) context;
                dataMuridListView.showDialogDelete(
                        "" + kode,
                        "" + nama);
            } else if (statusActivity.equals("listPengajar->view->editKelasPertemuan")) {
                String getId_kelas_pertemuan = dataModelArrayList.get(position).getId_kelas_pertemuan();
                IDataKelasPertemuanEditView dataKelasPertemuanEditView = (DataKelasPertemuanEditActivity) context;
                dataKelasPertemuanEditView.showDialogDeleteMurid(
                        "" + getId_kelas_pertemuan,
                        "" + kode,
                        "" + nama
                );
            }
        });

        String alamatFoto = globalVariable.getUrlUpload() + "image/murid/" + dataModelArrayList.get(position).getFoto() + ".jpg";
        Picasso.get().load(alamatFoto).resize(300, 600).centerInside().placeholder(R.drawable.ic_default_account_circle).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).into(holder.ivFoto);
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public static class DataMuridListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView txtNama, txtNamaWaliMurid, txtAlamat;
        protected ImageView ivFoto, ivDelete;

        public DataMuridListViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNama = itemView.findViewById(R.id.tv_nama);
            txtNamaWaliMurid = itemView.findViewById(R.id.tv_nama_wali_murid);
            txtAlamat = itemView.findViewById(R.id.tv_alamat);
            ivFoto = itemView.findViewById(R.id.iv_foto);
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
        AdapterDataMuridList.clickListener = clickListener;
    }
}
