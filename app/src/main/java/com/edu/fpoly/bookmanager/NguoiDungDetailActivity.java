package com.edu.fpoly.bookmanager;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.edu.fpoly.bookmanager.dao.UserDAO;
import com.edu.fpoly.bookmanager.model.User;


public class NguoiDungDetailActivity extends AppCompatActivity {

    TextInputLayout edFullName, edPhone;
    UserDAO userDAO;
    String fullName, phone, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("CHI TIẾT NGƯỜI DÙNG");
        setContentView(R.layout.activity_nguoi_dung_detail);

        edFullName = findViewById(R.id.edFullNameSua);
        edPhone = findViewById(R.id.edPhoneSua);
        userDAO = new UserDAO(this);

        Log.i("tt", "111");
        Bundle bundle = getIntent().getExtras();
        fullName = bundle.getString("tenUser","");
        phone = bundle.getString("phoneUser", "");
        username = bundle.getString("idUser", "");

        edFullName.getEditText().setText(fullName);
        edPhone.getEditText().setText(phone);
    }

    public void suaUser(View view) {
        User u = userDAO.checkUserExist(username);
        String fullname = checkFullname();
        String phone = checkPhone();

        if(fullname != null && phone != null){
            u.setHoten(fullname);
            u.setPhone(phone);
            if(userDAO.updateUser(u) > 0){
                Toast.makeText(this, "Sửa thông tin thành công!", Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(this, "Sửa thông tin không thành công!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String checkFullname(){
        String fullname = null;
        edFullName.setError(null);
        try{
            fullname = edFullName.getEditText().getText().toString();
            if(fullname.length() == 0){
                edFullName.setError("Họ tên không được để trống!");
                return null;
            }
            if(fullname.length() < 5 || fullname.length() > 50){
                edFullName.setError("Họ tên có độ dài từ 5-50 ký tự!");
                return null;
            }
        }catch(Exception e){
            edFullName.setError("Họ tên không hợp lệ!");
        }
        return fullname;
    }

    private String checkPhone(){
        String num = null;
        edPhone.setError(null);
        try{
            num = edPhone.getEditText().getText().toString();
            if(num.length() == 0){
                edPhone.setError("Số điện thoại không được để trống!");
                return null;
            }
            if(num.length() != 10){
                edPhone.setError("Số điện thoại phải có 10 số!");
                return null;
            }
        }catch(Exception e){
            edPhone.setError("Số điện thoại không hợp lệ!");
        }
        return num;
    }
}
