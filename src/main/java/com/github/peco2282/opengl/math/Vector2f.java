package com.github.peco2282.opengl.math;

import com.github.peco2282.opengl.annotation.FieldNotNullByDefault;
import com.github.peco2282.opengl.annotation.ParametersNotNullByDefault;
import com.github.peco2282.opengl.annotation.ReturnNotNullByDefault;
import lombok.Getter;

import java.nio.FloatBuffer;

@Getter
@ReturnNotNullByDefault
@FieldNotNullByDefault
@ParametersNotNullByDefault
public class Vector2f implements Vector<Vector2f, Matrix2f> {
  private final float x, y;

  public Vector2f() {
    this(0F, 0F);
  }

  public Vector2f(float x, float y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public float lengthSquared() {
    return x * x + y * y;
  }

  @Override
  public float length() {
    return (float) Math.sqrt(lengthSquared());
  }

  @Override
  public Vector2f normalize() {
    return divide(length());
  }

  @Override
  public Vector2f add(Vector2f other) {
    return new Vector2f(x + other.x, y + other.y);
  }

  @Override
  public Vector2f negate() {
    return scale(-1F);
  }

  @Override
  public Vector2f subtract(Vector2f other) {
    return add(other.negate());
  }

  @Override
  public Vector2f scale(float scalar) {
    return new Vector2f(x * scalar, y * scalar);
  }

  @Override
  public Vector2f divide(float scalar) {
    return scale(1F / scalar);
  }

  @Override
  public float dot(Vector2f other) {
    return x * other.x + y * other.y;
  }

  @Override
  public Vector2f lerp(Vector2f other, float alpha) {
    return scale(1F - alpha).add(other.scale(alpha));
  }

  @Override
  public void buffer(FloatBuffer buffer) {
    buffer.put(x).put(y).flip();
  }
}
