package com.example.jtalk.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jtalk.R;
import com.example.jtalk.model.User;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchAdapter extends BaseAdapter {

    List<User> userList;
    List<User> resultList;

    public SearchAdapter(List<User> resultList) {
        userList = new ArrayList<>();
        this.resultList = resultList;
        userList.addAll(resultList);

    }

    public List<User> getUserList() {
        return userList;
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public User getItem(int position) {
        return resultList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View userView;
        TextView name;
        User user = (User) getItem(position);
        CircleImageView avatar;

        if (convertView == null) {
            userView = View.inflate(parent.getContext(), R.layout.friend_item_search_fragment, null);
        } else {
            userView = convertView;
        }

        // binding data
        name =(TextView) userView.findViewById(R.id.username);
        name.setText(user.username);
        if(!user.avatar.equals("")){
            avatar =(CircleImageView) userView.findViewById(R.id.avatar);
            Glide.with(parent.getContext()).load(user.avatar).into(avatar);
        }

        return userView;
    }

    public void noticeDataChanged() {
        resultList.clear();
        resultList.addAll(userList);
        notifyDataSetChanged();
    }

    public void filter(String searchText) {
        resultList.clear();
        if (searchText.isEmpty() || searchText.equals("")) {
            resultList.addAll(userList);
        } else {

            for (User user : userList) {
                if (user.username.toLowerCase().contains(searchText.toLowerCase())) {
                    resultList.add(user);
                }
            }
        }
        notifyDataSetChanged();
    }




}
