package vn.com.huy_firebase.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import vn.com.huy_firebase.DAO.NguoiDungDAO;
import vn.com.huy_firebase.R;
import vn.com.huy_firebase.model.NguoiDung;

public class MainActivity extends AppCompatActivity {
    ArrayList<NguoiDung> listNguoiDung = new ArrayList<>();
    EditText edtUser, edtPass;
    Button btnDangNhap;
    NguoiDungDAO dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        dao = new NguoiDungDAO(MainActivity.this);
        listNguoiDung = (ArrayList<NguoiDung>) dao.getAll();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.activity_main);

                edtUser = findViewById(R.id.edtUser);
                edtPass = findViewById(R.id.edtPass);
                btnDangNhap =findViewById(R.id.btnDangNhap);

                btnDangNhap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NguoiDung nguoiDung;
                        int co = 0;
                        String ten = "";
                        for (int i = 0; i < listNguoiDung.size(); i++) {
                            nguoiDung = listNguoiDung.get(i);
                            String user = nguoiDung.getUserName();
                            String pass = nguoiDung.getPassword();
                            if (edtUser.getText().toString().equalsIgnoreCase(user) && edtPass.getText().toString().equalsIgnoreCase(pass)) {
                                co = 1;
                                ten = nguoiDung.getHoTen();
                            }
                        }
                            if (co == 1){

                            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                            intent.putExtra("ten",ten);
                            startActivity(intent);
                        }
                            else {
                                Toast.makeText(MainActivity.this, "Sai tài khoản or mật khẩu", Toast.LENGTH_SHORT).show();
                            }

                    }
                });
            }
        }, 2000);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "xin duoc quyen roi", Toast.LENGTH_SHORT).show();

        }
    }

}
