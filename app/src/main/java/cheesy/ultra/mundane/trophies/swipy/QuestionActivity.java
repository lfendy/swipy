package cheesy.ultra.mundane.trophies.swipy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_question);

        final View contentView = findViewById(R.id.fullscreen_content);

        final State.Id currentQuestionId = new State.Id((String) getIntent().getSerializableExtra(CURRENT_QUESTION));
        String questionText = HardcodedQs.getStateFromId(currentQuestionId).getText();
        TextView tv = (TextView) contentView;
        tv.setText(questionText);


        contentView.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft(){
                Toast.makeText(QuestionActivity.this, "SWIPE LEFT YO!", Toast.LENGTH_SHORT).show();
                startNextQuestionActivity(HardcodedQs.getAfterNo(currentQuestionId));
                finish();
            }
            @Override
            public void onSwipeRight(){
                Toast.makeText(QuestionActivity.this, "SWIPE RIGHT YO!", Toast.LENGTH_SHORT).show();
                startNextQuestionActivity(HardcodedQs.getAfterYes(currentQuestionId));
                finish();
            }

        });

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
