package org.dahlson.spinemedical;

import android.view.View;
import android.widget.TextView;

public interface OnDoctorItemClickListener {
    public void onItemClick(DoctorListAdapter.ViewHolder holder, TextView textView);
}
