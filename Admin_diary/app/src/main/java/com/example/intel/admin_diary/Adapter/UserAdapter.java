package com.example.intel.admin_diary.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.intel.admin_diary.Model.User;
import com.example.intel.admin_diary.R;
import com.example.intel.admin_diary.Rest.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    List<User> listUser;

    public UserAdapter(List<User> listUser) {
        this.listUser = listUser;
    }

    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user, parent, false);
        UserViewHolder mHolder = new UserViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(UserAdapter.UserViewHolder holder, final int position) {

        holder.tvIdLogin.setText(listUser.get(position).getIdLogin());
        holder.tvusername.setText(listUser.get(position).getUsername());
        holder.tvpassword.setText(listUser.get(position).getPassword());
        holder.tvnama.setText(listUser.get(position).getNama());
        if (listUser.get(position).getPhotoUrl() != null ){
            Picasso.with(holder.itemView.getContext()).load(ApiClient.BASE_URL+listUser.get(position).getPhotoUrl())
                    .into(holder.mPhotoURL);
//            Glide.with(holder.itemView.getContext()).load(listPembeli.get
//                    (position).getPhotoUrl())
//                    .into(holder.mPhotoURL);
        } else {
          Picasso.with(holder.itemView.getContext()).load(R.drawable.default_user).into(holder
 .mPhotoURL);
//            Glide.with(holder.itemView.getContext()).load(R.drawable.default_user).into(holder
//                    .mPhotoURL);
        }
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView mPhotoURL;
        TextView tvIdLogin, tvusername, tvpassword, tvnama;

        public UserViewHolder(View itemView) {
            super(itemView);
            mPhotoURL = (ImageView) itemView.findViewById(R.id.imgPembeli);
            tvIdLogin = (TextView) itemView.findViewById(R.id.tvIdLogincontent);
            tvusername = (TextView) itemView.findViewById(R.id.tvusernameContent);
            tvpassword = (TextView) itemView.findViewById(R.id.tvpasswordContent);
            tvnama = (TextView) itemView.findViewById(R.id.tvNamacontent);
        }
    }


}
