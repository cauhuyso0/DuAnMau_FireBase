package vn.com.huy_firebase.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import vn.com.huy_firebase.Adapter.SachAdapter;
import vn.com.huy_firebase.DAO.LoaiSachDAO;
import vn.com.huy_firebase.DAO.SachDAO;
import vn.com.huy_firebase.R;
import vn.com.huy_firebase.model.LoaiSach;
import vn.com.huy_firebase.model.Sach;


import static vn.com.huy_firebase.Activity.Main2Activity.loaiSaches;
import static vn.com.huy_firebase.Activity.Main2Activity.saches;

/**
 * A simple {@link Fragment} subclass.
 */
public class SachFragment extends Fragment {
    RecyclerView rcvSach;
    SachAdapter adapter;
    Sach sach;
   public static List<Sach> listSaches;
    LinearLayoutManager mLayoutManager;
    SachDAO dao;

    public SachFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sach, container, false);
        rcvSach = view.findViewById(R.id.rcvSach);

        dao = new SachDAO(getContext(), this);
        listSaches = dao.getAll();
        mLayoutManager = new LinearLayoutManager(getContext());
        rcvSach.setLayoutManager(mLayoutManager);

        adapter = new SachAdapter(getContext(), listSaches, this);

        rcvSach.setAdapter(adapter);

        return view;
        }
    public void capnhatLV(){

        adapter.notifyItemInserted(listSaches.size());

        adapter.notifyDataSetChanged();
     }
    }
