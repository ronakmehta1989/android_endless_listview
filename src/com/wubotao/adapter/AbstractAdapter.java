package com.wubotao.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * 
 * 
 * @author wubotao
 * 
 * @param <T>
 *            被存放元素的类型
 * 
 * 
 */
public abstract class AbstractAdapter<T> extends BaseAdapter {

	protected Context context;

	protected List<T> list = new ArrayList<T>();

	protected ListView listView;

	protected AbstractAdapter(Context context, ListView listView) {
		this.context = context;
		this.listView = listView;
	}

	public int getCount() {
		return list == null ? 0 : list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	protected Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			notifyDataSetChanged();
		}
	};

}
