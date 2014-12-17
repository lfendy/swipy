package cheesy.ultra.mundane.trophies.swipy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import cheesy.ultra.mundane.trophies.swipy.questions.HardcodedQs;
import cheesy.ultra.mundane.trophies.swipy.questions.State;
import cheesy.ultra.mundane.trophies.swipy.util.OnSwipeTouchListener;
import cheesy.ultra.mundane.trophies.swipy.util.SystemUiHider;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class QuestionActivity extends Activity {
    public static String CURRENT_QUESTION = "current question";
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;


    private Point windowSize;
    private Point layoutSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_question);

        final View contentView = findViewById(R.id.fullscreen_content);

        final State.Id currentQuestionId = new State.Id((String) getIntent().getSerializableExtra(CURRENT_QUESTION));
        String questionText = HardcodedQs.getStateFromId(currentQuestionId).getText();
        TextView tv = (TextView) contentView;
        tv.setText(questionText);

        Display display = getWindowManager().getDefaultDisplay();
        windowSize = new Point();
        display.getSize(windowSize);

        layoutSize = new Point((int)(windowSize.x * (8f / 10f)), (int)(windowSize.y * (8f / 10f)));
        tv.setWidth(layoutSize.x);
        tv.setHeight(layoutSize.y);

        resetTextView(tv);

        tv.setOnTouchListener(new View.OnTouchListener() {
            int touchStartX, touchStartY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int touchX = (int)event.getRawX();
                int touchY = (int)event.getRawY();

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        touchStartX = touchX;
                        touchStartY = touchY;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        moveTextView(v, touchX - touchStartX, touchY - touchStartY);
                        break;

                    case MotionEvent.ACTION_UP:
                        if(isYes(touchX)){
                            startNextQuestionActivity(HardcodedQs.getAfterYes(currentQuestionId));
                            finish();
                        }else if (isNo(touchX)){
                            startNextQuestionActivity(HardcodedQs.getAfterNo(currentQuestionId));
                            finish();
                        }else{
                            resetTextView(v);
                        }

                        break;
                }
                return true;
            }
        });
    }

    private void resetTextView(View tv) {
        tv.setX((windowSize.x - layoutSize.x) / 2);
        tv.setY((windowSize.y - layoutSize.y) / 2);
    }

    private void moveTextView(View v, int x_coord, int y_coord) {
        v.setX((windowSize.x - layoutSize.x) / 2 + x_coord);
        v.setY((windowSize.y - layoutSize.y) / 2 + y_coord);

    }

    private boolean isYes(int deltaX) {
        return deltaX > windowSize.x * .75;
    }

    private boolean isNo(int deltaX) {
        return deltaX < windowSize.x * .25;
    }

    private void startNextQuestionActivity(State next) {
        if(next.getType() == State.Type.Fail){
            Intent intent = new Intent(this, EndActivity.class);
            startActivity(intent);
        } else if (next.getType() == State.Type.Trophy) {
            Intent intent = new Intent(this, TrophyActivity.class);
            intent.putExtra(TrophyActivity.CURRENT_STATE, next.getId().getInnerId());
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, QuestionActivity.class);
            intent.putExtra(QuestionActivity.CURRENT_QUESTION, next.getId().getInnerId());
            startActivity(intent);
        }
    }


}
