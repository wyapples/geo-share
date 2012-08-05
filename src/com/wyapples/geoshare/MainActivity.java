package com.wyapples.geoshare;

import java.util.List;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockMapActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends SherlockMapActivity implements
		ActionBar.TabListener {

	public final static String TAG_SHARE = "share";
	public final static String TAG_RECEIVE = "receive";
	public final static int H_SHARE = 1;
	public final static int H_RECEIVE = 2;

	View shareView, receiveView;
	FrameLayout emptyLayout;
	MapView mapView;
	TextView geoInfoTextView;

	int mapCX, mapCY;

	public int whichTab = H_SHARE;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mapView = (MapView) findViewById(R.id.map);
		emptyLayout = (FrameLayout) findViewById(R.id.empty_layout);

		shareView = LayoutInflater.from(getBaseContext()).inflate(
				R.layout.inflater_share, null);
		geoInfoTextView = (TextView) shareView.findViewById(R.id.geo_info);

		receiveView = LayoutInflater.from(getBaseContext()).inflate(
				R.layout.inflater_receive, null);

		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		ActionBar.Tab shareTab = getSupportActionBar().newTab()
				.setText(TAG_SHARE).setTabListener(this);
		ActionBar.Tab receiveTab = getSupportActionBar().newTab()
				.setText(TAG_RECEIVE).setTabListener(this);
		getSupportActionBar().addTab(shareTab);
		getSupportActionBar().addTab(receiveTab);

		mapCX = mapView.getWidth() / 2;
		mapCY = mapView.getHeight() / 2;

		Projection mapProjection = mapView.getProjection();
		GeoPoint currentPoint = mapProjection.fromPixels(mapCX, mapCY);

		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(
				android.R.drawable.ic_menu_mylocation);
		CenterItemizedOverlay mCenterItemizedOverlay = new CenterItemizedOverlay(
				drawable, this, geoInfoTextView);
		OverlayItem overlayItem = new OverlayItem(mapView.getMapCenter(),
				"", "");
		mCenterItemizedOverlay.addOverlay(overlayItem);
		mapOverlays.add(mCenterItemizedOverlay);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

		switch (whichTab) {
		case H_SHARE:
			menu.clear();
			menu.add("My Location")
					.setIcon(android.R.drawable.ic_menu_mylocation)
					.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			menu.add("Send").setIcon(android.R.drawable.ic_menu_send)
					.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			break;

		case H_RECEIVE:
			menu.clear();
			break;
		}

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		String currentTab = (String) tab.getText();

		if (currentTab == TAG_SHARE) {
			whichTab = H_SHARE;
			emptyLayout.removeAllViews();
			emptyLayout.addView(shareView);

		} else if (currentTab == TAG_RECEIVE) {
			whichTab = H_RECEIVE;
			emptyLayout.removeAllViews();
			emptyLayout.addView(receiveView);
		}

		invalidateOptionsMenu();

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

}
