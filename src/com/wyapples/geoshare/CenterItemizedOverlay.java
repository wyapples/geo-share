package com.wyapples.geoshare;

import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.widget.TextView;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class CenterItemizedOverlay extends ItemizedOverlay {
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	Context mContext;
	TextView mTextView;
	CenterItemizedOverlay mCenterItemizedOverlay;

	public CenterItemizedOverlay(Drawable defaultMarker) {
		super(defaultMarker);
		// TODO Auto-generated constructor stub
	}

	public CenterItemizedOverlay(Drawable defaultMarker, Context context,
			TextView textView) {
		super(boundCenterBottom(defaultMarker));
		mContext = context;
		mTextView = textView;
	}

	public void addOverlay(OverlayItem overlay) {
		mOverlays.add(overlay);
		populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event, MapView mapView) {
		// TODO Auto-generated method stub
		//if (event.getAction() == MotionEvent.ACTION_UP) {
			mapView.getOverlays().remove(0);
			mTextView.setText("fuck");
			OverlayItem overlayItem = new OverlayItem(mapView.getMapCenter(),
					"", "");
			Drawable drawable = mContext.getResources().getDrawable(
					android.R.drawable.ic_menu_mylocation);
			mCenterItemizedOverlay = new CenterItemizedOverlay(drawable, mContext, mTextView);
			mCenterItemizedOverlay.addOverlay(overlayItem);
			mapView.getOverlays().add(mCenterItemizedOverlay);

		//}
		return super.onTouchEvent(event, mapView);
	}

}
