package com.example.renpeng.banyou;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by renpeng on 17/5/30.
 */
public class DialogUtils {

    public static Dialog getSelectSexDialog(Context context, View.OnClickListener onClickListener){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.select_sex_layout, null);

        Button btnLeft = (Button) view.findViewById(R.id.btn_left);
        Button btnRight = (Button) view.findViewById(R.id.btn_right);

        btnLeft.setOnClickListener(onClickListener);
        btnRight.setOnClickListener(onClickListener);

        final Dialog dialog = new Dialog(context, R.style.FloatNormalDialogStyle);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(view);

        return dialog;

    }

}
