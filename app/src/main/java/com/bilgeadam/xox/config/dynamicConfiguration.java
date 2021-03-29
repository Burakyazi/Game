package com.bilgeadam.xox.config;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import androidx.constraintlayout.widget.ConstraintLayout;
import org.jetbrains.annotations.NotNull;

public class dynamicConfiguration {

    /**
     * Configuration
     * @param layout Layout Where image resides
     */
    public static void setImageSize(@NotNull TableLayout layout){

        TableRow row,cell;
        ImageView img;

        //layout.getDrawableState();
        int dimension = Math.floorDiv(Math.min(layout.getHeight(),layout.getWidth()),layout.getChildCount());

        for (int i = 0; i < layout.getChildCount();i++) {
            row = (TableRow) layout.getChildAt(i);
            for (int j = 0;j < row.getChildCount();j++){
                cell = (TableRow) row.getChildAt(j);

                img= (ImageView) cell.getChildAt(0);
                img.setLayoutParams(new TableRow.LayoutParams(dimension,dimension));
                Log.println(Log.INFO,img.getTag().toString(),"Image size is set.");

            }
        }
        layout.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layout.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
    }
}
