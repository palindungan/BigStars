package com.its.bigstarsapp.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.its.bigstarsapp.Controllers.GlobalMessage;
import com.its.bigstarsapp.Controllers.GlobalProcess;
import com.its.bigstarsapp.Controllers.GlobalVariable;
import com.its.bigstarsapp.Controllers.SessionManager;
import com.its.bigstarsapp.Models.BayarSpp;

import java.util.ArrayList;

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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataPembayaranSppList.DataPembayaranSppListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public static class DataPembayaranSppListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public DataPembayaranSppListViewHolder(@NonNull View itemView) {
            super(itemView);
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
