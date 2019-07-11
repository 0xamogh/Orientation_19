package com.example.orientation19;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.EventViewHolder> {
    List<EventPOJO> events;


    public static class EventViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView title, description, time, venue;

        EventViewHolder(View itemView){
            super(itemView);
            cv = itemView.findViewById(R.id.cardView);
            title= itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.desc);
            venue= itemView.findViewById(R.id.venue);
            time= itemView.findViewById(R.id.time);
        }

    }

    public HomeAdapter(List<EventPOJO> events) {
        this.events = events;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item,viewGroup,false);
        EventViewHolder eventViewHolder= new EventViewHolder(v);
        return eventViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder eventViewHolder, int i) {
        eventViewHolder.title.setText(events.get(i).getEvent_title());
        eventViewHolder.description.setText(events.get(i).getDesc());
        eventViewHolder.time.setText(events.get(i).getTime());
        eventViewHolder.venue.setText(events.get(i).getVenue());

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}