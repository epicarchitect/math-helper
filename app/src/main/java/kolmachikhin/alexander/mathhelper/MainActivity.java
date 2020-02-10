package kolmachikhin.alexander.mathhelper;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import kolmachikhin.alexander.mathhelper.UI.Matrix.MatrixFragment;
import kolmachikhin.alexander.mathhelper.UI.Statistics.StatisticsAnalyzerFragment;
import kolmachikhin.alexander.mathhelper.UI.TheoryOfChances.BernoulliFragment;
import kolmachikhin.alexander.mathhelper.UI.TheoryOfChances.IntegralLaplaceFragment;
import kolmachikhin.alexander.mathhelper.UI.TheoryOfChances.LocalLaplaceFragment;
import kolmachikhin.alexander.mathhelper.UI.Combinatorics.CombinationFragment;
import kolmachikhin.alexander.mathhelper.UI.Combinatorics.DistributionFragment;
import kolmachikhin.alexander.mathhelper.UI.Combinatorics.FactorialFragment;
import kolmachikhin.alexander.mathhelper.UI.TheoryOfChances.MathWaitFragment;
import kolmachikhin.alexander.mathhelper.UI.UI;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        UI.init(this);

        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.factorial) {
                toolbar.setTitle(R.string.factorial);
                new FactorialFragment().open();
            }

            if (id == R.id.distribution) {
                toolbar.setTitle(R.string.distribution);
                new DistributionFragment().open();
            }

            if (id == R.id.combination) {
                toolbar.setTitle(R.string.combination);
                new CombinationFragment().open();
            }

            if (id == R.id.bernoulli) {
                toolbar.setTitle(R.string.bernoulli);
                new BernoulliFragment().open();
            }

            if (id == R.id.local_laplace) {
                toolbar.setTitle(R.string.local_laplace);
                new LocalLaplaceFragment().open();
            }

            if (id == R.id.integral_laplace) {
                toolbar.setTitle(R.string.integral_laplace);
                new IntegralLaplaceFragment().open();
            }

            if (id == R.id.math_wait) {
                toolbar.setTitle(R.string.math_wait);
                new MathWaitFragment().open();
            }

            if (id == R.id.statistics_analyzer) {
                toolbar.setTitle(R.string.statistics_analyzer);
                new StatisticsAnalyzerFragment().open();
            }

            if (id == R.id.determinant) {
                toolbar.setTitle(R.string.determinant);
                MatrixFragment matrixFragment = new MatrixFragment();
                matrixFragment.setDeterminantMode();
                matrixFragment.open();
            }

            if (id == R.id.gauss) {
                toolbar.setTitle(R.string.gauss);
                MatrixFragment matrixFragment = new MatrixFragment();
                matrixFragment.setGaussMode();
                matrixFragment.open();
            }

            if (id == R.id.cramer) {
                toolbar.setTitle(R.string.cramer);
                MatrixFragment matrixFragment = new MatrixFragment();
                matrixFragment.setCramerMode();
                matrixFragment.open();
            }

            UI.hideKeyboard();
            drawer.closeDrawer(GravityCompat.START);

            return true;
        });

        drawer.openDrawer(GravityCompat.START);

    }

}
