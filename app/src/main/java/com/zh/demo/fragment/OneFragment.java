package com.zh.demo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zh.demo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OneFragment extends Fragment {
    public static final String TAG = "Fragment_LifeCycle";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "onefragment_param1";
    private static final String ARG_PARAM2 = "onefragment_param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OneFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OneFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OneFragment newInstance(String param1, String param2) {
        OneFragment fragment = new OneFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i(TAG, "OneFragment: onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "OneFragment: onCreate");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "OneFragment: onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "OneFragment: onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "OneFragment: onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "OneFragment: onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "OneFragment: onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "OneFragment: onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "OneFragment: onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "OneFragment: onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "OneFragment: onDetach");
    }
}