package kolmachikhin.alexander.mathhelper.Core.MatrixLogic;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.ArrayList;

public class Gauss {

    private Matrix m;
    private ArrayList<Double> resultArray = new ArrayList<>();
    private boolean isError = false;

    public Gauss(Matrix m, ArrayList<Double> f) {
        this.m = m;
        this.m.addColumn(f);
        setTriangularMatrix();
        setResultArray();

        Log.d("matr", m.toString());
    }

    @SuppressLint("DefaultLocale")
    public String getTextResult() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < resultArray.size(); i++) {
            double x = resultArray.get(i);
            result.append("x").append(resultArray.size() - i).append(" = ").append(String.format("%.10f", x)).append("\n");
        }

        if (isError) {
            result.append("\n").append("При вычислениях произошла ошибка деления на 0, попробуйте поменять некоторые строки местами");
        }

        return result.toString();
    }


    private void setResultArray() {
        resultArray.add(
                findX(m.get(m.getColumnCount() - 2, m.getColumnCount() - 2),
                        m.get(m.getColumnCount() - 1, m.getColumnCount()-2)
                )
        );

        for (int i = m.getColumnCount() - 3; i >= 0; i--) {
            resultArray.add(findX(m.get(i, i), findB(i)));
        }
    }

    private double findB(int row) {
        double b = m.get(m.getColumnCount() - 1, row);

        for (int i = 0; i < resultArray.size(); i++)
            b -= m.get(m.getColumnCount() - i - 2, row) * resultArray.get(i);

        return b;
    }

    private double findX(double a, double b) {
        return a == 0 ? 0 : b / a;
    }

    private void setTriangularMatrix() {
        for (int rowTop = 0; rowTop < m.getColumnCount() - 1; rowTop++) {
            for (int rowBottom = 1; rowBottom < m.getColumnCount() - 1; rowBottom++) {
                sumRowsWithCoefficient(rowTop, rowBottom);
            }
        }
    }

    private void sumRowsWithCoefficient(int rowTop, int rowBottom) {
        if (rowTop < rowBottom) {

            double c = findCoefficient(m.get(rowTop, rowTop), m.get(rowTop, rowBottom));

            for (int i = 0; i < m.getColumnCount(); i++) {
                double sum = m.get(i, rowTop) * c + m.get(i, rowBottom);
                m.set(i, rowBottom, sum);
            }

        }
    }

    private double findCoefficient(double a, double b) {
        if (a == 0.0) isError = true;
        return a == 0.0 ? -b / 0.0000000000000001 : -b / a;
    }
}