package kolmachikhin.alexander.mathhelper.UI.TheoryOfChances;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import kolmachikhin.alexander.mathhelper.Core.TheoryOfChancesLogic.LaplaceLogic;
import kolmachikhin.alexander.mathhelper.R;
import kolmachikhin.alexander.mathhelper.UI.CalcFragment;

public class IntegralLaplaceFragment extends CalcFragment {

    private EditText etA;
    private EditText etB;
    private EditText etN;
    private EditText etP;

    private int a, b, n;
    private double p;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.integral_laplace_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        etA = v.findViewById(R.id.et_a);
        etB = v.findViewById(R.id.et_b);
        etN = v.findViewById(R.id.et_n);
        etP = v.findViewById(R.id.et_p);
    }

    @Override
    protected void getValues() {
        a = Integer.parseInt(etA.getText().toString());
        b = Integer.parseInt(etB.getText().toString());
        n = Integer.parseInt(etN.getText().toString());
        p = Double.parseDouble(etP.getText().toString());
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void calculate() {
        double l = LaplaceLogic.integralLaplace(a, b, n, p);
        setTvResult("Ответ = " + String.format("%.10f", l));
    }



}
