package com.ma.menglong;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Long on 2018/3/22.
 */

public class CardImageAdapter extends RecyclerView.Adapter<CardImageAdapter.ViewHolder> {
    private static final String TAG = "FruitAdapter";

    private Context mContext;

    private List<ImageText> imageTextList;
    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView image;
        TextView text;
        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            image = (ImageView) view.findViewById(R.id.image);
            text = (TextView) view.findViewById(R.id.text);
        }
    }
    public CardImageAdapter(List<ImageText> list) {
        imageTextList = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.card_image_text_view, parent, false);
//        final ViewHolder holder = new ViewHolder(view);
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                ImageText imageText = imageTextList.get(position);
////                Intent intent = new Intent(mContext, FruitActivity.class);
////                intent.putExtra(FruitActivity.FRUIT_NAME, fruit.getName());
////                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.getImageId());
////                mContext.startActivity(intent);
//            }
//        });
        //return holder;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageText imageText = imageTextList.get(position);
        holder.text.setText(imageText.getText());
        Glide.with(mContext).load(imageText.getImageId()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return imageTextList.size();
    }

}
