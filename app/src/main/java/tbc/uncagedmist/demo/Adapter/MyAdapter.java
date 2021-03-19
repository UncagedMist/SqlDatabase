package tbc.uncagedmist.demo.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tbc.uncagedmist.demo.Common.Common;
import tbc.uncagedmist.demo.Interface.ItemSelectListener;
import tbc.uncagedmist.demo.Model.State;
import tbc.uncagedmist.demo.ProductActivity;
import tbc.uncagedmist.demo.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<State> states;
    Context context;

    public MyAdapter(ArrayList<State> states, Context context) {
        this.states = states;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_layout, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Picasso.get()
                .load(states.get(position).getStateImage())
                .into(holder.stateImage);

        holder.stateName.setText(states.get(position).getStateName());

        holder.stateCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductActivity.class);
                Common.CurrentStateId = states.get(position).getStateId();
                Common.CurrentStateName = states.get(position).getStateName();
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView stateName;
        ImageView stateImage;
        CardView stateCard;

        ItemSelectListener itemSelectListener;

        public void setItemSelectListener(ItemSelectListener itemSelectListener) {
            this.itemSelectListener = itemSelectListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            stateName =  itemView.findViewById(R.id.state_name);
            stateImage = itemView.findViewById(R.id.state_image);
            stateCard = itemView.findViewById(R.id.card_states);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemSelectListener.onItemSelected(view,getAdapterPosition());
        }
    }
}
