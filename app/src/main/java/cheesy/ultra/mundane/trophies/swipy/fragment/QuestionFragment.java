package cheesy.ultra.mundane.trophies.swipy.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cheesy.ultra.mundane.trophies.swipy.R;
import cheesy.ultra.mundane.trophies.swipy.questions.State;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class QuestionFragment extends Fragment {
    public static String CURRENT_QUESTION_ID = "current question id";
    public static String CURRENT_QUESTION_TEXT = "current question text";

    public interface QuestionEventHandler {
        public void handleYes(State.Id currentId);
        public void handleNo(State.Id currentId);
    }


    private Point windowSize;
    private Point layoutSize;

    private QuestionEventHandler questionHandler;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        questionHandler = (QuestionEventHandler)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        final View contentView = view.findViewById(R.id.fullscreen_content);

        final State.Id currentQuestionId = new State.Id((String)getArguments().get(CURRENT_QUESTION_ID));
        final String questionText = (String)getArguments().get(CURRENT_QUESTION_TEXT);

        TextView tv = (TextView) contentView;
        tv.setText(questionText);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        windowSize = new Point();
        display.getSize(windowSize);

        layoutSize = new Point((int)(windowSize.x * (8f / 10f)), (int)(windowSize.y * (6f / 10f)));
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
                            questionHandler.handleYes(currentQuestionId);
                        }else if (isNo(touchX)){
                            questionHandler.handleNo(currentQuestionId);
                        }else{
                            resetTextView(v);
                        }

                        break;
                }
                return true;
            }
        });
        return view;
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

}
