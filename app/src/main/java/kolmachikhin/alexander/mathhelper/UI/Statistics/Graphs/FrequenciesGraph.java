package kolmachikhin.alexander.mathhelper.UI.Statistics.Graphs;

import android.content.Context;

import kolmachikhin.alexander.mathhelper.Core.Statistics.StatisticsAnalyzer;

import static kolmachikhin.alexander.mathhelper.UI.UI.dp;

public class FrequenciesGraph extends Graph {

    private int[] points;

    public FrequenciesGraph(Context c, StatisticsAnalyzer a, int t) {
        super(c, a, t);
        points = analyzer.getFrequencies();
    }

    protected void drawYPoints() {
        for (int i = 0; i < analyzer.getMaxFrequency() + 2; i++) {
            c.drawText("- " + i, dp(10), height - dp(i * STEP + MARGIN), paint);
        }
    }

    protected void drawPolygon() {
        int oldX;
        int oldY = height - dp(MARGIN) - dp((points[0] * STEP));
        int startX = dp(MARGIN + 2);
        int startY = height - dp(MARGIN + 2);
        for (int i = 1; i < points.length; i++) {
            oldX = startX;
            startX += dp((int) (analyzer.getStep() * STEP) + 2);
            startY = height - dp(MARGIN) - dp((points[i] * STEP));

            c.drawLine(oldX, oldY, startX, startY, paint);
            c.drawCircle(oldX, oldY, dp(5), paint);

            oldY = startY;
        }
        c.drawCircle(startX, startY, dp(5), paint);
    }

    protected void drawGistogramm() {
        int oldX;
        int startX = dp(MARGIN);
        int startY;
        for (int i = 0; i < points.length; i++) {
            oldX = startX;
            startX += dp((int) (analyzer.getStep() * STEP) + 2);
            startY = height - dp(MARGIN) - dp((points[i] * STEP));
            drawColumn(oldX, startY, startX, height - dp(MARGIN), paint);
        }
    }

}
