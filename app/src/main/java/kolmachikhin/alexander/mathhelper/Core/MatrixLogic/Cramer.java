package kolmachikhin.alexander.mathhelper.Core.MatrixLogic;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.ArrayList;

import static kolmachikhin.alexander.mathhelper.Core.MatrixLogic.Determinant.determinant;

public class Cramer {

    private Matrix m;
    private ArrayList<Double> f;
    private ArrayList<Double> resultArray = new ArrayList<>();
    private double determinant;

    public Cramer(Matrix m, ArrayList<Double> f) {
        this.m = m;
        this.f = f;
        this.determinant = Determinant.determinant(m);
        calculate();
    }

    @SuppressLint("DefaultLocale")
    public void calculate() {
        if (determinant != 0) {
            for (int i = 0; i < m.getColumnCount(); i++) {
                resultArray.add(determinant(varMatrix(i)) / determinant);
            }
        }
    }

    private Matrix varMatrix(int c) {
        Matrix varMatrix = new Matrix(m.getMatrixArray());
        varMatrix.setColumn(c, f);
        return varMatrix;
    }

    @SuppressLint("DefaultLocale")
    public String getTextResult() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < resultArray.size(); i++) {
            result
                    .append("x")
                    .append(i + 1)
                    .append(" = ")
                    .append(String.format("%.10f", resultArray.get(i)))
                    .append("\n");
        }

        if (determinant == 0) {
            result.append("Определитель равен 0, решаете методом Гаусса");
        }

        return result.toString();
    }
}
