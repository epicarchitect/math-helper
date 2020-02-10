package kolmachikhin.alexander.mathhelper.Core.Statistics;

public class Interval {
    private double start;
    private double end;

    public Interval(double start, double end) {
        this.start = start;
        this.end = end;
    }

    public Interval(Interval i) {
        start = i.getStart();
        end = i.getEnd();
    }

    public double getStart() {
        return start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    public boolean inInterval(double v) {
        return v >= start && v <= end;
    }

    public double getCenter() {
        return end - ((end - start) / 2);
    }

    @Override
    public String toString() {
        return start + " - " + end;
    }
}
