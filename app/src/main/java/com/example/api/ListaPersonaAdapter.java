package com.example.api;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ListaPersonaAdapter extends RecyclerView.Adapter<ListaPersonaAdapter.ViewHolder> {
    private List<Persona> mData;
    private List<Persona> originalItems;
    private LayoutInflater mInflater;
    private Context context;
    final ListaPersonaAdapter.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(Persona item);
    }


    public ListaPersonaAdapter(List<Persona> itemList, Context context, ListaPersonaAdapter.OnItemClickListener listener){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(itemList);
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void filter(String strSeatch){
        if(strSeatch.length() == 0){
            mData.clear();
            mData.addAll(originalItems);
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<Persona> collect = mData.stream()
                        .filter(i -> i.getTitle().toLowerCase(Locale.ROOT).contains(strSeatch))
                        .collect(Collectors.toList());
                mData.clear();
                mData.addAll(collect);
            }
            else{
                for (Persona p : originalItems) {
                    if (p.getTitle().toLowerCase().contains(strSeatch)){
                        mData.add(p);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public ListaPersonaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_element, null);
        return new ListaPersonaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListaPersonaAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
        Glide.with(context).load("https://thispersondoesnotexist.com/image")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.iconImage);
    }

    public void setItems(List<Persona> items){
        mData = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView userId, id, title, body;

        ViewHolder(View itemView){
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            //userId = itemView.findViewById(R.id.userId);
            //id = itemView.findViewById(R.id.id);
            title= itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);

        }
        void bindData(@NonNull final Persona item){
            //userId.setText(item.getUserId());
            //id.setText(item.getId());
            title.setText(item.getTitle());
            body.setText(item.getBody());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
