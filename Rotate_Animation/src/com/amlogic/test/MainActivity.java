package com.amlogic.test;

import com.amlogic.test.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnClickListener{
	private ValueAnimator mRotation = null;
	protected long mEndTime;
	protected long mStartTime;
	MyImageView myImageView=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_main);
		findViewById(R.id.imageView1).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
	    if (this.mRotation != null){
		      this.mRotation.cancel();
	          mRotation=null;
	          ((TextView)findViewById(R.id.text)).setText(" Time used: " + (-MainActivity.this
	        		  .mStartTime + MainActivity.this.mEndTime) + " times " + myImageView.getCounter() + " times " + (float)(-MainActivity.this.mStartTime + MainActivity.this.mEndTime) / myImageView.getCounter() + " ms/frame");
	    }
	    else{
	    	((TextView)findViewById(R.id.text)).setText("");
		StartRotationY();
	    }
	}

	private void StartRotationY()
	  {
	    if (this.mRotation != null)
	      this.mRotation.cancel();
	    this.mRotation = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F, 0.0F });
	    myImageView = (MyImageView)findViewById(R.id.imageView1);
	    myImageView.setImageResource(R.drawable.test);
	    myImageView.preStart();
	    this.mRotation.addListener(new AnimatorListenerAdapter()
	    {
	      public void onAnimationEnd(Animator paramAnonymousAnimator)
	      {
	        myImageView.setRotationY(0.0F);
	        myImageView.setPivotX(myImageView.getWidth() / 2);
	        myImageView.setPivotY(myImageView.getHeight() / 2);
	        MainActivity.this.mEndTime = System.currentTimeMillis();
	      }

	      public void onAnimationStart(Animator paramAnonymousAnimator)
	      {
	        myImageView.setRotationY(0.0F);
	        myImageView.setPivotX(myImageView.getWidth() / 2);
	        myImageView.setPivotY(myImageView.getHeight() / 2);
	        MainActivity.this.mStartTime = System.currentTimeMillis();
	        myImageView.setCameraDistance(10000.0F);
	      }
	    });
	    this.mRotation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
	    {
	      public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
	      {
	        float f = 0.0F + 360.0F * ((Float)paramAnonymousValueAnimator.getAnimatedValue()).floatValue();
	        myImageView.setRotationY(f);
	      }
	    });
	    this.mRotation.setInterpolator(new LinearInterpolator());
	    this.mRotation.setDuration(5000L);
	    this.mRotation.setRepeatCount(-1);
	    this.mRotation.setRepeatMode(1);
	    long i=this.mRotation.getFrameDelay();
	    if(i>0)
	    	this.mRotation.setFrameDelay(0);
	    this.mRotation.start();
	  }
}
