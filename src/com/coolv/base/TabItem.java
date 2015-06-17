package com.coolv.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by moon.zhong on 2015/2/4.
 */
public class TabItem extends View {
	public enum ViewBage{VIEW_BAGE_NONE, VIEW_BAGE_DOT, VIEW_BAGE_COUNT}
	
	private ViewBage vBage = ViewBage.VIEW_BAGE_NONE;
	
	private int bcount = 0;	// 提示数目
	private final float DEFAULT_FONT_SIZE = 14;
    private int mTextSize = 12;
    private int mTextColorSelect = 0xff45c01a;
    private int mTextColorNormal = 0xff777777;
    private Paint mTextPaintNormal;
    private Paint mTextPaintSelect;
    private int mViewHeight, mViewWidth;
    private String mTextValue ;
    private Bitmap mIconNormal;
    private Bitmap mIconSelect;
    private Rect mBoundText;
    private Paint mIconPaintSelect;
    private Paint mIconPaintNormal;
    private Paint mBagePaint;
    
    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public void setViewBage(ViewBage viewBage){
    	this.vBage = viewBage;
    	invalidate();
    }
    
    public void setViewBage(ViewBage viewBage, int bcount){
    	this.vBage = viewBage;
    	this.bcount = bcount;
    	invalidate();
    }
    
    public TabItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initText();
    }

    private void initView() {
        mBoundText = new Rect();
    }

    private void initText() {
        mTextPaintNormal = new Paint();
        mTextPaintNormal.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, mTextSize, getResources().getDisplayMetrics()));
        mTextPaintNormal.setColor(mTextColorNormal);
        mTextPaintNormal.setAntiAlias(true);
        mTextPaintNormal.setAlpha(0xff);

        mTextPaintSelect = new Paint();
        mTextPaintSelect.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, mTextSize, getResources().getDisplayMetrics()));
        mTextPaintSelect.setColor(mTextColorSelect);
        mTextPaintSelect.setAntiAlias(true);
        mTextPaintSelect.setAlpha(0);

        mIconPaintSelect = new Paint(Paint.ANTI_ALIAS_FLAG) ;
        mIconPaintSelect.setAlpha(0);

        mIconPaintNormal = new Paint(Paint.ANTI_ALIAS_FLAG) ;
        mIconPaintNormal.setAlpha(0xff);
        
        mBagePaint = new Paint();
        mBagePaint.setColor(Color.RED);
        mBagePaint.setAntiAlias(true);
    }

    private void measureText() {
        mTextPaintNormal.getTextBounds(mTextValue, 0, mTextValue.length(), mBoundText);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0, height = 0;

        measureText();
        int contentWidth = Math.max(mBoundText.width(), mIconNormal.getWidth());
        int desiredWidth = getPaddingLeft() + getPaddingRight() + contentWidth;
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                width = Math.min(widthSize, desiredWidth);
                break;
            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                width = desiredWidth;
                break;
        }
        int contentHeight = mBoundText.height() + mIconNormal.getHeight();
        int desiredHeight = getPaddingTop() + getPaddingBottom() + contentHeight;
        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                height = Math.min(heightSize, desiredHeight);
                break;
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                height = contentHeight;
                break;
        }
        setMeasuredDimension(width, height);
        mViewWidth = getMeasuredWidth() ;
        mViewHeight = getMeasuredHeight() ;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBitmap(canvas) ;
        drawText(canvas) ;
        
        drawViewBager(canvas);
    }

	private int Dp2Px(Context context, float dp) {  
	    final float scale = context.getResources().getDisplayMetrics().density;  
	    return (int) (dp * scale + 0.5f);  
	}

	private int Px2Dp(Context context, float px) {  
	    final float scale = context.getResources().getDisplayMetrics().density;  
	    return (int) (px / scale + 0.5f);  
	}
	
    private void drawViewBager(Canvas canvas){
        int left = (mViewWidth - mIconNormal.getWidth())/2 ;
        int top = (mViewHeight - mIconNormal.getHeight() - mBoundText.height()) /2 ;
        
    	switch (vBage) {
		case VIEW_BAGE_COUNT:

	        
			mBagePaint.setStyle(Paint.Style.STROKE);//设置空心
			mBagePaint.setStrokeWidth(2);
			mBagePaint.setTextSize(Dp2Px(getContext(), DEFAULT_FONT_SIZE));
			canvas.drawCircle(mViewWidth - left, top + 20, 20, mBagePaint);// 大圆
			canvas.drawText(bcount + "", mViewWidth - left- DEFAULT_FONT_SIZE/2, top + 20 + DEFAULT_FONT_SIZE/2, mBagePaint);// 大圆 
			break;

		case VIEW_BAGE_DOT:
			canvas.drawCircle(mViewWidth - left, top, 10, mBagePaint);// 大圆 
			break;
			
		case VIEW_BAGE_NONE:
			
			break;
		}
    }
    
    private void drawBitmap(Canvas canvas) {
        int left = (mViewWidth - mIconNormal.getWidth())/2 ;
        int top = (mViewHeight - mIconNormal.getHeight() - mBoundText.height()) /2 ;
        canvas.drawBitmap(mIconNormal, left, top ,mIconPaintNormal);
        canvas.drawBitmap(mIconSelect, left, top , mIconPaintSelect);
    }
    private void drawText(Canvas canvas) {
        float x = (mViewWidth - mBoundText.width())/2.0f ;
        float y = (mViewHeight + mIconNormal.getHeight() + mBoundText.height()) /2.0F ;
        canvas.drawText(mTextValue,x,y, mTextPaintNormal);
        canvas.drawText(mTextValue,x,y, mTextPaintSelect);
    }

    public void setTextSize(int textSize) {
        this.mTextSize = textSize;
        mTextPaintNormal.setTextSize(textSize);
        mTextPaintSelect.setTextSize(textSize);
    }

    public void setTextColorSelect(int mTextColorSelect) {
        this.mTextColorSelect = mTextColorSelect;
        mTextPaintSelect.setColor(mTextColorSelect);
        mTextPaintSelect.setAlpha(0);
    }

    public void setTextColorNormal(int mTextColorNormal) {
        this.mTextColorNormal = mTextColorNormal;
        mTextPaintNormal.setColor(mTextColorNormal);
        mTextPaintNormal.setAlpha(0xff);
    }

    public void setTextValue(String TextValue) {
        this.mTextValue = TextValue;
    }
    public void setIconText(int[] iconSelId,String TextValue) {
        this.mIconSelect = BitmapFactory.decodeResource(getResources(), iconSelId[0]);
        this.mIconNormal = BitmapFactory.decodeResource(getResources(), iconSelId[1]);
        this.mTextValue = TextValue;
    }

    public void setTabAlpha(float alpha){
        int paintAlpha = (int)(alpha*255) ;
        mIconPaintSelect.setAlpha(paintAlpha);
        mIconPaintNormal.setAlpha(255-paintAlpha);
        mTextPaintSelect.setAlpha(paintAlpha);
        mTextPaintNormal.setAlpha(255-paintAlpha);
        invalidate();
    }
}
