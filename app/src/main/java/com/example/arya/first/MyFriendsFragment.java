package com.example.arya.first;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MyFriendsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_friends, container, false);
        LinearLayout linearLayout = (LinearLayout)rootView.findViewById(R.id.friends_list);

        ArrayList<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(0);
        user1.setName("Marina");
        users.add(user1);
        User user2 = new User();
        user2.setId(1);
        user2.setName("Denis");
        users.add(user2);
        User user3 = new User();
        user3.setId(2);
        user3.setName("Natalya");
        users.add(user3);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = 50;
        layoutParams.topMargin = 25;
        for (final User user : users) {
            final TextView name = new TextView(inflater.getContext());
            name.setText(user.getName());
            name.setTextSize(18);
            name.setTextColor(getResources().getColor(R.color.black));
            name.setLayoutParams(layoutParams);
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast toast = Toast.makeText(inflater.getContext(),
                            "User index - " + user.getId(), Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
            linearLayout.addView(name);
        }
        return rootView;
    }
}
