package kolmachikhin.alexander.mathhelper.UI.TheoryOfChances;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

import kolmachikhin.alexander.mathhelper.Core.TheoryOfChancesLogic.MathWaitLogic;
import kolmachikhin.alexander.mathhelper.R;
import kolmachikhin.alexander.mathhelper.UI.CalcFragment;
import kolmachikhin.alexander.mathhelper.UI.UI;

public class MathWaitFragment extends CalcFragment {

    private EditText etCapacity;

    private LinearLayout xLayout;
    private LinearLayout pLayout;

    private ArrayList<Double> xList = new ArrayList<>();
    private ArrayList<Double> pList = new ArrayList<>();

    private int capacity = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.math_wait_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        etCapacity = v.findViewById(R.id.et_capacity);

        xLayout = v.findViewById(R.id.x_list);
        pLayout = v.findViewById(R.id.p_list);

        etCapacity.setOnKeyListener((v1, keyCode, event) -> {
            setCapacity();
            return false;
        });
    }

    private EditText getETById(int id) {
        return a.findViewById(id);
    }

    private double getValueById(int id) {
        EditText et = getETById(id);
        String value =  et.getText().toString();
        if (value.equals("")) value = "0";
        return  Double.valueOf(value);
    }

    private void setCapacity() {
        try {
            xLayout.removeAllViews();
            pLayout.removeAllViews();

            capacity = Integer.parseInt(etCapacity.getText().toString());

            for (int i = 0; i < capacity; i++) {
                xLayout.addView(buildET(i));
                pLayout.addView(buildET(i + 1000));
            }
        } catch (Exception ignored) {}
    }

    @SuppressLint("SetTextI18n")
    private EditText buildET(int id) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(UI.dp(50), UI.dp(50));

        EditText et = new EditText(a);

        if (id < 1000) et.setText(id + "");
        et.setId(id);
        et.setTextColor(Color.BLACK);
        et.setInputType(InputType.TYPE_CLASS_PHONE);
        et.setGravity(Gravity.CENTER);
        et.setLayoutParams(params);

        return et;
    }

    @Override
    protected void getValues() {
        xList.clear();
        pList.clear();
        for (int i = 0; i < capacity; i++) {
            xList.add(getValueById(i));
            pList.add(getValueById(i + 1000));
        }
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void calculate() {
        double m = MathWaitLogic.M(xList, pList);
        double mx2 = MathWaitLogic.Mx2(xList, pList);
        double d = MathWaitLogic.D(xList, pList);
        double s = MathWaitLogic.S(xList, pList);

        String result = "M = " + String.format("%.10f", m) + "\n"
                + "M(x^2) = " + String.format("%.10f", mx2) + "\n"
                + "D = " + String.format("%.10f", d) + "\n"
                + "S = " + String.format("%.10f", s);

        setTvResult(result);
    }

}
