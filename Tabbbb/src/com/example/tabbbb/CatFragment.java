package com.example.tabbbb;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CatFragment extends Fragment{
	private String value = "";
	
	@Override	//只有在頁籤第一次顯示時被呼叫，它會傳入母體 Activity ，所以這時候可以呼叫它的公開方法去取得值
	public void onAttach(Activity activity){
		super.onAttach(activity);
		
		MainActivity mainActivity = (MainActivity) activity;
		value = mainActivity.getCatData();
	}
	
	@Override	//建立layout
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.cat_tab, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		TextView txtResult = (TextView) this.getView().findViewById(R.id.textView1);
		txtResult.setText(value);
	}
}
