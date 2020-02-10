package kolmachikhin.alexander.mathhelper.Core.TheoryOfChancesLogic;

import java.util.ArrayList;

public class MathWaitLogic {

    public static double M(ArrayList<Double> xList, ArrayList<Double> pList) {
        float res = 0;

        for (int i = 0; i < xList.size(); i++)
            res += xList.get(i) * pList.get(i);

        return res;
    }

    public static double Mx2(ArrayList<Double> xList, ArrayList<Double> pList) {
        float res = 0;

        for (int i = 0; i < xList.size(); i++)
            res += Math.pow(xList.get(i), 2) * pList.get(i);

        return res;
    }

    public static double D(ArrayList<Double> xList, ArrayList<Double> pList) {
        return (Mx2(xList, pList) - Math.pow(M(xList, pList), 2));
    }

    public static double S(ArrayList<Double> xList, ArrayList<Double> pList) {
        return Math.sqrt(Math.abs(D(xList, pList)));
    }

}
