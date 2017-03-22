package com.example.gio.firstproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.gio.firstproject.R;
import com.example.gio.firstproject.activities.MainActivity;
import com.example.gio.firstproject.model.Header;
import com.example.gio.firstproject.model.ItemUser;
import com.example.gio.firstproject.model.ListItem;

import java.util.ArrayList;

/**
 * Created by Gio on 3/10/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private final ArrayList<ListItem> mListItems;
    private final IsOnFavouriteListener mIsOnFavouriteListener;
    private MyOnClickListener mMyOnClickListener;

    public UserAdapter(Context context, ArrayList<ListItem> datas, IsOnFavouriteListener listener) {
        mListItems = datas;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        mIsOnFavouriteListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate view from item_userml
        if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_user, parent, false);
            return new HeaderViewHolder(v);
        } else if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
            return new VHUser(v);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListItem listItem = mListItems.get(position);
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder vHHeader = (HeaderViewHolder) holder;
            if (listItem instanceof Header) {
                Header header = (Header) listItem;
                vHHeader.mTvHeader.setText(header.getHeader());
            }
        } else if (holder instanceof VHUser) {
            if (listItem instanceof ItemUser) {
                ItemUser currentItem = (ItemUser) listItem;
                VHUser vhUser = (VHUser) holder;
                vhUser.tvName.setText(currentItem.getName());
                vhUser.tvCompany.setText(currentItem.getCompany());
                vhUser.tvMajor.setText(currentItem.getMajor());
                if (currentItem.getIsFavourite()) { //IsFavourite = true
                    vhUser.imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_stargold);
                } else {
                    vhUser.imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_staroff);
                }
            }
        }
    }

    class VHUser extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvCompany;
        private TextView tvMajor;
        private ImageButton imgBtnIsFavourite;

        public VHUser(final View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvCompany = (TextView) itemView.findViewById(R.id.tvCompany);
            tvMajor = (TextView) itemView.findViewById(R.id.tvMajor);
            imgBtnIsFavourite = (ImageButton) itemView.findViewById(R.id.imgBtnFavourite);

            //set onClick Favourite button
            imgBtnIsFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ItemUser user = (ItemUser) mListItems.get(getAdapterPosition());
                    if (user.getIsFavourite()) {
                        imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_staroff);
                        user.setIsFavourite(false);
                        Log.d(TAG, "ItemUser set " + user.getIsFavourite());
                    } else {
                        imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_stargold);
                        user.setIsFavourite(true);
                    }
                    mIsOnFavouriteListener.onFavouriteClick();
                    Log.d(TAG, "click Item_Detail1 " + user.getId() + user.getName() + user.getCompany() + user.getMajor() + user.getAbout() + user.getIsFavourite());
                }
            });

            //set onClick Item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMyOnClickListener.onClick(getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }

    public interface IsOnFavouriteListener {
        void onFavouriteClick();
    }

    public interface MyOnClickListener {
        void onClick(int id);
    }

    public void setMyOnClickListener(MyOnClickListener mMyOnClickListener) {
        this.mMyOnClickListener = mMyOnClickListener;
    }

    //set view type for recyclerView
    @Override
    public int getItemViewType(int position) {
        return mListItems.get(position).getType();
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvHeader;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            this.mTvHeader = (TextView) itemView.findViewById(R.id.tvHeader);
        }
    }

}


