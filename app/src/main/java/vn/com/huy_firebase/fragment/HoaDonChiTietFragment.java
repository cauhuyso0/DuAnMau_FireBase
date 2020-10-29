package vn.com.huy_firebase.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import vn.com.huy_firebase.Adapter.HoaDonChiTietAdapter;
import vn.com.huy_firebase.DAO.HoaDonChiTietDAO;
import vn.com.huy_firebase.R;
import vn.com.huy_firebase.model.HoaDonChiTiet;

import static vn.com.huy_firebase.Activity.Main2Activity.listHDCTs;

/**
 * A simple {@link Fragment} subclass.
 */
public class HoaDonChiTietFragment extends Fragment {
    RecyclerView rcvHDCT;
    HoaDonChiTietDAO chiTietDAO;
    HoaDonChiTietAdapter adapter;
    LinearLayoutManager mLayoutManager;


    public HoaDonChiTietFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hoa_don_chi_tiet, container, false);
        rcvHDCT = view.findViewById(R.id.rcvHDCT);

//        chiTietDAO = new HoaDonChiTietDAO(getActivity(), this);
//
//
//        mLayoutManager = new LinearLayoutManager(getActivity());
//        rcvHDCT.setLayoutManager(mLayoutManager);
//
//        adapter = new HoaDonChiTietAdapter(getActivity(), listHDCTs, this);
//        rcvHDCT.setAdapter(adapter);

        return view;
    }
public void capnhatLV(){
    adapter.notifyItemInserted(listHDCTs.size());

    adapter.notifyDataSetChanged();
}
}
