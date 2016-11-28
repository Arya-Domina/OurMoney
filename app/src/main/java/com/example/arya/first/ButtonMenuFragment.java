package com.example.arya.first;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ButtonMenuFragment extends Fragment {

    private Button buttonMyItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_button_menu, container, false);

        buttonMyItems = (Button) rootView.findViewById(R.id.button_my_items);

        buttonMyItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ButtonMenuFragment.this, MyItemsActivity.class);
//                startActivity(intent);
            }
        });

        return rootView;
    }
}
