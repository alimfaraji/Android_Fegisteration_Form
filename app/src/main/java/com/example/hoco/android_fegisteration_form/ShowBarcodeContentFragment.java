package com.example.hoco.android_fegisteration_form;

/**
 * Created by dorna on 8/11/15.
 */

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@TargetApi(11)
public class ShowBarcodeContentFragment extends Fragment {
    TextView contentText, formatText;

    @Override
    @TargetApi(11)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {
        View view = inflater.inflate(R.layout.fragment_show_barcode_content, container, false);

        contentText = (TextView) view.findViewById(R.id.content_barcode_barcodePage);
        contentText.setText(getArguments().getString(getString(R.string.contents_barcode)));

        formatText = (TextView) view.findViewById(R.id.format_barcode_barcodePage);
        formatText.setText(getArguments().getString(getString(R.string.format_barcode)));

        return view;
    }

}