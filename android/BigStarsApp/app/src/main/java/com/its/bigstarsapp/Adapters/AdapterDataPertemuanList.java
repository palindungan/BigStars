package com.its.bigstarsapp.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.its.bigstarsapp.Models.Pertemuan;

import java.util.ArrayList;

public class AdapterDataPertemuanList extends RecyclerView.Adapter<AdapterDataPertemuanList.AdapterDataPertemuanListViewHolder> {
    Context context;
    ArrayList<Pertemuan> dataModelArrayList;

    public AdapterDataPertemuanList(Context context, ArrayList<Pertemuan> dataModelArrayList) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
    }

    @NonNull
    @Override
    public AdapterDataPertemuanListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataPertemuanListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class AdapterDataPertemuanListViewHolder extends RecyclerView.ViewHolder {
        public AdapterDataPertemuanListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}