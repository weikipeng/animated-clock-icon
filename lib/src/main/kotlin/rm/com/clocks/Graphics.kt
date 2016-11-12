package rm.com.clocks

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.View

/**
 * Created by alex
 */

internal fun Resources.dip(value: Int): Int = (value * displayMetrics.density).toInt()
internal fun Context.dip(value: Int): Int = resources.dip(value)
internal fun View.dip(value: Int): Int = resources.dip(value)

internal fun smoothPaint(paintColor: Int = Color.WHITE): Paint =
    Paint().apply {
      isAntiAlias = true
      color = paintColor
    }

internal fun smoothLinePaint(paintColor: Int = Color.WHITE): Paint =
    smoothPaint(paintColor).apply {
      style = Paint.Style.STROKE
      strokeCap = Paint.Cap.ROUND
      strokeJoin = Paint.Join.ROUND
    }


internal inline fun Canvas.transform(crossinline init: Canvas.() -> Unit) {
  save()
  init()
  restore()
}

internal fun Canvas.drawLineWithAngle(fromX: Float, fromY: Float, angleDegrees: Float, length: Float, paint: Paint) {
  val (endX, endY) = angleDegrees.radiusOf(fromX, fromY, length)

  drawLine(fromX, fromY, endX, endY, paint)
}

internal fun rectFOf(left: Int, top: Int, right: Int, bottom: Int) = RectF(
    left.toFloat()
    , top.toFloat()
    , right.toFloat()
    , bottom.toFloat()
)

internal fun Int.withAlpha(alpha: Int): Int {
  require(alpha in 0x00..0xFF)
  return this and 0x00FFFFFF or (alpha shl 24)
}