package kolmachikhin.alexander.mathhelper.UI.Statistics.Graphs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.view.View;

import kolmachikhin.alexander.mathhelper.Core.Statistics.StatisticsAnalyzer;
import kolmachikhin.alexander.mathhelper.R;

import static kolmachikhin.alexander.mathhelper.UI.UI.dp;

public abstract class Graph extends View {

    protected Context context;
    protected int width; // in dp
    protected int height; // in dp
    protected StatisticsAnalyzer analyzer;
    protected Paint paint;
    protected Canvas c;
    protected int type;

    public static final int STEP = 25;
    public static final int STROKE_WIDTH = 5;
    public static final int TEXT_SIZE = dp(12);
    public static final int MARGIN = 40;
    public static final int POLYGON = 0;
    public static final int GISTOGRAMM = 1;

    public Graph(Context context, StatisticsAnalyzer analyzer, int type) {
        super(context);
        this.context = context;
        this.analyzer = analyzer;
        this.type = type;
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas c) {
        this.c = c;
        this.width = getWidth();
        this.height = getHeight();

        paint.setColor(ContextCompat.getColor(context, R.color.colorDarkText));
        paint.setStrokeWidth(STROKE_WIDTH);
        paint.setTextSize(TEXT_SIZE);

        drawFrame();
        drawXPoints();
        drawYPoints();


        if (type == POLYGON) {
            paint.setColor(ContextCompat.getColor(context, R.color.colorBlue));
            drawPolygon();
        }

        if (type == GISTOGRAMM) {
            paint.setColor(ContextCompat.getColor(context, R.color.colorGreen));
            drawGistogramm();
        }

    }

    private void drawFrame() {
        c.drawLine(1, 1, 1, height - 1, paint);
        c.drawLine(1, 1, width - 1, 1, paint);
        c.drawLine(1, height - 1, width, height - 1, paint);
        c.drawLine(width - 1, 1, width - 1, height - 1, paint);
    }

    protected void drawXPoints() {
        int min = (int) analyzer.getMin();
        int max = (int) analyzer.getMax();
        for (int i = min; i < max + 3; i++) {
            c.drawText("|", dp((i - min) * STEP + MARGIN), height - dp(30), paint);
            c.drawText("" + i, dp((i - min) * STEP + MARGIN), height - dp(10), paint);
        }
    }

    protected void drawYPoints() {
        for (int i = 0; i < analyzer.getMaxAccumulatedFrequency() + 5; i++) {
            c.drawText("- " + i, dp(10), height - dp(i * STEP + MARGIN), paint);
        }
    }

    protected abstract void drawPolygon();

    protected abstract void drawGistogramm();

    protected void drawColumn(int x, int y, int width, int height, Paint p) {
        c.drawRect(x, y, width, height, p);
    }


}
