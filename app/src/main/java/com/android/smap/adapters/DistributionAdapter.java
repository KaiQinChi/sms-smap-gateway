package com.android.smap.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.android.smap.R;
import com.android.smap.api.models.Distribution;
import com.android.smap.api.models.Survey;
import com.android.smap.ui.VelocAdapter;
import com.android.smap.ui.ViewQuery;
import com.google.inject.Inject;

import java.util.List;

public class DistributionAdapter extends VelocAdapter {

	private List<Distribution>	mModel;

	@Inject
	public DistributionAdapter(Context context, List<Distribution> model) {
		super(context);
		this.mModel = model;
	}

	@Override
	public View newView(LayoutInflater inflator, int position, ViewGroup parent) {
		return inflator.inflate(R.layout.item_distribution, null, false);
	}

	@Override
	public void bindView(Context context, View view, ViewQuery query,
			int position) {

        Distribution distribution = mModel.get((position));
		
		// TODO - Revisit when percentage bar when complete / incomplete is implemented

		String distributionName = distribution.getName();
		int totalDialogue = distribution.getMembersCount();
        int completedDialogue = distribution.getCompletedCount();
        query.find(R.id.txt_name).text(distributionName);

		// String formatting
		String template = getContext().getResources().getString(
				R.string.template_quotient);

        String progress = String.format(template, completedDialogue, totalDialogue);
        query.find(R.id.txt_member_progress).text(String.valueOf(progress));

	}

	@Override
	public int getCount() {
		return mModel.size();
	}

	@Override
	public Distribution getItem(int position) {

        if(mModel != null){
            return mModel.get(position);
        }
        return null;
	}

	public void setModel(List<Distribution> model) {
		this.mModel = model;
		notifyDataSetChanged();
	}

}
