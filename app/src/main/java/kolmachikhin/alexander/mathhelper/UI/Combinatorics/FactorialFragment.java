package kolmachikhin.alexander.mathhelper.UI.Combinatorics;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import kolmachikhin.alexander.mathhelper.R;
import kolmachikhin.alexander.mathhelper.UI.CalcFragment;

import static kolmachikhin.alexander.mathhelper.Core.CombinatoricsLogic.ComboLogic.factorial;

public class FactorialFragment extends CalcFragment {

    private EditText etN;

    private int n;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.factorial_fragment, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        etN = v.findViewById(R.id.et_n);
    }

    @Override
    protected void calculate() {
        setTvResult("Ответ = " + factorial(n));
    }

    @Override
    protected void getValues() {
        n = Integer.parseInt(etN.getText().toString());
    }

}
