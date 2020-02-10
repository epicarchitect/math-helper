package kolmachikhin.alexander.mathhelper.UI.Matrix;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import kolmachikhin.alexander.mathhelper.Core.MatrixLogic.Cramer;
import kolmachikhin.alexander.mathhelper.Core.MatrixLogic.Determinant;
import kolmachikhin.alexander.mathhelper.Core.MatrixLogic.Gauss;
import kolmachikhin.alexander.mathhelper.Core.MatrixLogic.Matrix;
import kolmachikhin.alexander.mathhelper.R;
import kolmachikhin.alexander.mathhelper.UI.CalcFragment;

import static kolmachikhin.alexander.mathhelper.UI.UI.dp;

public class MatrixFragment extends CalcFragment {

    public static final int MAX_CAPACITY = 10;
    public static final int MIN_CAPACITY = 1;

    public static final String GAUSS = "gauss";
    public static final String CRAMER = "cramer";
    public static final String DETERMINANT = "determinant";

    private String mode;

    private LinearLayout matrixLayout;
    private ImageView buttonUp;
    private ImageView buttonDown;
    private ImageView buttonMax;
    private ImageView buttonMin;
    private TextView tvCapacity;

    private int capacity = 2;
    private ArrayList<ArrayList<Double>> matrixArray = new ArrayList<>();
    private ArrayList<Double> f = new ArrayList<>();

    private Matrix matrix;

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        buttonUp = v.findViewById(R.id.button_up);
        buttonDown = v.findViewById(R.id.button_down);
        buttonMax = v.findViewById(R.id.button_max);
        buttonMin = v.findViewById(R.id.button_min);
        tvCapacity = v.findViewById(R.id.capacity);
        matrixLayout = v.findViewById(R.id.matrix);

        buttonUp.setOnClickListener(view -> capacityUp());

        buttonDown.setOnClickListener(view -> capacityDown());

        buttonMax.setOnClickListener(view -> capacityMax());

        buttonMin.setOnClickListener(view -> capacityMin());

        capacityUp();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.matrix_fragment, container, false);
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void calculate() {
        String result = "";

        if (mode.equals(DETERMINANT))
            result = "Ответ = " + String.format("%.10f", Determinant.determinant(matrix));

        if (mode.equals(GAUSS))
            result = new Gauss(matrix, f).getTextResult();

        if (mode.equals(CRAMER))
            result = new Cramer(matrix, f).getTextResult();

        setTvResult(result);
    }

    @Override
    protected void getValues() {
        readMatrixArray();
        readFreeArray();
    }

    @SuppressLint("SetTextI18n")
    private void capacityMax() {
        if (capacity != MAX_CAPACITY){
            capacity = MAX_CAPACITY;
            tvCapacity.setText("" + capacity);

            if (mode.equals(CRAMER) || mode.equals(GAUSS))
                buildSystemOfEquations();

            if (mode.equals(DETERMINANT))
                buildSimpleMatrix();
        }
    }

    @SuppressLint("SetTextI18n")
    private void capacityMin() {
        if (capacity != MIN_CAPACITY) {
            capacity = MIN_CAPACITY;
            tvCapacity.setText("" + capacity);

            if (mode.equals(CRAMER) || mode.equals(GAUSS))
                buildSystemOfEquations();

            if (mode.equals(DETERMINANT))
                buildSimpleMatrix();

        }

    }

    @SuppressLint("SetTextI18n")
    private void capacityUp() {
        if (capacity != MAX_CAPACITY) {
            capacity++;
            tvCapacity.setText("" + capacity);

            if (mode.equals(CRAMER) || mode.equals(GAUSS))
                buildSystemOfEquations();

            if (mode.equals(DETERMINANT))
                buildSimpleMatrix();
        }
    }

    @SuppressLint("SetTextI18n")
    private void capacityDown() {
        if (capacity != MIN_CAPACITY) {
            capacity--;
            tvCapacity.setText("" + capacity);

            if (mode.equals(CRAMER) || mode.equals(GAUSS))
                buildSystemOfEquations();

            if (mode.equals(DETERMINANT))
                buildSimpleMatrix();
        }
    }

    private void buildSystemOfEquations() {
        matrixLayout.removeAllViewsInLayout();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dp(50), dp(50));

        int id = 0;

        for (int i = 0; i < capacity; i++) {
            LinearLayout column = createColumn();
            matrixLayout.addView(column);

            for (int z = 0; z < capacity; z++) {
                id++;

                EditText et = new EditText(a);

                et.setId(id);
                et.setHint("0");
                et.setHintTextColor(Color.GRAY);
                et.setTextColor(Color.BLACK);
                et.setInputType(InputType.TYPE_CLASS_PHONE);
                et.setGravity(Gravity.CENTER);
                et.setLayoutParams(params);

                column.addView(et);

            }
        }

        LinearLayout column = createColumn();
        matrixLayout.addView(column);

        for (int z = 0; z < capacity; z++) {
            id++;

            EditText et = new EditText(a);

            et.setId(id);
            et.setHint("0");
            et.setHintTextColor(Color.LTGRAY);
            et.setTextColor(Color.WHITE);
            et.setBackgroundResource(R.color.colorPrimary);
            et.setGravity(Gravity.CENTER);
            et.setInputType(InputType.TYPE_CLASS_PHONE);
            et.setLayoutParams(params);

            column.addView(et);

        }
    }

    private void buildSimpleMatrix() {
        matrixLayout.removeAllViewsInLayout();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dp(50), dp(50));

        int id = 0;

        for (int i = 0; i < capacity; i++) {
            LinearLayout column = createColumn();
            matrixLayout.addView(column);

            for (int z = 0; z < capacity; z++) {
                id++;

                EditText et = new EditText(a);

                et.setId(id);
                et.setHint("0");
                et.setHintTextColor(Color.GRAY);
                et.setTextColor(Color.BLACK);
                et.setInputType(InputType.TYPE_CLASS_PHONE);
                et.setGravity(Gravity.CENTER);
                et.setLayoutParams(params);

                column.addView(et);
            }
        }
    }

    private LinearLayout createColumn() {
        LinearLayout column = new LinearLayout(a);
        column.setOrientation(LinearLayout.VERTICAL);
        column.setGravity(Gravity.CENTER);
        return column;
    }

    private void readFreeArray() {
        if (!mode.equals(DETERMINANT)) {
            f.clear();
            int id = capacity * capacity;
            for (int z = 0; z < capacity; z++) {
                id++;

                EditText current = a.findViewById(id);
                String value = current.getText().toString();

                if (value.equals("")) {
                    value = "0";
                }
                f.add(Double.valueOf(value));
            }
        }
    }

    private void readMatrixArray() {
        matrixArray.clear();

        int id = 0;
        for (int i = 0; i < capacity; i++) {
            ArrayList<Double> columnValues = new ArrayList<>();

            for (int z = 0; z < capacity; z++) {
                id++;

                EditText current = a.findViewById(id);
                String value =  current.getText().toString();
                if (value.equals("")) {
                    value = "0";
                }
                columnValues.add(Double.valueOf(value));
            }
            matrixArray.add(columnValues);
        }

        matrix = new Matrix(matrixArray);
    }

    public void setGaussMode() {
        mode = GAUSS;
    }

    public void setCramerMode() {
        mode = CRAMER;
    }

    public void setDeterminantMode() {
        mode = DETERMINANT;
    }
}
