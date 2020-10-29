package vn.com.huy_firebase.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

import vn.com.huy_firebase.Adapter.NguoiDungAdapter;
import vn.com.huy_firebase.DAO.NguoiDungDAO;
import vn.com.huy_firebase.R;
import vn.com.huy_firebase.model.NguoiDung;

import static vn.com.huy_firebase.Activity.Main2Activity.nguoiDungs;

/**
 * A simple {@link Fragment} subclass.
 */
public class NguoiDungFragment extends Fragment {
  RecyclerView rcvNguoiDung;
  NguoiDungAdapter adapter;
  List<NguoiDung> list;
  NguoiDung nguoiDung;
  FloatingActionButton btnFloatNguoiDung;

  LinearLayoutManager mLayoutManager;
  DatabaseReference mDatabase;
  NguoiDungDAO dao;


  public NguoiDungFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment

    View view = inflater.inflate(R.layout.fragment_nguoi_dung, container, false);

    rcvNguoiDung = view.findViewById(R.id.rcvNguoiDung);
    btnFloatNguoiDung = view.findViewById(R.id.btnFloatNguoiDung);

    dao = new NguoiDungDAO(getActivity(), this);

    try {
      mLayoutManager = new LinearLayoutManager(getActivity());
      rcvNguoiDung.setLayoutManager(mLayoutManager);

      adapter = new NguoiDungAdapter(getActivity(), nguoiDungs, this);
      rcvNguoiDung.setAdapter(adapter);
    }
    catch (Exception e){
      Log.d("adapter", "Adapter không được");
    }



    btnFloatNguoiDung.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View view1 = inflater.inflate(R.layout.them_nguoi_dung, null);

        builder.setTitle("Thêm Người Dùng")
                .setView(view1)
                .setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                    EditText edtdkUser = view1.findViewById(R.id.edtdkUser);
                    EditText edtdkPass = view1.findViewById(R.id.edtdkPass);
                    EditText edtTen = view1.findViewById(R.id.edtTen);
                    EditText edtSDT = view1.findViewById(R.id.edtSDT);

                    dao = new NguoiDungDAO(getContext());

                    String userName = edtdkUser.getText().toString();
                    String passWord = edtdkPass.getText().toString();
                    String tenNguoiDung = edtTen.getText().toString();
                    String sdt = edtSDT.getText().toString();

                    nguoiDung = new NguoiDung(userName, passWord, sdt, tenNguoiDung);
                    dao.insert(nguoiDung);

                  }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {

                  }
                });
        builder.show();

      }
    });


    return view;

  }

  public void xoaNguoiDung(NguoiDung n){
    dao.delete(n);

  }



}