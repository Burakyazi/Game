package com.bilgeadam.xox.Actions;

import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import com.bilgeadam.xox.Utils.EnumSupport;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * this class contains button animations
 */
public class Animations {

    private static final float ALPHA_Value = 0.02F;
    private static final long DEFAULT_ANIMATION_LENGTH = 1000L;


    /**
     * Sends the button out of view randomly.
     * @param button Target Button
     * @param duration Animation duration
     * @param windowManager Current window
     */
    @RequiresApi(api = Build.VERSION_CODES.R)
    public void sendButtonRandomly(@NotNull final Button button,@NotNull Optional<Long> duration, @NotNull WindowManager windowManager){
        // Get current window size
        Rect bounds = windowManager.getCurrentWindowMetrics().getBounds();

        //Get button centers current location
        int[] currentButtonLocations = new int[2];
        button.getLocationInWindow(currentButtonLocations);

        // Get button dimensions
        int xDiff = currentButtonLocations[0] + button.getWidth()/2;
        int yDiff = currentButtonLocations[1] + button.getHeight()/2;

        switch ((Direction) EnumSupport.createRandomValue(Direction.values())){
            case Up:
                button.animate().translationXBy(bounds.top - yDiff).alpha(ALPHA_Value).setDuration(duration.orElse(DEFAULT_ANIMATION_LENGTH));
                break;
            case Down:
                button.animate().translationXBy(bounds.bottom - yDiff).alpha(ALPHA_Value).setDuration(duration.orElse(DEFAULT_ANIMATION_LENGTH));
                break;
            case Left:
                button.animate().translationXBy(bounds.left - xDiff).alpha(ALPHA_Value).setDuration(duration.orElse(DEFAULT_ANIMATION_LENGTH));
                break;
            case Right:
                button.animate().translationXBy(bounds.right - xDiff).alpha(ALPHA_Value).setDuration(duration.orElse(DEFAULT_ANIMATION_LENGTH));
                break;
        }
    }
    /**
     * Animates and removes the text
     * @param text Target text
     * @param duration Duration of the animation
     */
    public void removeText(@NotNull TextView text, Long duration) {

        float rotateSize = (float) duration/1000 * 360;
        text.animate().rotationBy(rotateSize).alpha(ALPHA_Value).setDuration(duration);
        setWidgetVisibilityWithDelay(text,duration,View.GONE);
    }

    /**
     *
     * @param targetView View to be removed
     * @param delay Delay of the removal
     * @param visibility Duration of the animation
     */
    public void setWidgetVisibilityWithDelay(final View targetView, Long delay, int visibility){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() ->
                targetView.setVisibility(visibility),delay);
    }

    /**
     * Drop the image from the top
     * @param image Current Image view
     * @param imageId The image itself
     * @param duration Duration of the animation
     */
    public void dropImage(@NotNull ImageView image, int imageId, long duration){

        // Get location of image view
        int[] currentLocation = new int[2];
        image.getLocationInWindow(currentLocation);

        // Set initial image case
        image.setImageResource(Integer.parseInt(String.valueOf(imageId)));
        image.setAlpha(ALPHA_Value);
        image.setTranslationY(-image.getHeight());

        image.animate().translationYBy(image.getHeight()).alpha(1.0F).setDuration(duration);

    }
}
