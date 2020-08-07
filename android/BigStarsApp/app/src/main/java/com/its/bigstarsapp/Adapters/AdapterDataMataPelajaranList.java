package com.its.bigstarsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.its.bigstarsapp.Activities.Data.MataPelajaran.List.DataMataPelajaranListActivity;
import com.its.bigstarsapp.Activities.Data.MataPelajaran.List.view.IDataMataPelajaranListView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.MataPelajaran;
import com.its.bigstarsapp.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterDataMataPelajaranList extends RecyclerView.Adapter<AdapterDataMataPelajaranList.DataMataPelajaranListViewHolder> {
    Context context;
    ArrayList<MataPelajaran> dataModelArrayList;

    private static ClickListener clickListener;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    String statusActivity;

    public AdapterDataMataPelajaranList(Context context, ArrayList<MataPelajaran> dataModelArrayList) {
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
    public AdapterDataMataPelajaranList.DataMataPelajaranListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_data_mata_pelajaran_list, parent, false);
        return new DataMataPelajaranListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataMataPelajaranList.DataMataPelajaranListViewHolder holder, int position) {
        String kode = dataModelArrayList.get(position).getId_mata_pelajaran();
        String nama = dataModelArrayList.get(position).getNama();

        holder.txtNama.setText(nama);

        if (statusActivity.equals("home->view->editMataPelajaran")) {
            holder.ivDelete.setVisibility(View.VISIBLE);
        }

        holder.ivDelete.setOnClickListener(v -> {
            IDataMataPelajaranListView dataMataPelajaranListView = (DataMataPelajaranListActivity) context;
            dataMataPelajaranListView.showDialogDelete(
                    "" + kode,
                    "" + nama);
        });
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public static class DataMataPelajaranListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView txtNama;
        protected ImageView ivDelete;

        public DataMataPelajaranListViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNama = itemView.findViewById(R.id.txt_nama);
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
        AdapterDataMataPelajaranList.clickListener = clickListener;
    }
}
