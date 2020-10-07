package com.edu.fpoly.bookmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.fpoly.bookmanager.R;
import com.edu.fpoly.bookmanager.dao.UserDAO;
import com.edu.fpoly.bookmanager.model.User;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    private Context context;
    private List<User> listUser;
    private LayoutInflater inflater;
    private UserDAO userDAO;

    public UserAdapter(Context context, List<User> listUser) {
        this.context = context;
        this.listUser = listUser;
        this.inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        userDAO = new UserDAO(context);
    }


    @Override
    public int getCount() {
        return listUser.size();
    }

    @Override
    public Object getItem(int position) {
        return listUser.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    //thiet lap view
    //lay du lieu
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null)
        {
            //tao view
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_nguoi_dung,null);
            holder.txtName = (TextView)convertView.findViewById(R.id.tvName);
            holder.txtPhone = (TextView)convertView.findViewById(R.id.tvPhone);
            holder.imgDelete = (ImageView)convertView.findViewById(R.id.ivDelete);

            holder.img = (ImageView)convertView.findViewById(R.id.ivIcon);
            //tao template tu view
            convertView.setTag(holder);
        }
        else //lay view da ton tai
        {
            holder = (ViewHolder)convertView.getTag();
        }
        //lay du lieu
        User u = (User)listUser.get(position);
        holder.txtName.setText(u.getHoten());
        holder.txtPhone.setText(u.getPhone());
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User u = listUser.get(position);
                listUser.remove(u);//xoa trong list, but doen't remove it in database
                notifyDataSetChanged();//update
                userDAO.deleteNguoiDung(u.getUsername());//delete the NguoiDung in database

            }
        });

        return convertView;
    }
    public static class ViewHolder {
        ImageView img;
        TextView txtName;
        TextView txtPhone;
        ImageView imgDelete;
    }
    public void changeDataset(List<User> list)
    {
        this.listUser = list;
        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

}