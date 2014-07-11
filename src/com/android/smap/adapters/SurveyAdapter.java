package com.android.smap.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.android.smap.R;
import com.android.smap.api.models.Survey;
import com.android.smap.ui.VelocAdapter;
import com.android.smap.ui.ViewQuery;
import com.android.smap.utils.MWUiUtils;
import com.google.inject.Inject;

public class SurveyAdapter extends VelocAdapter {

	private List<Survey>	mModel;

	@Inject
	public SurveyAdapter(Context context, List<Survey> model) {
		super(context);
		this.mModel = model;
	}

	@Override
	public View newView(LayoutInflater inflator, int position, ViewGroup parent) {
		return inflator.inflate(R.layout.item_survey, null, false);
	}

	@Override
	public void bindView(Context context, View view, ViewQuery query,
			int position) {

		int completed = mModel.get((position)).completed;
		int total = mModel.get((position)).members;
		int partial = mModel.get((position)).partial;
		int unfinished = (total - completed) - partial;
		boolean isFinished = completed == total ? true : false;

		String template = getContext().getResources().getString(
				R.string.template_quotient);
		String completedProgress = String.format(template, completed, total);
		String partialProgress = String.format(template, partial, unfinished);

		query.find(R.id.txt_survey).text(mModel.get((position)).name);
		query.find(R.id.txt_completed_progress).text(completedProgress);
		query.find(R.id.txt_member_progress).text(partialProgress);

		// set progress bar length
		View progress = query.find(R.id.view_progress).get();

		float percent = (float) ((float) completed / (float) total);
		LayoutParams params = progress.getLayoutParams();
		params.width = (int) (MWUiUtils.getScreenWidth(getContext()) * percent);
		progress.setLayoutParams(params);

		if (isFinished) {
			query.find(R.id.ic_tick).invisible(false);
		} else {
			query.find(R.id.ic_tick).invisible(true);
		}
	}

	@Override
	public int getCount() {
		return mModel.size();
	}

	@Override
	public Object getItem(int position) {
		return mModel.get(position);
	}

	public void setModel(List<Survey> model) {
		this.mModel = model;
	}

}
