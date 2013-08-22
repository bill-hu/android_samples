package com.amlogic.test;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

@SuppressLint("NewApi")
public class MyImageView extends ImageView
{
  private int mCounter = 0;
  private float mLastTranslation = 0.0F;
  private boolean mRotating = false;

  public MyImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public void afterStop()
  {
    this.mRotating = false;
    invalidate();
  }

  public int getCounter()
  {
    return this.mCounter;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if ((isFocused()) && (!this.mRotating))
    {
      setAlpha(0.5F);
      return;
    }
    setAlpha(1.0F);
  }

  public void preStart()
  {
    this.mCounter = 0;
    this.mRotating = true;
    invalidate();
  }


public void setRotationX(float paramFloat)
  {
    float f = getRotationX();
    if (f != this.mLastTranslation)
    {
      this.mLastTranslation = f;
      this.mCounter = (1 + this.mCounter);
    }
    super.setRotationX(paramFloat);
  }

  public void setRotationY(float paramFloat)
  {
//	  this.mCounter = (1 + this.mCounter);
    float f = getRotationY();
    if (f != this.mLastTranslation)
    {
      this.mLastTranslation = f;
      this.mCounter = (1 + this.mCounter);
    }
    super.setRotationY(paramFloat);
  }

}