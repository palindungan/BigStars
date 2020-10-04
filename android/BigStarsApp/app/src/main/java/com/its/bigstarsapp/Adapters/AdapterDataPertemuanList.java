package com.its.bigstarsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public static class AdapterDataPertemuanListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public AdapterDataPertemuanListViewHolder(@NonNull View itemView) {
            super(itemView);

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