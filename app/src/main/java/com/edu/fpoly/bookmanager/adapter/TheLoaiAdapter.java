package com.edu.fpoly.bookmanager.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.fpoly.bookmanager.R;
import com.edu.fpoly.bookmanager.dao.TheLoaiDAO;
import com.edu.fpoly.bookmanager.model.TheLoai;

import java.util.List;

public class TheLoaiAdapter extends BaseAdapter {
    private Context context;
    private List<TheLoai> listTheLoai;
    private LayoutInflater inflater;
    private TheLoaiDAO theLoaiDAO;

    public TheLoaiAdapter(Context context, List<TheLoai> listTheLoai) {
        this.context = context;
        this.listTheLoai = listTheLoai;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        theLoaiDAO = new TheLoaiDAO(context);
    }

    @Override
    public int getCount() {
        return listTheLoai.size();
    }

    @Override
    public Object getItem(int position) {
        return listTheLoai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolderTL holder;
        if(convertView==null)
        {
            holder = new ViewHolderTL();
            convertView = inflater.inflate(R.layout.item_theloai,null);
            holder.matl = (TextView)convertView.findViewById(R.id.tvMaTheLoai);
            holder.tentl = (TextView)convertView.findViewById(R.id.tvTenTheLoai);
            holder.imgDelete = (ImageView)convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TheLoai tl = listTheLoai.get(position); //xoa trong list, nhung khong xoa trong database
                    listTheLoai.remove(tl);
                    notifyDataSetChanged();
                    theLoaiDAO.deleteTheLoai(tl.getMaTL()); // xoa trong database
                }
            });
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolderTL) convertView.getTag();
        }
        TheLoai tl = listTheLoai.get(position);

        Log.i("test2", tl.getMaTL() + " ... " + tl.getTenTL());
        holder.matl.setText(tl.getMaTL());
        holder.tentl.setText(tl.getTenTL());

        return convertView;
    }

    public static class ViewHolderTL
    {
        TextView matl;
        TextView tentl;
        ImageView imgDelete;
    }
    public void changeDataset(List<TheLoai> list)
    {
        this.listTheLoai = list;
        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
