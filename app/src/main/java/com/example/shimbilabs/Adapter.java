package com.example.shimbilabs;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Adapter  extends RecyclerView.Adapter<Adapter.ViewHolder>{

    public static final String EXTRA_MESSAGE = "Hello world";
    ArrayList<String> urls;
    String text;
    Context context;
    //constructor
    public Adapter(ArrayList<String> ImgUrl, Context context_)
    {
        Log.d("Adapter class " , ""+ImgUrl);
        this.urls = ImgUrl;
        this.context = context_;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image;
        private TextView textView;

        public ViewHolder(View v)
        {
            super(v);
            image =(ImageView)v.findViewById(R.id.img);
            textView = (TextView)v.findViewById(R.id.sample);
        }

        public ImageView getImage()
        {
            return this.image;
        }
        public TextView getTextView()
        {
            return this.textView;
        }
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.myimageview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        Glide.with(this.context)
                .load(urls.get(position))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.getImage());

        holder.textView.setText("Image no. - "+(position+1));
        holder.getImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Image is - ",""+position+" = "+urls.get(position));
                Toast.makeText(context,"Image number "+(position+1),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, images.class);
                String url = urls.get(position);
                intent.putExtra(EXTRA_MESSAGE, url);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return urls.size();
    }

}