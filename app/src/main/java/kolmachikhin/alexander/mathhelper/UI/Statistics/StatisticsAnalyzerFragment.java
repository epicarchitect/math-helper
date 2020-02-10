package kolmachikhin.alexander.mathhelper.UI.Statistics;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import kolmachikhin.alexander.mathhelper.Core.Statistics.StatisticsAnalyzer;
import kolmachikhin.alexander.mathhelper.R;
import kolmachikhin.alexander.mathhelper.UI.CalcFragment;
import kolmachikhin.alexander.mathhelper.UI.Statistics.Graphs.AccumulatedFrequenciesGraph;
import kolmachikhin.alexander.mathhelper.UI.Statistics.Graphs.AccumulatedParticularsGraph;
import kolmachikhin.alexander.mathhelper.UI.Statistics.Graphs.FrequenciesGraph;
import kolmachikhin.alexander.mathhelper.UI.Statistics.Graphs.Graph;
import kolmachikhin.alexander.mathhelper.UI.Statistics.Graphs.ParticularsGraph;

import static kolmachikhin.alexander.mathhelper.UI.UI.dp;

public class StatisticsAnalyzerFragment extends CalcFragment {
    private EditText etCapacity;

    private LinearLayout listLayout;
    private LinearLayout resultLayout;
    private FrameLayout graphLayout;
    private RadioGroup radioTypeGraph;
    private Spinner spinnerGraphs;

    private ArrayList<Double> list = new ArrayList<>();

    public static final int FREQUENCIES = 0;
    public static final int ACCUMULATED_FREQUENCIES = 1;
    public static final int PARTICULARS = 2;
    public static final int ACCUMULATED_PARTICULARS  = 3;
    private int capacity = 0;

    private StatisticsAnalyzer analyzer;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.statistics_analyzer_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        etCapacity = v.findViewById(R.id.et_capacity);
        listLayout = v.findViewById(R.id.list);
        resultLayout = v.findViewById(R.id.result_layout);
        graphLayout = v.findViewById(R.id.graph);
        spinnerGraphs = v.findViewById(R.id.spinner_graphs);
        radioTypeGraph = v.findViewById(R.id.radio_type_graph);

        etCapacity.setOnKeyListener((v1, keyCode, event) -> {
            setCapacity();
            return false;
        });

        radioTypeGraph.setOnCheckedChangeListener((group, checkedId) -> setGraph(spinnerGraphs.getSelectedItemPosition(), checkedId));

        spinnerGraphs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setGraph(position, radioTypeGraph.getCheckedRadioButtonId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private EditText getETById(int id) {
        return a.findViewById(id);
    }

    private double getValueById(int id) {
        EditText et = getETById(id);
        String value =  et.getText().toString();
        if (value.equals("")) value = "0";
        return Double.valueOf(value);
    }

    @SuppressLint("SetTextI18n")
    private void setCapacity() {
        try {
            listLayout.removeAllViews();

            capacity = Integer.parseInt(etCapacity.getText().toString());

            if (capacity >= 50) {
                capacity = 50;
                etCapacity.setText(capacity + "");
            }

            LinearLayout layout = buildLinearLayout();
            layout.setOrientation(LinearLayout.HORIZONTAL);
            for (int i = 0; i < capacity; i++) {
                if (i % 5 == 0) {
                    listLayout.addView(layout);
                    layout = buildLinearLayout();
                    layout.setOrientation(LinearLayout.HORIZONTAL);
                }
                layout.addView(buildET(i));

                if (i == capacity - 1) {
                    listLayout.addView(layout);
                }

            }
            listLayout.addView(layout);
        } catch (Exception ignored) {}
    }

    @SuppressLint("SetTextI18n")
    private EditText buildET(int id) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dp(50), dp(50));
        EditText et = new EditText(a);
        et.setId(id);
        et.setHint((id + 1) + "");
        et.setTextColor(Color.BLACK);
        et.setInputType(InputType.TYPE_CLASS_PHONE);
        et.setGravity(Gravity.CENTER);
        et.setLayoutParams(params);
        return et;
    }

    @Override
    protected void getValues() {
        list.clear();
        for (int i = 0; i < capacity; i++) {
            list.add(getValueById(i));
        }
        analyzer = new StatisticsAnalyzer(list);
    }

    private LinearLayout buildLinearLayout() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout layout = new LinearLayout(a);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(dp(8), dp(8), dp(8), dp(8));
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(params);
        return layout;
    }

    private TextView buildTextView(String text) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TextView tv = new TextView(a);
        tv.setGravity(Gravity.CENTER);
        tv.setText(text);
        tv.setLayoutParams(params);
        tv.setTextColor(ContextCompat.getColor(context, R.color.colorDarkText));
        return tv;
    }

    private void setResultLayout(StatisticsAnalyzer a) {
        resultLayout.removeAllViews();

        String[] titles = {
                "Интервалы",
                "Середины интервалов",
                "Частоты",
                "Частости",
                "Накопленные частоты",
                "Накопленные частости"
        };

        LinearLayout layout = buildLinearLayout();
        layout.addView(buildTextView(titles[0]));
        for (int i = 0; i < a.getCountIntervals(); i++) {
            layout.addView(buildTextView(a.getIntervals()[i].toString() + ""));
        }
        resultLayout.addView(layout);

        layout = buildLinearLayout();
        layout.addView(buildTextView(titles[1]));
        for (int i = 0; i < a.getCountIntervals(); i++) {
            layout.addView(buildTextView(a.getCenterPoints()[i] + ""));

        }
        resultLayout.addView(layout);

        layout = buildLinearLayout();
        layout.addView(buildTextView(titles[2]));
        for (int i = 0; i < a.getCountIntervals(); i++) {
            layout.addView(buildTextView(a.getFrequencies()[i] + ""));

        }
        resultLayout.addView(layout);

        layout = buildLinearLayout();
        layout.addView(buildTextView(titles[3]));
        for (int i = 0; i < a.getCountIntervals(); i++) {
            layout.addView(buildTextView(a.getParticulars()[i] + ""));

        }
        resultLayout.addView(layout);

        layout = buildLinearLayout();
        layout.addView(buildTextView(titles[4]));
        for (int i = 0; i < a.getCountIntervals(); i++) {
            layout.addView(buildTextView(a.getAccumulatedFrequencies()[i] + ""));
        }
        resultLayout.addView(layout);

        layout = buildLinearLayout();
        layout.addView(buildTextView(titles[5]));
        for (int i = 0; i < a.getCountIntervals(); i++) {
            layout.addView(buildTextView(a.getAccumulatedParticulars()[i] + ""));

        }
        resultLayout.addView(layout);
    }

    public void setGraph(int dataType, int graphType) {
        graphLayout.removeAllViews();

        Graph graph = null;
        int width = dp((int) ((analyzer.getMax() - analyzer.getMin() + 3) * Graph.STEP + Graph.MARGIN));
        int height = dp((analyzer.getMaxAccumulatedFrequency() + 2) * Graph.STEP + Graph.MARGIN);

        if (graphType == R.id.gistogramm) graphType = Graph.GISTOGRAMM;
        if (graphType == R.id.polygon) graphType = Graph.POLYGON;

        if (dataType == FREQUENCIES) {
            graph = new FrequenciesGraph(context, analyzer, graphType);
            height = dp((analyzer.getMaxFrequency() + 2) * Graph.STEP + Graph.MARGIN);
        }

        if (dataType == ACCUMULATED_FREQUENCIES) {
            graph = new AccumulatedFrequenciesGraph(context, analyzer, graphType);
            height = dp((analyzer.getMaxAccumulatedFrequency() + 2) * Graph.STEP + Graph.MARGIN);
        }

        if (dataType == PARTICULARS) {
            graph = new ParticularsGraph(context, analyzer, graphType);
            height = dp((int) (analyzer.getMaxParticular() * 10 + 3)) * Graph.STEP + Graph.MARGIN;
        }

        if (dataType == ACCUMULATED_PARTICULARS) {
            graph = new AccumulatedParticularsGraph(context, analyzer, graphType);
            height = dp((int) (analyzer.getMaxAccumulatedParticular() * 10 + 3)) * Graph.STEP + Graph.MARGIN;
        }

        if (graph != null) {
            graphLayout.addView(graph, width, height);
        }
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void calculate() {
        setResultLayout(analyzer);

        radioTypeGraph.setVisibility(View.VISIBLE);
        radioTypeGraph.check(R.id.gistogramm);
        spinnerGraphs.setVisibility(View.VISIBLE);
        spinnerGraphs.setSelection(0);

        String res = "Среднее арифметическое: " + String.format("%.10f", analyzer.getAverage())
                + "\n Мода: " + analyzer.getFashionInterval().toString()
                + "\n Медиана: " + String.format("%.10f", analyzer.getMedian())
                + "\n Выборочная дисперсия: " + String.format("%.10f", analyzer.getDispersion())
                + "\n Среднеквадратичное отклонение: " + String.format("%.10f", analyzer.getDeviation());

        setTvResult(res);
    }
}
