package com.wubotao.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.wubotao.adapter.StringAdapter;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_list);

		ListView listView = (ListView) findViewById(R.id.listview_main);
		StringAdapter adapter = new StringAdapter(this, listView);
		listView.setAdapter(adapter);

	}
}
