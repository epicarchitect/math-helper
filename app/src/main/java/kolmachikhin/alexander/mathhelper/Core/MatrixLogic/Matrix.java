package kolmachikhin.alexander.mathhelper.Core.MatrixLogic;

import android.util.Log;

import java.util.ArrayList;

public class Matrix {

    private ArrayList<ArrayList<Double>> m = new ArrayList<>();

    public Matrix(ArrayList<ArrayList<Double>> a) {
        setMatrixArray(a);
    }

    public int getColumnCount() {
        return m.size();
    }

    public int getRowCount() {
        return m.get(0).size();
    }

    public ArrayList<ArrayList<Double>> getMatrixArray() {
        return m;
    }

    public void setMatrixArray(ArrayList<ArrayList<Double>> a) {
        m = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            m.add(new ArrayList<>());
            for (int z = 0; z < a.get(i).size(); z++) {
                m.get(i).add(a.get(i).get(z));
            }
        }
    }

    public double get(int column, int row) {
        return m.get(column).get(row);
    }

    public void set(int column, int row, double value) {
        m.get(column).set(row, value);
    }

    public void setColumn(int index, ArrayList<Double> column) {
        m.set(index, column);
    }

    public void addColumn(ArrayList<Double> column) {
        m.add(column);
    }


    public void removeColumn(int column) {
        m.remove(column);
    }

    public void removeRow(int row) {
        for (int i = 0; i < m.size(); i++)
            m.get(i).remove(row);
    }

    public void swapRows(int row1, int row2) {
        for (int i = 0; i < m.size(); i++) {
            double tmp = get(i, row1);
            set(i, row1, get(i, row2));
            set(i, row2, tmp);
        }
    }

    @Override
    public String toString() {
        String s = " \n";

        for (int i = 0; i < getRowCount(); i++) {
            for (int z = 0; z < getColumnCount(); z++) {
                s += get(z, i) + " ";
            }
            s += "\n";
        }

        s += "\n";

        return s;
    }

}
