package kolmachikhin.alexander.mathhelper.UI.Statistics.Graphs;

import android.content.Context;

import kolmachikhin.alexander.mathhelper.Core.Statistics.StatisticsAnalyzer;

import static kolmachikhin.alexander.mathhelper.UI.UI.dp;

public class ParticularsGraph extends Graph {

    private double[] points;

    public ParticularsGraph(Context c, StatisticsAnalyzer a, int t) {
        super(c, a, t);
        points = a.getParticulars();
    }

    @Override
    protected void drawYPoints() {
        for (int i = 0; i < analyzer.getMaxParticular() * 10 + 2; i++) {
            c.drawText("- " + ((float) i / 10), dp(10), height - dp(i * STEP + MARGIN), paint);
        }
    }

    @Override
    protected void drawPolygon() {
        int oldX;
        int oldY = height - dp(MARGIN) - dp((int) (points[0] * 10 * STEP));
        int startX = dp(MARGIN + 2);
        int startY = height - dp(MARGIN + 2);
        for (int i = 1; i < points.length; i++) {
            oldX = startX;
            startX += dp((int) (analyzer.getStep() * STEP) + 2);
            startY = height - dp(MARGIN) - dp((int) (points[i] * 10 * STEP));

            c.drawLine(oldX, oldY, startX, startY, paint);
            c.drawCircle(oldX, oldY, dp(5), paint);

            oldY = startY;
        }
        c.drawCircle(startX, startY, dp(5), paint);
    }

    @Override
    protected void drawGistogramm() {
        int oldX;
        int startX = dp(MARGIN);
        int startY;
        for (int i = 0; i < points.length; i++) {
            oldX = startX;
            startX += dp((int) (analyzer.getStep() * STEP) + 2);
            startY = height - dp(MARGIN) - dp((int) (points[i] * 10 * STEP));
            drawColumn(oldX, startY, startX, height - dp(MARGIN), paint);
        }
    }

}
