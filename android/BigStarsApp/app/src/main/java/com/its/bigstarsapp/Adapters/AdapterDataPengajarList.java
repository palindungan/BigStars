package com.its.bigstarsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.its.bigstarsapp.Activities.Data.Pengajar.List.DataPengajarListActivity;
import com.its.bigstarsapp.Activities.Data.Pengajar.List.view.IDataPengajarListView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.Pengajar;
import com.its.bigstarsapp.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterDataPengajarList extends RecyclerView.Adapter<AdapterDataPengajarList.DataPengajarListViewHolder> {

    Context context;
    ArrayList<Pengajar> dataModelArrayList;

    private static ClickListener clickListener;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    String statusActivity;

    public AdapterDataPengajarList(Context context, ArrayList<Pengajar> dataModelArrayList) {
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
    public AdapterDataPengajarList.DataPengajarListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_data_pengajar_list, parent, false);
        return new DataPengajarListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataPengajarList.DataPengajarListViewHolder holder, int position) {
        String kode = dataModelArrayList.get(position).getId_pengajar();
        String nama = dataModelArrayList.get(position).getNama();

        if (statusActivity.equals("home->view->editPengajar")) {
            holder.ivDelete.setVisibility(View.VISIBLE);
        }

        String setNama = "Pengajar : " + nama;
        String setUsername = "Username : " + dataModelArrayList.get(position).getUsername();
        String setNoHp = "No : " + dataModelArrayList.get(position).getNo_hp();

        String setNumber = String.valueOf(position + 1);

        holder.tvNama.setText(setNama);
        holder.tvUsername.setText(setUsername);
        holder.tvNoHP.setText(setNoHp);
        holder.tvNumber.setText(setNumber);
        holder.ivDelete.setOnClickListener(v -> {
            IDataPengajarListView dataPengajarListView = (DataPengajarListActivity) context;
            dataPengajarListView.showDialogDelete(
                    "" + kode,
                    "" + nama);
        });

        String alamatFoto = globalVariable.getUrlUpload() + "image/pengajar/" + dataModelArrayList.get(position).getFoto() + ".jpg";
        Picasso.get().load(alamatFoto).resize(300, 600).centerInside().placeholder(R.drawable.ic_default_account_circle).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).into(holder.ivFoto);
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public static class DataPengajarListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView tvNama, tvUsername, tvNoHP, tvNumber;
        protected ImageView ivFoto, ivDelete;

        public DataPengajarListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tv_nama);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvNoHP = itemView.findViewById(R.id.tv_no_hp);
            tvNumber = itemView.findViewById(R.id.tv_number);
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
        AdapterDataPengajarList.clickListener = clickListener;
    }
}
