package com.example.apptest.androidprojectmonitor.feature

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

class SideBar : View {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var onTextTouchedListener: ((String) -> Unit)? = null

    fun setOnTextTouchedListener(l: (String) -> Unit) {
        onTextTouchedListener = l;
    }

    private var paint: Paint = Paint()

    init {
        paint.textSize = 25f
        paint.isAntiAlias = true
        paint.color = Color.BLACK
        paint.strokeWidth = 1f
        paint.typeface= Typeface.DEFAULT_BOLD
        paint.style = Paint.Style.STROKE
        paint.textAlign = Paint.Align.LEFT

    }

    protected var current: Int = -1
    private val rect: Rect = Rect();
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val charHeight = height * 1.0f / chars.size

        for (i in 0 until chars.size) {
            paint.textSize = 20f
            paint.isAntiAlias = true
            paint.color = Color.BLACK
            paint.strokeWidth = 2f
            paint.style = Paint.Style.STROKE
            paint.textAlign = Paint.Align.LEFT
            paint.getTextBounds(chars[i], 0, 1, rect)

            if (current == i) {
                paint.color = Color.parseColor("#3399ff");
                paint.isFakeBoldText = true;
            }

            canvas!!.drawText(
                    chars[i],
                    (width - rect.right) * 1.0f / 2,
                    (charHeight / 2 + ((abs(rect.top) + abs(rect.bottom)) / 2)) + charHeight * i,
                    paint
            )
            paint.reset()
        }


    }


    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        val action = event!!.action
        val touchY = event.y;
        val preChooseIdx = current

        val currentTouchIdx = (touchY / height) * chars.size

        when (action) {
            MotionEvent.ACTION_UP -> {
                background = ColorDrawable(0x00000000);
                current = -1;
                invalidate();
            }
            else -> {
                background = ColorDrawable(0xFF0000)

                if (currentTouchIdx.toInt() != preChooseIdx) {
                    // 如果触摸点没有超出控件范围
                    if (currentTouchIdx >= 0 && currentTouchIdx < chars.size) {
                        onTextTouchedListener?.let {
                            it(chars[currentTouchIdx.toInt()])
                        }
//                        if (listener != null) {
//                            listener.onTouchedLetterChange(alphabet[currentTouchIndex]);
//                        }

//                        if (textViewDialog != null) {
//                            textViewDialog.setText(alphabet[currentTouchIndex]);
//                            textViewDialog.setVisibility(View.VISIBLE);
//                        }

                        current = currentTouchIdx.toInt()
                        invalidate();
                    }
                }
            }
        }


        return true
    }

    companion object {
        val chars = arrayOf(
                "A", "B", "C", "D", "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z", "#"
        )
    }
}