package com.edu.fpoly.bookmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.edu.fpoly.bookmanager.adapter.UserAdapter;
import com.edu.fpoly.bookmanager.dao.UserDAO;
import com.edu.fpoly.bookmanager.model.User;

import java.util.List;

public class ListNguoiDungActivity extends AppCompatActivity {

    ListView lvUser;
    public static List<User> listUser;
    UserDAO userDAO;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("NGƯỜI DÙNG");
        setContentView(R.layout.activity_list_nguoi_dung);

        lvUser = (ListView) findViewById(R.id.lvNguoiDung);

        userDAO = new UserDAO(ListNguoiDungActivity.this);
        listUser = userDAO.getAllNguoiDung();
        userAdapter = new UserAdapter(this,listUser);
        lvUser.setAdapter(userAdapter);
        Log.i("tt", "333");

        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(ListNguoiDungActivity.this, NguoiDungDetailActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString("tenUser",listUser.get(i).getHoten());
                bundle.putString("phoneUser",listUser.get(i).getPhone());
                bundle.putString("idUser",listUser.get(i).getUsername());
                Log.i("tt", "222");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listUser = userDAO.getAllNguoiDung();
        userAdapter = new UserAdapter(this,listUser);
        lvUser.setAdapter(userAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch(item.getItemId()) {
            case R.id.add:
                intent = new Intent(ListNguoiDungActivity.this,NguoiDungActivity.class);
                startActivity(intent);
                return(true);
            case R.id.changePass:
                intent = new Intent(ListNguoiDungActivity.this,ChangePasswordActivity.class);
                startActivity(intent);
                return(true);
            case R.id.logOut:
                SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                //xoa tinh trang luu tru truoc do
                edit.clear();
                edit.commit();
                intent = new Intent(ListNguoiDungActivity.this,MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
