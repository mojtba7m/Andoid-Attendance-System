package com.example.mojtba.myapplication;

/**
 * Created by pratap.kesaboyina on 24-12-2014.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

public class SectionListDataAdapte extends RecyclerView.Adapter<SectionListDataAdapte.SingleItemRowHolder>
 {
private String idit;
    private ArrayList<ClientScanResult> itemsList;
    private Context mContext;
     int kl;
    public SectionListDataAdapte(Context context, ArrayList<ClientScanResult> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.clientlist, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        final ClientScanResult singleItem = itemsList.get(i);
holder.tvName.setText(singleItem.getIpAddr());
        holder.tvPrice.setText(singleItem.getHWAddr());




       /* Glide.with(mContext)
                .load(feedItem.getImageURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.bg)
                .into(feedListRowHolder.thumbView);*/
    }

    @Override
    public int getItemCount() {     return itemsList.size();
    }

     public void appendItems(List<ClientScanResult> items) {
         int count = getItemCount();
         itemsList.addAll(items);
         notifyItemRangeInserted(count, items.size());
     }


     public void clear() {
         itemsList.clear();
     }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tvOwner;
        protected TextView tvName;

        protected TextView tvPrice;
        protected TextView idddddd;
        protected ImageView itemImage;


        public SingleItemRowHolder(View view) {
            super(view);
             this.tvName =(TextView) view.findViewById(R.id.a);
            this.tvPrice =(TextView) view.findViewById(R.id.b);



            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Toast.makeText(mContext,idddddd.getText(),Toast.LENGTH_LONG).show();



                }
            });


        }

    }

}
