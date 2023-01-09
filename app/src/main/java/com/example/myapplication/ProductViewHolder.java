package com.example.myapplication;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView tag;
    public  TextView comment;
    public ImageView image;
    public  TextView price;
    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        findViews(itemView);
    }
    private void findViews(View view) {
        title=view.findViewById(R.id.title);
        tag=view.findViewById(R.id.tag);
        comment=view.findViewById(R.id.comment);
        price=view.findViewById(R.id.price);
        image=view.findViewById(R.id.product_image);
    }
    public void setItem(final product p) {
        title.setText(p.title);
        tag.setText(p.tag);
        switch (p.tag){
            case "رجال":
                tag.setBackgroundColor(Color.BLUE);
                break;
            case "نساء":
                tag.setBackgroundColor(Color.RED);
                break;
            case "اطفال":
                tag.setBackgroundColor(Color.parseColor("#FFD700"));
                break;
        }
        comment.setText(p.comment);
        price.setText(p.price);


    }
}
