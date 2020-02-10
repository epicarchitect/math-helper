package kolmachikhin.alexander.mathhelper.Core;

import android.content.Context;
import android.widget.Toast;

public class Core {

    public static void say(Context c, String text) {
        Toast.makeText(c, text, Toast.LENGTH_SHORT).show();
    }

}
