package com.example.gio.firstproject.fragmentdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.gio.firstproject.R;

/**
 * Created by Gio on 3/23/2017.
 */

public class TopFragmentDemo2 extends Fragment {

    private EditText inputNameText;
    private EditText inputAgeText;

    private FragmentMainDemo2 mainActivity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Đọc file xml tạo ra đối tượng View.
        // inflate(@LayoutRes int resource, @Nullable ViewGroup root, boolean attachToRoot)
        View view = inflater.inflate(R.layout.activity_fragment_top, container, false);

        inputNameText = (EditText) view.findViewById(R.id.edtName);
        inputAgeText = (EditText) view.findViewById(R.id.edtAge);

        Button applyButton = (Button) view.findViewById(R.id.btnApply);

        applyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                applyText();
            }
        });

        return view;
    }


    //  Phương thức này được gọi sau khi Fragment được ghép vào Activity.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FragmentMainDemo2) {
            this.mainActivity = (FragmentMainDemo2) context;
        }
    }


    private void applyText() {
        String topText = this.inputNameText.getText().toString();
        String bottomText = this.inputAgeText.getText().toString();

        this.mainActivity.showText(topText, bottomText);
    }
}
