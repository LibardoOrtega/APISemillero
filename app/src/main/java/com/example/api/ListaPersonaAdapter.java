package com.example.api;

import static com.example.api.View.MainActivity.bitMapImage;
import static com.example.api.utils.Utils.convertImageViewToBitmap;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.api.Interactors.Persona;

import java.util.ArrayList;
import java.util.List;

public class ListaPersonaAdapter extends RecyclerView.Adapter<ListaPersonaAdapter.ViewHolder> implements Filterable {
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
        this.originalItems = new ArrayList<>(itemList);
        //originalItems.addAll(itemList);
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public Filter getFilter(){
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Persona> filteredList = new ArrayList<>();

            if (constraint.toString().isEmpty()){
                filteredList.addAll(originalItems);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Persona item : originalItems){
                    if (item.getId().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                    if (item.getTitle().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mData.clear();
            mData.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    @Override
    public ListaPersonaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_element, null);
        return new ListaPersonaAdapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
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
            id = itemView.findViewById(R.id.id);
            title= itemView.findViewById(R.id.title);

        }
        @RequiresApi(api = Build.VERSION_CODES.M)
        void bindData(@NonNull final Persona item){
            iconImage.setImageIcon(item.getIconImage());
            id.setText(item.getId());
            title.setText(item.getTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                    bitMapImage = convertImageViewToBitmap(iconImage);
                }
            });
        }
    }
}
