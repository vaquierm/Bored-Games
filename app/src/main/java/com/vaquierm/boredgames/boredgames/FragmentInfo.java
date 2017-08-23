package com.vaquierm.boredgames.boredgames;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Michael Vaquier on 2017-08-22.
 */

public class FragmentInfo extends Fragment {

    private final static String TAG = FragmentInfo.class.getName();

    Button closeButton;

    FragmentInfo instance;

    public FragmentInfo() {
        //required empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "FragmentInfo created");
        instance = this;

        View view = inflater.inflate(R.layout.fragment_info, container, false);

        closeButton = (Button) view.findViewById(R.id.close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "closeButton CLICK");
                getFragmentManager().beginTransaction().remove(instance).commit();
            }
        });

        return view;
    }
}
