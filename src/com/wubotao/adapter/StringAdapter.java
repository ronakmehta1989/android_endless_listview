package com.wubotao.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.wubotao.activity.R;

/**
 * 
 * 
 * @author wubotao
 * 
 */
public class StringAdapter extends AbstractAdapter<String> {

	boolean headerLoading = false;
	boolean footerLoading = false;

	ViewSwitcher footerView;
	ViewSwitcher headerView;

	public StringAdapter(Context context, ListView listView) {
		super(context, listView);

		LayoutInflater inflater = LayoutInflater.from(context);

		headerView = (ViewSwitcher) inflater.inflate(R.layout.header, null);
		listView.addHeaderView(headerView);
		headerLoading(true);

		footerView = (ViewSwitcher) inflater.inflate(R.layout.footer, null);

		new ContentTask().execute();

		TextView moreByHeader = (TextView) headerView
				.findViewById(R.id.textview_to_load_more);
		moreByHeader.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!headerLoading) {
					new HeaderTask().execute();
				}
			}
		});

		TextView moreByFooter = (TextView) footerView
				.findViewById(R.id.textview_to_load_more);
		moreByFooter.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!footerLoading) {
					new FooterTask().execute();
				}
			}
		});
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.main_list_item, null);
			viewHolder.content = (TextView) convertView
					.findViewById(R.id.textview_content);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		String text = (String) getItem(position);
		viewHolder.content.setText(text);

		return convertView;
	}

	class ViewHolder {
		TextView content;
	}

	class HeaderTask extends AsyncTask<String, Integer, List<String>> {

		@Override
		protected void onPreExecute() {
			headerLoading(true);
		}

		@Override
		protected List<String> doInBackground(String... params) {
			List<String> list = new ArrayList<String>();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < 3; i++) {
				list.add("header-" + i);
			}
			return list;
		}

		@Override
		protected void onPostExecute(List<String> result) {
			list.addAll(0, result);
			notifyDataSetChanged();

			headerLoading(false);
		}

	}

	class ContentTask extends AsyncTask<String, Integer, List<String>> {

		@Override
		protected List<String> doInBackground(String... params) {
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < 3; i++) {
				list.add("content-" + i);
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			return list;
		}

		@Override
		protected void onPostExecute(List<String> result) {
			list.addAll(0, result);
			notifyDataSetChanged();

			headerLoading(false);
			listView.addFooterView(footerView);
		}

	}

	class FooterTask extends AsyncTask<String, Integer, List<String>> {

		@Override
		protected void onPreExecute() {
			footerLoading(true);
		}

		@Override
		protected List<String> doInBackground(String... params) {
			List<String> messages = new ArrayList<String>();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < 3; i++) {
				list.add("footer-" + i);
			}
			return messages;
		}

		@Override
		protected void onPostExecute(List<String> result) {
			list.addAll(result);
			notifyDataSetChanged();

			footerLoading(false);
		}

	}

	private void footerLoading(boolean isLoading) {
		if (isLoading) {
			footerView.setDisplayedChild(1);
		} else {
			footerView.setDisplayedChild(0);
		}
		footerLoading = isLoading;
	}

	private void headerLoading(boolean isLoading) {
		if (isLoading) {
			headerView.setDisplayedChild(1);
		} else {
			headerView.setDisplayedChild(0);
		}
		headerLoading = isLoading;
	}

}
