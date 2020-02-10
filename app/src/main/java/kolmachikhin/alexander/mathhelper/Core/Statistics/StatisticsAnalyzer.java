package kolmachikhin.alexander.mathhelper.Core.Statistics;

import java.util.ArrayList;
import java.util.Collections;

public class StatisticsAnalyzer {
    private ArrayList<Double> list;
    private Interval[] intervals;
    private int[] frequencies; // частоты
    private int[] accumulatedFrequencies;
    private double[] centerPoints;
    private double[] particulars; // частости
    private double[] accumulatedParticulars;
    private double step; // h
    private int countIntervals; // k
    private double min;
    private double max;
    private int n;
    private double average;
    private Interval fashionInterval; // мода
    private double median; // мода
    private double dispersion; // выборочная дисперсия
    private double deviation; // среднеквадратичное отклонение

    public StatisticsAnalyzer(ArrayList<Double> list) {
        Collections.sort(list);
        this.list = list;
        this.n = list.size();

        min = Collections.min(list);
        max = Collections.max(list);

        setCountIntervals();
        setStep();
        setIntervals();
        setFrequencies();
        setCenterPoints();
        setParticulars();
        setAccumulatedFrequencies();
        setAccumulatedParticulars();
        setAverage();
        setFashionInterval();
        setMedian();
        setDispersion();
        setDeviation();
    }

    public ArrayList<Double> getList() {
        return list;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    private void setCountIntervals() {
        countIntervals = (int) Math.round(1 + 3.322 * Math.log10(n));
    }

    public int getCountIntervals() {
        return countIntervals;
    }

    private void setStep() {
        step = ((max - min) / countIntervals) + 0.1; // + 0.1 чтоб навернека
    }

    public double getStep() {
        return step;
    }

    private void setIntervals() {
        intervals = new Interval[countIntervals];
        double start = min;
        for (int i = 0; i < countIntervals; i++) {
            intervals[i] = new Interval(start, start + step);
            start += step;
        }
    }

    public Interval[] getIntervals() {
        return intervals;
    }

    private void setFrequencies() {
        frequencies = new int[intervals.length];

        for (double v : list) {
            for (int i = 0; i < intervals.length; i++) {
                if (intervals[i].inInterval(v)) {
                    frequencies[i]++;
                }
            }
        }
    }

    public int[] getFrequencies() {
        return frequencies;
    }

    public int getMaxAccumulatedFrequency() {
        int max = 0;
        for (int i : accumulatedFrequencies)
            if (i > max) max = i;
        return max;
    }

    public int getMaxFrequency() {
        int max = 0;
        for (int i : frequencies)
            if (i > max) max = i;
        return max;
    }

    public double getMaxAccumulatedParticular() {
        double max = 0;
        for (double i : accumulatedParticulars)
            if (i > max) max = i;
        return max;
    }

    public double getMaxParticular() {
        double max = 0;
        for (double i : particulars)
            if (i > max) max = i;
        return max;
    }

    private void setCenterPoints() {
        centerPoints = new double[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            centerPoints[i] = intervals[i].getCenter();
        }
    }

    public double[] getCenterPoints() {
        return centerPoints;
    }

    private void setParticulars() {
        particulars = new double[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            particulars[i] = (double) frequencies[i] / n;
        }
    }

    public double[] getParticulars() {
        return particulars;
    }

    private void setAccumulatedFrequencies() {
        this.accumulatedFrequencies = new int[intervals.length];
        accumulatedFrequencies[0] = frequencies[0];
        for (int i = 1; i < intervals.length; i++) {
            accumulatedFrequencies[i] = frequencies[i] + accumulatedFrequencies[i - 1];
        }
    }

    public int[] getAccumulatedFrequencies() {
        return accumulatedFrequencies;
    }

    private void setAccumulatedParticulars() {
        this.accumulatedParticulars = new double[intervals.length];
        accumulatedParticulars[0] = particulars[0];
        for (int i = 1; i < intervals.length; i++) {
            accumulatedParticulars[i] = particulars[i] + accumulatedParticulars[i - 1];
        }
    }

    public double[] getAccumulatedParticulars() {
        return accumulatedParticulars;
    }

    private void setAverage() {
        double sum = 0;
        for (double v : list) sum += v;
        average = sum / n;
    }

    public double getAverage() {
        return average;
    }

    public double getMedian() {
        return median;
    }

    private void setMedian() {
        if (n % 2 != 0) {
            median = list.get((n + 1) / 2);
        } else {
            median = (list.get((n - 2) / 2) + list.get(n / 2)) / 2;
        }
    }

    public Interval getFashionInterval() {
        return fashionInterval;
    }

    private void setFashionInterval() {
        int max = 0;
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > frequencies[max]) {
                max = i;
            }
        }
        fashionInterval = new Interval(intervals[max]);
    }

    public double getDispersion() {
        return dispersion;
    }

    private void setDispersion() {
        double sum = 0;
        for (int i = 0; i < countIntervals; i++) {
            sum += Math.pow(centerPoints[i] - average, 2);
        }
        dispersion = ((double) 1 / (n - 1)) * sum;
    }

    public double getDeviation() {
        return deviation;
    }

    private void setDeviation() {
        deviation = Math.sqrt(dispersion);
    }

}
