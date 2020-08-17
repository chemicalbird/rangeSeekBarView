package com.example.rangeview;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.widget.TextView;

import com.android.rangeview.RangeSeekBarView;
import com.android.rangeview.SplitRangeView;

public class MainActivity extends AppCompatActivity {

    RangeSeekBarView.TimeLineChangeListener listener = new RangeSeekBarView.TimeLineChangeListener() {
        @Override
        public void onRangeChanged(long start, long end) {
            infoView.setText(String.format("%d : %d", start, end));
        }

        @Override
        public void onRangeMove(long start, long end) {
            infoView.setText(String.format("%d : %d", start, end));
        }
    };
    private TextView infoView;
    private Object lastRangeChangeObject;
    private float testStartFraction;
    private float testEndFraction;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        infoView = findViewById(R.id.info);

        RangeSeekBarView seekBarView1 = findViewById(R.id.seekbar1);
        seekBarView1.setDuration(1000);
        seekBarView1.addIndicatorChangeListener(listener);

        RangeSeekBarView seekBarView2 = findViewById(R.id.seekbar2);
        seekBarView2.setDuration(1000);
        seekBarView2.addIndicatorChangeListener(listener);

        RangeSeekBarView seekBarView3 = findViewById(R.id.seekbar3);
        seekBarView3.setDuration(1000);
        seekBarView3.addIndicatorChangeListener(listener);

        RangeSeekBarView seekBarView4 = findViewById(R.id.seekbar4);
        seekBarView4.setDuration(1000);
        seekBarView4.addIndicatorChangeListener(listener);

        RangeSeekBarView seekBarView5 = findViewById(R.id.seekbar5);
        seekBarView5.setDuration(1000);
        seekBarView5.addIndicatorChangeListener(listener);

        RangeSeekBarView seekBarView6 = findViewById(R.id.seekbar6);
        seekBarView6.setDuration(1000);
        seekBarView6.addIndicatorChangeListener(listener);

        findViewById(R.id.reset).setOnClickListener(v -> {
            seekBarView1.resetState(0, 1000, true);
            seekBarView2.resetState(0, 1000, true);
            seekBarView3.resetState(0, 1000, true);
            seekBarView4.resetState(0, 1000, true);
            seekBarView5.resetState(100, 400, true);
            seekBarView6.resetState(0, 1000, true);
        });

        SplitRangeView splitRangeView = findViewById(R.id.split_range);
        splitRangeView.getLayoutParams().width = 60 * 100;
//        splitRangeView.addSpan(0, 100, "", 1);

        splitRangeView.addSpan(200, 399, "", 2);

        splitRangeView.addSpan(800, 790, "", 3);
        splitRangeView.addSpan(2000, 2200, "", 4);

        Paint customSpanPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        customSpanPaint.setColor(Color.CYAN);

        Paint customSelectedSpanPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        customSelectedSpanPaint.setColor(Color.WHITE);
        customSelectedSpanPaint.setStyle(Paint.Style.STROKE);
        customSelectedSpanPaint.setStrokeWidth(4);

        SplitRangeView.Span span = new SplitRangeView.Span(0, 100, "", 1) {
            @Override
            protected boolean draw(Canvas canvas, RectF bound) {
                canvas.drawRoundRect(bound, 8, 8, customSpanPaint);
                if (isSelected()) {
                    canvas.drawRoundRect(bound, 8, 8, customSelectedSpanPaint);
                }
                return true;
            }
        };
        splitRangeView.addSpan(span);
    }
}
