package com.example.listevents.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.listevents.R;
import com.example.listevents.model.Event;
import com.example.listevents.viewmodel.EventViewModel;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder>{

    private EventViewModel eventViewModel;
    private OnFavoriteClickListener listener;
    private OnItemClickListener clickListener;
    private List<Event> events;
    private Context context;

    public EventAdapter (List<Event> events, Context context, OnFavoriteClickListener listener, OnItemClickListener clickListener){
        this.events = events;
        this.context = context;
        this.listener = listener;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_layout, parent, false);
        eventViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(EventViewModel.class);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ColorDrawable placeholder = new ColorDrawable(Color.GRAY);
        Event e = events.get(position);
        int drawableId = context.getResources().getIdentifier(
                    e.getImage(),
                    "drawable",
                    context.getPackageName()
        );

        Glide
                .with(context)
                .load(drawableId)
                .apply(new RequestOptions()
                        .centerCrop()
                        .fitCenter()
                        .placeholder(placeholder)
                )
                .into(holder.image);

        int icon = (e.getFavorite() == 1)
                ? R.drawable.star_yellow
                : R.drawable.star_white;

        // Listener to change Favorite Status...

        holder.favorite.setImageResource(icon);
        holder.favorite.setOnClickListener(view -> {
            listener.onFavoriteClick(position, e);
        });

        // Listener to see the details of Event...

        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onItemClick(e);
            }
        });

        holder.bind(e.getName(),e.getDate(),e.getPlace(),e.getFavorite());
    }

    public void setData(List<Event> data) {
        this.events = data;
        notifyDataSetChanged();
    }

    public void updateIconAtPosicion(int position, int value){
        if (position >= 0 && position < events.size()) {
            events.get(position).setFavorite(value);
            notifyItemChanged(position);
        }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public ImageView favorite;
        public TextView name;
        public TextView date;
        public TextView place;

        public ViewHolder(@NonNull View v) {
            super(v);
            name = v.findViewById(R.id.textViewNameEvent);
            date = v.findViewById(R.id.textViewDateEvent);
            place = v.findViewById(R.id.textViewUbicationEvent);
            image = v.findViewById(R.id.imageViewEvent);
            favorite = v.findViewById(R.id.imageViewFavorite);
        }

        public void bind(String name, String date, String place, int favorite) {
            this.name.setText(name);
            this.date.setText(date);
            this.place.setText(place);
            if (favorite == 1){
                this.favorite.setImageResource(R.drawable.star_yellow);
            } else {
                this.favorite.setImageResource(R.drawable.star_white);
            }
        }
    }

    public interface OnFavoriteClickListener {
        void onFavoriteClick(int position, Event event);
    }

    public interface OnItemClickListener {
        void onItemClick(Event item);
    }
}
