package com.example.gio.firstproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Gio on 3/10/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> mUser;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public UserAdapter(Context context, List<User> datas) {
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

        public UserViewHolder(View itemView) {
            super(itemView);
            imgAvatar = (ImageView) itemView.findViewById(R.id.imgAvatar);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvCompany = (TextView) itemView.findViewById(R.id.tvCompany);
            tvMajor = (TextView) itemView.findViewById(R.id.tvMajor);
        }
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        //get User from mUser via position
        User user = mUser.get(position);

        //bind data to viewholder
        holder.tvName.setText(user.getName());
        holder.tvCompany.setText(user.getCompany());
        holder.tvMajor.setText(user.getMajor());
    }

    @Override
    public int getItemCount() {
        return mUser.size();
    }


}
