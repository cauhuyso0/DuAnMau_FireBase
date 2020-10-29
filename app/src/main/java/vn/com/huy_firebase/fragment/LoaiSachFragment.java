package vn.com.huy_firebase.fragment;


import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import vn.com.huy_firebase.Adapter.LoaiSachAdapter;
import vn.com.huy_firebase.Adapter.NguoiDungAdapter;
import vn.com.huy_firebase.DAO.LoaiSachDAO;
import vn.com.huy_firebase.DAO.NguoiDungDAO;
import vn.com.huy_firebase.R;
import vn.com.huy_firebase.model.LoaiSach;
import vn.com.huy_firebase.model.NguoiDung;

import static vn.com.huy_firebase.Activity.Main2Activity.loaiSaches;
import static vn.com.huy_firebase.Activity.Main2Activity.saches;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoaiSachFragment extends Fragment {
    RecyclerView rcvLoaiSach;
    LoaiSachAdapter adapter;
    LoaiSach loaiSach;
    public static List<LoaiSach> listLoaiSachs;

    LinearLayoutManager mLayoutManager;
    LoaiSachDAO dao;

    public LoaiSachFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_loai_sach, container, false);

        rcvLoaiSach = view.findViewById(R.id.rcvLoaiSach);

        dao = new LoaiSachDAO(getActivity(), this);
        listLoaiSachs = dao.getAll();
        mLayoutManager = new LinearLayoutManager(getContext());
        rcvLoaiSach.setLayoutManager(mLayoutManager);

        adapter = new LoaiSachAdapter(getContext(), listLoaiSachs, this);
        rcvLoaiSach.setAdapter(adapter);
        return view;
    }

    public void capnhatLV(){
        adapter.notifyItemInserted(listLoaiSachs.size());

        adapter.notifyDataSetChanged();

    }
}
