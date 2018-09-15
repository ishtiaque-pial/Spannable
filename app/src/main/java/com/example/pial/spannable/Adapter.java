package com.example.pial.spannable;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.AdapterHolder> {

    ArrayList<Model> modelArrayList = new ArrayList<>();
    Context context;

    public Adapter(ArrayList<Model> modelArrayList, Context context) {
        this.modelArrayList = modelArrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout,parent,false);
        AdapterHolder adapterHolder = new AdapterHolder(view);
        return adapterHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterHolder holder, int position) {
        /*Spannable span = Spannable.Factory.getInstance().newSpannable(modelArrayList.get(position).getName());
        span.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View v) {
                Log.d("main", "link clicked");
                Toast.makeText(context, "link 1 clicked", Toast.LENGTH_SHORT).show();
            } }, 0, modelArrayList.get(position).getName().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.name.setText(span);
        holder.name.setMovementMethod(LinkMovementMethod.getInstance());
        Spannable span1 = Spannable.Factory.getInstance().newSpannable(modelArrayList.get(position).getRestaurent());
        span1.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View v) {
                Log.d("main", "link clicked");
                Toast.makeText(context, "link 2 clicked", Toast.LENGTH_SHORT).show();
            } }, 0, modelArrayList.get(position).getRestaurent().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.restaurant.setText(span1);
        holder.restaurant.setMovementMethod(LinkMovementMethod.getInstance());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "item click", Toast.LENGTH_SHORT).show();
            }
        });*/



        SpannableString ss = new SpannableString(modelArrayList.get(position).getName() +" reviewed "+modelArrayList.get(position).getRestaurent());
        ClickableSpan firstwordClick = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Log.d("main", "link clicked");
                holder.flag=false;
                Toast.makeText(context, "link 1 clicked", Toast.LENGTH_SHORT).show();
            }
        };
        ClickableSpan secondwordClick = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Log.d("main", "link clicked");
                holder.flag=false;
                Toast.makeText(context, "link 2 clicked", Toast.LENGTH_SHORT).show();
            }
        };
        ss.setSpan(firstwordClick,0, modelArrayList.get(position).getName().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(secondwordClick,modelArrayList.get(position).getName().length()+10, modelArrayList.get(position).getName().length()+10+modelArrayList.get(position).getRestaurent().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.name.setLinksClickable(true);
        holder.name.setMovementMethod(LinkMovementMethod.getInstance());
        holder.name.setText(ss, TextView.BufferType.SPANNABLE);

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.flag) {
                    commonTask();
                }
                holder.flag= true;
            }
        });

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commonTask();
            }
        });

    }

    private void commonTask() {
        Toast.makeText(context, "item click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {

        TextView name, restaurant;
        LinearLayout item;
        boolean flag= true;
        public AdapterHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.one);
            restaurant = itemView.findViewById(R.id.two);
            item = itemView.findViewById(R.id.item);
        }
    }
}
