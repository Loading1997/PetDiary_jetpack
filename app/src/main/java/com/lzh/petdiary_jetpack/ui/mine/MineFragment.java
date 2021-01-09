package com.lzh.petdiary_jetpack.ui.mine;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.lzh.libnavannotation.FragmentDestination;
import com.lzh.petdiary_jetpack.R;
import com.lzh.petdiary_jetpack.ui.home.HomeViewModel;

@FragmentDestination(pageUrl = "main/tabs/mine", asStarter = false)
public class MineFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Log.e(" E/","MineFragment : onCreateView");

        return root;
    }
}