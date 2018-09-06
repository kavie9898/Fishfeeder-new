package com.fishfeeder_new.user;

/**
 * Created by Jasmeet on 1/29/2018.
 */
import android.content.Context;
import java.util.List;
import android.app.Activity;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fishfeeder_new.R;

public class ListAdapterClass extends BaseAdapter {

    Context context;
    List<User> valueList;

    public ListAdapterClass(List<User> listValue, Context context)
    {
        this.context = context;
        this.valueList = listValue;
    }

    @Override
    public int getCount()
    {
        return this.valueList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.valueList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewItem viewItem = null;

        if(convertView == null)
        {
            viewItem = new ViewItem();
            LayoutInflater layoutInfiater = (LayoutInflater)this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInfiater.inflate(R.layout.listview_item, null);
            viewItem.ViewUserName = (TextView)convertView.findViewById(R.id.textView1);
            convertView.setTag(viewItem);
        }
        else
        {
            viewItem = (ViewItem) convertView.getTag();
        }

        viewItem.ViewUserName.setText(valueList.get(position).UserName);

        return convertView;
    }
}

class ViewItem
{
    TextView ViewUserName;

}