package kolmachikhin.alexander.mathhelper.Core.TheoryOfChancesLogic;

import kolmachikhin.alexander.mathhelper.Core.CombinatoricsLogic.ComboLogic;

public class BernoulliLogic {

    public static double bernoulli(int m, int n, double p) {
        return (ComboLogic.C(m, n) * Math.pow(p, m) * Math.pow(1 - p, n - m));
    }

}
