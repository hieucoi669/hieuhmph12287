package com.edu.fpoly.bookmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.fpoly.bookmanager.R;
import com.edu.fpoly.bookmanager.dao.SachDAO;
import com.edu.fpoly.bookmanager.model.Sach;

import java.util.List;

public class SachAdapter extends BaseAdapter {
    private Context context;
    private List<Sach> listSach;
    private LayoutInflater inflater;
    private SachDAO sachDAO;

    public SachAdapter(Context context, List<Sach> listSach) {
        this.context = context;
        this.listSach = listSach;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sachDAO = new SachDAO(context);
    }

    @Override
    public int getCount() {
        return listSach.size();
    }

    @Override
    public Object getItem(int position) {
        return listSach.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_sach,null);
            holder.tvMaSach = (TextView)convertView.findViewById(R.id.tvMaSach);
            holder.tvTenSach = (TextView)convertView.findViewById(R.id.tvTenSach);
            holder.imgDelete = (ImageView)convertView.findViewById(R.id.imgDeleteSach);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Sach sach = listSach.get(position); //xoa trong list, nhung khong xoa trong database
                    listSach.remove(sach);
                    notifyDataSetChanged();
                    sachDAO.deleteSach(sach.getMasach()); // xoa trong database
                }
            });
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        Sach sach = listSach.get(position);
        holder.tvMaSach.setText(sach.getMasach());
        holder.tvTenSach.setText(sach.getTensach());

        return convertView;
    }

    public static class ViewHolder
    {
        TextView tvMaSach;
        TextView tvTenSach;
        ImageView imgDelete;
    }
    public void changeDataset(List<Sach> list)
    {
        this.listSach = list;
        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
