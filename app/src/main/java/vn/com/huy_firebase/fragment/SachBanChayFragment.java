package vn.com.huy_firebase.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


import vn.com.huy_firebase.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SachBanChayFragment extends Fragment {


    public SachBanChayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sach_ban_chay, container, false);
    }

}
