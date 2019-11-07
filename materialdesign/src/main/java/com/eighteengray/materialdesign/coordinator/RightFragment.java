package com.eighteengray.materialdesign.coordinator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.eighteengray.materialdesign.R;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RightFragment extends Fragment
{
    View view;

    public RightFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_right, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick()
    public void onClick(View view)
    {
        int i = view.getId();
        if (i == R.id.fab_saomiao) {

        } else if (i == R.id.fab_record) {

        } else if (i == R.id.fab_light) {

        } else if (i == R.id.fab_camera) {

        }
    }



}
