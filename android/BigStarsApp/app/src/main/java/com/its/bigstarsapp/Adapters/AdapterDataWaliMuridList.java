package com.its.bigstarsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.its.bigstarsapp.Activities.Data.WaliMurid.List.DataWaliMuridListActivity;
import com.its.bigstarsapp.Activities.Data.WaliMurid.List.view.IDataWaliMuridListView;
import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.WaliMurid;
import com.its.bigstarsapp.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterDataWaliMuridList extends RecyclerView.Adapter<AdapterDataWaliMuridList.DataWaliMuridListViewHolder> {
    Context context;
    ArrayList<WaliMurid> dataModelArrayList;

    private static ClickListener clickListener;

    GlobalMessage globalMessage;
    GlobalProcess globalProcess;
    GlobalVariable globalVariable;
    SessionManager sessionManager;

    String statusActivity;

    public AdapterDataWaliMuridList(Context context, ArrayList<WaliMurid> dataModelArrayList) {
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
    public AdapterDataWaliMuridList.DataWaliMuridListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_data_wali_murid_list, parent, false);
        return new DataWaliMuridListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataWaliMuridList.DataWaliMuridListViewHolder holder, int position) {
        String kode = dataModelArrayList.get(position).getId_wali_murid();
        String nama = dataModelArrayList.get(position).getNama();

        if (statusActivity.equals("home->view->editWaliMurid")) {
            holder.ivDelete.setVisibility(View.VISIBLE);
        }

        String setNama = "Wali Murid : " + dataModelArrayList.get(position).getNama();
        String setUsername = "Username : " + dataModelArrayList.get(position).getUsername();
        String setNoHp = "No. Hp : " + dataModelArrayList.get(position).getNo_hp();

        holder.tvNama.setText(setNama);
        holder.tvUsername.setText(setUsername);
        holder.tvNoHp.setText(setNoHp);
        holder.ivDelete.setOnClickListener(v -> {
            IDataWaliMuridListView dataWaliMuridListView = (DataWaliMuridListActivity) context;
            dataWaliMuridListView.showDialogDelete(
                    "" + kode,
                    "" + nama);
        });
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public static class DataWaliMuridListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView tvNama, tvUsername, tvNoHp;
        protected ImageView ivDelete;

        public DataWaliMuridListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tv_nama);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvNoHp = itemView.findViewById(R.id.tv_no_hp);
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
        AdapterDataWaliMuridList.clickListener = clickListener;
    }
}
