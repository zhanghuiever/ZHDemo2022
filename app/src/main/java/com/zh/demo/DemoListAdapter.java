package com.zh.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class DemoListAdapter extends BaseAdapter {

    public final static String DEMO_LIST_ITEM_TITLE = "title";

    private LayoutInflater mInflater;
    private List<Map<String, Object>> mList;
    private OnDemoListItemClickListener onDemoListItemClickListener;

    public void setOnDemoListItemClickListener(OnDemoListItemClickListener onDemoListItemClickListener) {
        this.onDemoListItemClickListener = onDemoListItemClickListener;
    }

    public DemoListAdapter(Context context, List<Map<String, Object>> mList) {
        this.mInflater = LayoutInflater.from(context);
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView ==null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.layout_demolist_item, null);
            viewHolder.title = convertView.findViewById(R.id.demo_list_item_title);
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String title = (String) mList.get(position).get(DEMO_LIST_ITEM_TITLE);
        viewHolder.title.setText(title);
        viewHolder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDemoListItemClickListener.onItemClick(position);
            }
        });
        return convertView;
    }

    public final class ViewHolder{
        public TextView title;
    }

    public interface OnDemoListItemClickListener{
        void onItemClick(int position);
    }
}
