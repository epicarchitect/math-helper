package kolmachikhin.alexander.mathhelper.Core.TheoryOfChancesLogic;

public class LaplaceLogic {

    public static double funLocalLaplace(int m, int n, double p) {
        double q = 1 - p;
        double x = Math.abs((m - n * p) / Math.sqrt(n * p * q));
        return fi(x);
    }

    public static double fi(double x) {
        return (1.0 / Math.sqrt(2 * Math.PI)) * Math.exp(-(x * x) / 2);
    }

    public static double localLaplace(int m, int n, double p) {
        double q = 1 - p;
        return (1.0 / Math.sqrt(n * p * q)) * funLocalLaplace(m, n, p);
    }

    public static double funIntegralLaplace(double b) {
        int n = 1000;
        double h = b / n;

        double result = 0;

        for (int i = 0; i < n; i++) {
            result += (h / 2) * (fi(i * h) + fi((i + 1) * h));
        }

        return result;

    }

    public static double integralLaplace(int a, int b, int n, double p) {
        double q = 1 - p;

        double arg1 = (b - n * p) / Math.sqrt(n * p * q);
        double arg2 = (a - n * p) / Math.sqrt(n * p * q);

        return funIntegralLaplace(arg1) - funIntegralLaplace(arg2);
    }

}
