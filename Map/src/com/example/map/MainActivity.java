package com.example.map;

import java.io.IOException;
import java.util.List;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
//Mainfest的六個權限  
//property-->android-->4.4.2-->add-->google-play-services_lib
//Mainfest  <meta-data> * 2 & <<uses-feature>

public class MainActivity extends Activity implements LocationListener{
	
	TextView txv;
	GoogleMap map;
	int MIN_TIME = 5000;
	float MIN_DIST = 5;
	LocationManager mgr;
	Geocoder geo;
	List<Address> addresses = null;
	Address addr = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txv = (TextView) findViewById(R.id.txv);
        mgr = (LocationManager) getSystemService(LOCATION_SERVICE);
        //google map物件，此物件可操作地圖
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapView)).getMap();
        //顯示“我的位置”圖示按鈕
        map.setMyLocationEnabled(true);
        //設定地圖模式為普通街道模式
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //將縮放級數設為18
        map.moveCamera(CameraUpdateFactory.zoomTo(18));
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		if(location != null){
			txv.setText(String.format("緯度 %.4f, 經度 %.4f (%s 定位)", location.getLatitude(),location.getLongitude(),location.getProvider()));
			//將地圖中心移動至目前位置
			//map.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
			map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			//更改縮放級數
			//map.moveCamera(CameraUpdateFactory.zoomTo(16));
			geo = new Geocoder(this);
	        try {
				addresses = geo.getFromLocationName("新北市蘆州區中正路158號", 1);
				if(addresses == null || addresses.isEmpty()){
		        	Toast.makeText(this, "找不到該位置", Toast.LENGTH_SHORT).show();
		        }
		        else{
		        	addr = addresses.get(0);
		        	double lat = addr.getLatitude();
		        	double lng = addr.getLongitude();
		        	String strAddr = "";
		        	for (int i = 0; i <= addr.getMaxAddressLineIndex(); i++) {
						strAddr += addr.getAddressLine(i);
					}
		        	map.addMarker(new MarkerOptions()
		            .position(new LatLng(lat, lng))
		            .title(strAddr));
		        }	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			txv.setText("暫時無法取得資訊...");
		}
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mgr.removeUpdates(this);
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		String best = mgr.getBestProvider(new Criteria(), true);
		if(best != null){
			txv.setText("取得定位資訊中...");
			//mgr.requestLocationUpdates(best, MIN_TIME, MIN_DIST, this);
			mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
			mgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
		}
		else {
			txv.setText("請確認已開啟定位功能");
		}
	}


	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}