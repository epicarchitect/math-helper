package kolmachikhin.alexander.mathhelper.UI;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import kolmachikhin.alexander.mathhelper.R;

public abstract class CalcFragment extends Fragment {

    protected Activity a;
    protected Button buttonCalculate;
    protected TextView tvResult;
    protected Context context;

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        a = getActivity();
        context = getContext();
        tvResult = v.findViewById(R.id.tv_result);
        buttonCalculate = v.findViewById(R.id.button_calculate);

        buttonCalculate.setOnClickListener(view -> {
            try {
                getValues();
                calculate();
            } catch (Exception e) {
                printError();
            }

            UI.hideKeyboard();
        });
    }


    public void setTvResult(String result) {
        tvResult.setText(result);
    }

    protected abstract void calculate();

    protected abstract void getValues();

    protected void printError() {
        Toast.makeText(a, "Ошибка при считывании данных", Toast.LENGTH_LONG).show();
    }

    public void open() {
        UI.replace(R.id.main_container, this);
    }

}
