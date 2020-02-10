package kolmachikhin.alexander.mathhelper.Core.MatrixLogic;

public class Determinant {

    private static Matrix minor(Matrix m, int column) {
        Matrix minor = new Matrix(m.getMatrixArray());
        minor.removeRow(0);
        minor.removeColumn(column);
        return minor;
    }

    public static double determinant(Matrix m) {
        if (m.getColumnCount() == 1) return m.get(0, 0);

        double d = 0;

        for (int i = 0; i < m.getColumnCount(); i++)
            d += minusOrPlus(i) * m.get(i, 0) * determinant(minor(m, i));

        return d;
    }

    private static int minusOrPlus(int i) {
        return i % 2 == 0 ? 1 : -1;
    }

}
