package com.example.arya.first;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PageFragment extends Fragment {
    private static final String ARG_PAGE_NUMBER = "page_number";
    FragmentTransaction ft;
    Fragment[] fragments = {new HistoryFragment(), new MyItemsFragment(), new MyFriendsFragment(), new CheckFragment()};
    TextView text;
    FrameLayout frameLayout;

    public PageFragment() {
    }

    public static PageFragment newInstance(int page) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page_layout, container, false);
        text = (TextView)rootView.findViewById(R.id.page_number_label);
//        frameLayout = (FrameLayout)rootView.findViewById(R.id.fragment_layout);


        fun(getArguments().getInt(ARG_PAGE_NUMBER) - 1);
//        switch (getArguments().getInt(ARG_PAGE_NUMBER, -1)) {
//            case 1:
//                text.setText("This is 1 page?");
//                ft = getFragmentManager().beginTransaction();
//                ft.replace(R.id.fragment_layout, fragments[0]);
//                ft.commit();
//                break;
//            case 2:
//                text.setText("This is 2 page?");
//                break;
//            case 3:
//                text.setText("This is tree page?");
//                break;
//            case 4:
//                text.setText("This is for page?");
//                break;
//            default:
//                text.setText("This is SPARTAAA!!!!1111");
//                break;
//        }

//        int page = getArguments().getInt(ARG_PAGE_NUMBER, -1);
//        text.setText(String.format("Pageeee %d", page));

        return rootView;
    }

    private void fun(int index) {
        text.setText("This is " + index + " page?");

        ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_layout, fragments[0]);
        ft.commit();
//                fragment = new MyItemsFragment();
//                ft = getFragmentManager().beginTransaction();
//                ft.add(R.id.fragment_layout, fragment);
//                ft.commit();
//                text.setText("This is second page?");
    }
}
