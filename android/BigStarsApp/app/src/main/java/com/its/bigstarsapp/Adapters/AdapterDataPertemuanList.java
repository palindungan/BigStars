package com.its.bigstarsapp.Adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterDataPertemuanList extends RecyclerView.Adapter<AdapterDataPertemuanList.AdapterDataPertemuanListViewHolder> {
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