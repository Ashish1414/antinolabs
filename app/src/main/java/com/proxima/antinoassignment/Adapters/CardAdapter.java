package com.proxima.antinoassignment.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.proxima.antinoassignment.R;
import com.proxima.antinoassignment.utility.CardObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    Context context;
    ArrayList<CardObject> cardObjects;

    public CardAdapter(Context context, ArrayList<CardObject> cardObjects) {
        this.context = context;
        this.cardObjects = cardObjects;
    }
    @NonNull
    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardAdapter.ViewHolder holder, final int position) {
        holder.cardName.setText(cardObjects.get(position).getName());
        holder.cardLocation.setText(cardObjects.get(position).getLocation());
        holder.cardAge.setText(cardObjects.get(position).getAge());
        Picasso.with(context).load(cardObjects.get(position).getImageURL()).into(holder.cardImage);
        if (holder.cardImage.getDrawable()==null)
        {
            holder.cardImage.setImageResource(R.drawable.image);
        }
    }

    @Override
    public int getItemCount() {
        return cardObjects.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView cardName , cardAge , cardLocation;
        CircleImageView cardImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardAge = itemView.findViewById(R.id.cardAge);
            cardImage = itemView.findViewById(R.id.cardImage);
            cardName = itemView.findViewById(R.id.cardName);
            cardLocation = itemView.findViewById(R.id.cardLocation);
        }
    }
}
