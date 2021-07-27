package com.example.volley2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;

import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.ViewHolder> implements Filterable {
    List<MovieObject> moviesList;
    List<MovieObject> moviesListAll;

    Context context;

    public RecyAdapter(Context context, List MoviesList)
    {
        this.context = context;
        this.moviesList = MoviesList;
        this.moviesListAll = new ArrayList<>(moviesList);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(moviesList.get(position).getTitle());
        holder.rating.setText(moviesList.get(position).getRating());
        Glide.with(context).load(moviesList.get(position).getDescription()).into(holder.imageView);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),DetailsActivity.class);
                intent.putExtra("ov",moviesList.get(position).getUrl());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
            return moviesList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {

        //running on background thread
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<MovieObject> filtered= new ArrayList<>();
            if(constraint.toString().isEmpty()){
                filtered.addAll(moviesListAll);
            }else{
                for(MovieObject movie:moviesListAll){
                    if(movie.getTitle().toLowerCase().contains(constraint.toString().toLowerCase())){
                        filtered.add(movie);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filtered;
            return filterResults;

        }

        //running on ui thread
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            moviesList.clear();
            moviesList.addAll((Collection<? extends MovieObject>) results.values);
            notifyDataSetChanged();

        }
    };


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,rating;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.movie_title);
            rating = (TextView) itemView.findViewById(R.id.rating);
            imageView =  itemView.findViewById(R.id.coverImage);
        }
    }
}
