package com.android.smap.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.smap.GatewayApp;
import com.android.smap.R;
import com.android.smap.activities.FragmentContainerActivity.Builder;
import com.android.smap.adapters.SurveyAdapter;
import com.android.smap.api.models.Survey;
import com.android.smap.controllers.ControllerError;
import com.android.smap.controllers.ControllerErrorListener;
import com.android.smap.controllers.ControllerListener;
import com.android.smap.controllers.SurveyDefinitionsController;
import com.android.smap.di.DataManager;
import com.android.smap.utils.MWUiUtils;
import com.google.inject.Inject;

public class TestFragment extends BaseFragment implements
		OnItemClickListener,
		ControllerListener,
		ControllerErrorListener {

	@Inject
	private DataManager					mDataManager;
	private SurveyAdapter				mAdapter;
	private SurveyDefinitionsController	mController;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		LinearLayout view = (LinearLayout) inflater.inflate(
				R.layout.fragment_surveys,
				null);

		ListView listView = (ListView) view.findViewById(R.id.list_surveys);
		mDataManager = GatewayApp.getDependencyContainer().getDataManager();
		mAdapter = new SurveyAdapter(getActivity(), mDataManager
				.getSurveys());
		listView.setOnItemClickListener(this);
		listView.setAdapter(mAdapter);
		mController = new SurveyDefinitionsController(getActivity(), this, this);
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		mController.start();
	}

	@Override
	public void onControllerResult() {
		mAdapter.setModel(mDataManager.getSurveys());
	}

	@Override
	public void onControllerError(ControllerError error) {
		MWUiUtils.showMessagePopup(getActivity(), "Failed to retrieve Surveys");
	}

	@Override
	public void onItemClick(AdapterView<?> av, View parent, int pos, long viewId) {

		Survey survey = (Survey) mAdapter.getItem(pos);

		Bundle b = new Bundle();
		b.putLong(SurveyDetailFragment.EXTRA_SURVEY_ID, survey.getId());
		startActivity(new Builder(getActivity(), SurveyDetailFragment.class)
				.arguments(b).build());
	}
}
