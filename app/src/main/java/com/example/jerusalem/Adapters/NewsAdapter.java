package com.example.jerusalem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.jerusalem.Models.News;
import com.example.jerusalem.R;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    ArrayList<News> listNews;
    Context context;

    public NewsAdapter(ArrayList<News> listNews, Context context) {
        this.listNews = listNews;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = listNews.get(position);
        holder.title.setText(news.getTitle());
        holder.date.setText(news.getDate());
        holder.description.setText(news.getDescription());
        Glide.with(context).
                load(news.getImage_URL()).
                apply(new RequestOptions().override(240,160))
                .into(holder.image_URL);
       // holder.URL.setText(news.getURL());

    }

    @Override
    public int getItemCount() {

        return listNews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title , date , description , URL;
        ImageView image_URL;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            description = itemView.findViewById(R.id.description);
            title =  itemView.findViewById(R.id.title);
            date =itemView.findViewById(R.id.date);
          //  URL = itemView.findViewById(R.id.URL);
            image_URL = itemView.findViewById(R.id.imageURL);

        }


    }
}
