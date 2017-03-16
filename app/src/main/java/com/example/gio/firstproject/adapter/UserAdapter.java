package com.example.gio.firstproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gio.firstproject.R;
import com.example.gio.firstproject.activities.MainActivity;
import com.example.gio.firstproject.model.User;

import java.util.ArrayList;

/**
 * Created by Gio on 3/10/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ArrayList<User> mUsers;
    Context mContext;
    private LayoutInflater mLayoutInflater;
    private final IsOnFavouriteListener mIsOnFavouriteListener;

    public UserAdapter(Context context, ArrayList<User> datas, IsOnFavouriteListener listener) {
        mContext = context;
        mUsers = datas;
        mLayoutInflater = LayoutInflater.from(context);
        mIsOnFavouriteListener = listener;
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
                    User user = mUsers.get(getAdapterPosition());
                    if (user.getIsFavourite()) {
                        imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_staroff);
                        user.setIsFavourite(false);
                        Log.d(TAG, "User set " + user.getIsFavourite());
                        //mUsers.set(getAdapterPosition(), new User(user.getId(), user.getName(), user.getCompany(), user.getMajor(), user.getAbout(), 1));
                    } else {
                        imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_stargold);
                        user.setIsFavourite(true);
                        //mUsers.set(getAdapterPosition(), new User(user.getId(), user.getName(), user.getCompany(), user.getMajor(), user.getAbout(), 0));
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
    public void onBindViewHolder(UserViewHolder holder, int position) {
        //get User from mUsers via position
        User user = mUsers.get(position);

        //bind data to viewholder
//        if (user.getId() % 2 == 0) {
//            holder.imgAvatar.setImageResource(R.drawable.ic_setting);
//        }
        holder.tvName.setText(user.getName());
        holder.tvCompany.setText(user.getCompany());
        if (user.getIsFavourite()) { //IsFavourite = true
            holder.imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_stargold);
//            Log.d(TAG, "Set Favourite = 1" + user.getIsFavourite());
        } else {
            holder.imgBtnIsFavourite.setBackgroundResource(R.drawable.ic_staroff);
//            Log.d(TAG, "Set Favourite = 0" + user.getIsFavourite());
        }
        holder.tvMajor.setText(user.getMajor());
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public interface IsOnFavouriteListener {
        void onFavouriteClick();
    }

    public interface MyOnClickListener {
        void onClick(int id);
    }

    MyOnClickListener mMyOnClickListener;

    public void setMyOnClickListener(MyOnClickListener mMyOnClickListener) {
        this.mMyOnClickListener = mMyOnClickListener;
    }
}


