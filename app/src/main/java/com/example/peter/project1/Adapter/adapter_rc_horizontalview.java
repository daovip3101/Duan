package com.example.peter.project1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.peter.project1.ChiTietActivity;
import com.example.peter.project1.Model.SanPham;
import com.example.peter.project1.R;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by daovip on 4/3/2018.
 */

public class adapter_rc_horizontalview extends RecyclerView.Adapter<adapter_rc_horizontalview.View10> {
    ArrayList<SanPham> arrayList;
    Context c;
    public adapter_rc_horizontalview(ArrayList<SanPham> arrayList,Context c) {
        this.arrayList = arrayList;
        this.c=c;
    }

    @Override
    public View10 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view1o_horizontalview,parent,false);
        return new View10(view);
    }

    @Override
    public void onBindViewHolder(View10 holder, final int position) {
        holder.view_image.setBackgroundResource(arrayList.get(position).getHinh());
        holder.view_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c,ChiTietActivity.class);
                i.putExtra("SanPham",arrayList.get(position));
                i.putExtra("key",1);
                startActivity(c,i,null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class View10 extends RecyclerView.ViewHolder{
        View view_image;
        public View10(View itemView) {
            super(itemView);
            view_image=itemView.findViewById(R.id.view_horizontal);
        }
    }
}
