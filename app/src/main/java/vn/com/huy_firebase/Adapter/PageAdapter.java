package vn.com.huy_firebase.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import vn.com.huy_firebase.R;
import vn.com.huy_firebase.fragment.LoaiSachFragment;
import vn.com.huy_firebase.fragment.SachFragment;

public class PageAdapter extends FragmentStatePagerAdapter {
    public PageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                LoaiSachFragment f0 = new LoaiSachFragment();
                Bundle b1 = new Bundle();
                b1.putInt("loaithu", R.layout.fragment_loai_sach);
                f0.setArguments(b1);
                return f0;

            case 1:
                SachFragment f1 = new SachFragment();

                Bundle b2  = new Bundle();
                b2.putInt("loaichi", R.layout.fragment_sach);
                f1.setArguments(b2);
                return f1;
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
