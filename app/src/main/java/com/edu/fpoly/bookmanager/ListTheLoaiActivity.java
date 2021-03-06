package com.edu.fpoly.bookmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.edu.fpoly.bookmanager.adapter.TheLoaiAdapter;
import com.edu.fpoly.bookmanager.dao.TheLoaiDAO;
import com.edu.fpoly.bookmanager.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class ListTheLoaiActivity extends AppCompatActivity {

    public static List<TheLoai> listTL;
    ListView lvTheLoai;
    TheLoaiDAO theLoaiDAO;
    TheLoaiAdapter theLoaiAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("THỂ LOẠI");
        setContentView(R.layout.activity_list_the_loai);
        lvTheLoai = findViewById(R.id.lvTheLoai);
        registerForContextMenu(lvTheLoai);


        lvTheLoai = findViewById(R.id.lvTheLoai);
        theLoaiDAO = new TheLoaiDAO(ListTheLoaiActivity.this);
        listTL = theLoaiDAO.getAllTheLoai();

        theLoaiAdapter = new TheLoaiAdapter(this,listTL);
        lvTheLoai.setAdapter(theLoaiAdapter);
        lvTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListTheLoaiActivity.this,
                        TheLoaiDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("maTL", listTL.get(position).getMaTL());
                bundle.putString("tenTL", listTL.get(position).getTenTL());
                bundle.putString("moTaTL",listTL.get(position).getMoTa());
                bundle.putString("viTriTL",listTL.get(position).getViTri());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_theloai, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(ListTheLoaiActivity.this,TheLoaiActivity.class);
                startActivity(intent);
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listTL = theLoaiDAO.getAllTheLoai();
        theLoaiAdapter = new TheLoaiAdapter(this,listTL);
        lvTheLoai.setAdapter(theLoaiAdapter);
    }

}
