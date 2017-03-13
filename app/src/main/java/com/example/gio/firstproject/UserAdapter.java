package com.example.gio.firstproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gio on 3/10/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ArrayList<User> mUser;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public UserAdapter(Context context, ArrayList<User> datas) {
        mContext = context;
        mUser = datas;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate view from information.xml
        View itemView = mLayoutInflater.inflate(R.layout.information, parent, false);
        return new UserViewHolder(itemView);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgAvatar;
        private TextView tvName, tvCompany, tvMajor;
        private ImageButton imgBtnIsFavourite;

        public UserViewHolder(final View itemView) {
            super(itemView);
            imgAvatar = (ImageView) itemView.findViewById(R.id.imgAvatar);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvCompany = (TextView) itemView.findViewById(R.id.tvCompany);
            tvMajor = (TextView) itemView.findViewById(R.id.tvMajor);
            imgBtnIsFavourite = (ImageButton) itemView.findViewById(R.id.imgBtnFavourite);

            //set onClick Favourite button
            imgBtnIsFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User user = mUser.get(getAdapterPosition());
                    if (user.getIsFavourite() == 0) {
                        imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_staroff);
//                        user.setIsFavourite(1);
                        mUser.set(getAdapterPosition(), new User(user.getId(), user.getName(), user.getCompany(), user.getMajor(), user.getAbout(), 1));
                        Log.d(TAG, "click fvr "+user.getId()+ user.getName()+ user.getCompany()+ user.getMajor()+ user.getAbout()+ user.getIsFavourite());
                    } else {
                        imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_staron);
//                        user.setIsFavourite(0);
                        mUser.set(getAdapterPosition(), new User(user.getId(), user.getName(), user.getCompany(), user.getMajor(), user.getAbout(), 0));
                    }
                }
            });

            //set onClick Item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DetailItem.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("user_item", mUser.get(getAdapterPosition()));
                    intent.putExtra("mUser", bundle);
//                    User user = mUser.get(getAdapterPosition());
//                    Toast.makeText(mContext, user.getName(), Toast.LENGTH_SHORT).show();
                    mContext.startActivity(intent);

                }
            });

        }
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        //get User from mUser via position
        User user = mUser.get(position);

        //bind data to viewholder
        if (user.getId()%2==0) {
            holder.imgAvatar.setImageResource(R.drawable.ic_setting);
        }
        holder.tvName.setText(user.getName());
        holder.tvCompany.setText(user.getCompany());
        if (user.getIsFavourite() == 0) {
            holder.imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_staroff);
        } else {
            holder.imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_staron);
        }
        holder.tvMajor.setText(user.getMajor());
    }

    @Override
    public int getItemCount() {
        return mUser.size();
    }
}


