package tbc.uncagedmist.demo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import tbc.uncagedmist.demo.Common.Common;
import tbc.uncagedmist.demo.Interface.ItemSelectListener;
import tbc.uncagedmist.demo.Model.Product;
import tbc.uncagedmist.demo.R;
import tbc.uncagedmist.demo.ResultActivity;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    Context context;
    ArrayList<Product> products;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_product, parent,false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Picasso.get()
                .load(products.get(position).getProductImage())
                .into(holder.productImage);

        holder.productName.setText(products.get(position).getProductName());

        holder.productCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ResultActivity.class);
                Common.CurrentProductUrl = products.get(position).getProductImage();
                Common.CurrentProductName = products.get(position).getProductName();
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CircleImageView productImage;
        TextView productName;
        CardView productCard;

        ItemSelectListener itemSelectListener;

        public void setItemSelectListener(ItemSelectListener itemSelectListener) {
            this.itemSelectListener = itemSelectListener;
        }

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.product_image);
            productName =  itemView.findViewById(R.id.product_name);

            productCard = itemView.findViewById(R.id.card_products);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemSelectListener.onItemSelected(view,getAdapterPosition());
        }
    }
}
