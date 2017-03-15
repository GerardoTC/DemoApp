package com.demo.gerardo_tarazona.demoapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by elizabethtarazona on 14/03/2017.
 */

public class UserAdapter extends ArrayAdapter<UserObject> {
    private Activity context;
    private List<UserObject> userInfoList;
    public UserAdapter (Activity context,List<UserObject> userInfoList){
        super(context,R.layout.one_user_layout,userInfoList);
        this.context = context;
        this.userInfoList = userInfoList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.one_user_layout,null,true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.user);


        UserObject user = userInfoList.get(position);
        textViewName.setText(user.getName());

        return listViewItem;

    }

}
