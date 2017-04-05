package com.example.gio.firstproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gio.firstproject.R;
import com.example.gio.firstproject.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Copyright by Gio.
 * Created on 4/5/2017.
 */

public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.ViewHolder> {

    private List<Item> mItems;
    private Context mContext;
    private PostItemListener mItemListener;

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvIdOwner;
        TextView tvTypeOwner;
        TextView tvNameOwner;
        ImageView imgOwner;
        PostItemListener mItemListener;

        ViewHolder(View itemView, PostItemListener postItemListener) {
            super(itemView);
            tvIdOwner = (TextView) itemView.findViewById(R.id.tvIdOwner);
            tvTypeOwner = (TextView) itemView.findViewById(R.id.tvTypeOwner);
            tvNameOwner = (TextView) itemView.findViewById(R.id.tvNameOwner);
            imgOwner = (ImageView) itemView.findViewById(R.id.imgOwner);

            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Item item = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(item.getAnswerId());

            notifyDataSetChanged();
        }
    }

    public AnswersAdapter(Context context, List<Item> posts, PostItemListener itemListener) {
        mItems = posts;
        mItemListener = itemListener;
        mContext = context;
    }

    @Override
    public AnswersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.item_owner, parent, false);

        return new ViewHolder(postView, this.mItemListener);
    }

    @Override
    public void onBindViewHolder(AnswersAdapter.ViewHolder holder, int position) {

        Item item = mItems.get(position);
        TextView tvIdOwner = holder.tvIdOwner;
        TextView tvTypeOwner = holder.tvTypeOwner;
        TextView tvNameOwner = holder.tvNameOwner;
        tvIdOwner.setText("Id: " + String.valueOf(item.getOwner().getUserId()));
        tvTypeOwner.setText("User Type: " + item.getOwner().getUserType());
        tvNameOwner.setText("Name: " + item.getOwner().getDisplayName());

        ImageView imgOwner = holder.imgOwner;
        Picasso.with(mContext).load(item.getOwner().getProfileImage()).into(imgOwner);

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateAnswers(List<Item> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    private Item getItem(int adapterPosition) {
        return mItems.get(adapterPosition);
    }

    public interface PostItemListener {
        void onPostClick(long id);
    }
}
