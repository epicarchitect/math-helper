package kolmachikhin.alexander.mathhelper.Core.CombinatoricsLogic;

public class ComboLogic {

    public static double A(int m, int n) {
        return factorial(n) / factorial(n - m);
    }

    public static double C(int m, int n) {
        return factorial(n) / (factorial(m) * factorial(n - m));
    }

    public static double factorial(int n) {
        double res = 1;

        for (int i = 1; i <= n; i++) res *= i;

        return res;
    }
}
