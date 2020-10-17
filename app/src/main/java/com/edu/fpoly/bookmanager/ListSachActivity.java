package com.edu.fpoly.bookmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.edu.fpoly.bookmanager.adapter.SachAdapter;
import com.edu.fpoly.bookmanager.dao.SachDAO;
import com.edu.fpoly.bookmanager.model.Sach;

import java.util.List;

public class ListSachActivity extends AppCompatActivity {

    List<Sach> listSach;
    ListView lvSach;
    SachDAO sachDAO;
    SachAdapter sachAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sach);

        lvSach = findViewById(R.id.lvSach);
        sachDAO = new SachDAO(this);
        listSach = sachDAO.getAllSach();
        sachAdapter = new SachAdapter(this,listSach);
        lvSach.setAdapter(sachAdapter);
        lvSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListSachActivity.this,DetailSachActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("maSach", listSach.get(position).getMasach());
                bundle.putString("tenSach", listSach.get(position).getTensach());
                bundle.putString("maTL", listSach.get(position).getMatheloai());
                bundle.putString("tacGia",listSach.get(position).getTacgia());
                bundle.putString("NXB",listSach.get(position).getNxb());
                bundle.putString("giaBia",listSach.get(position).getGiabia());
                bundle.putString("soLuong",listSach.get(position).getSoluong());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listSach = sachDAO.getAllSach();
        sachAdapter = new SachAdapter(this,listSach);
        lvSach.setAdapter(sachAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(ListSachActivity.this,SachActivity.class);
                startActivity(intent);
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }


}
