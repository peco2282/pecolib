package com.github.peco2282.opengl.math;

import com.github.peco2282.opengl.annotation.ReturnNotNullByDefault;

import java.nio.FloatBuffer;

@ReturnNotNullByDefault
public class Matrix2f implements Matrix<Matrix2f, Vector2f> {
  private float m00, m01;
  private float m10, m11;

  public Matrix2f() {
    setIdentity();
  }

  public Matrix2f(Vector2f col1, Vector2f col2) {
    this.m00 = col1.x();
    this.m01 = col1.y();
    this.m10 = col2.x();
    this.m11 = col2.y();
  }

  public Matrix2f(float m00, float m01, float m10, float m11) {
    this.m00 = m00;
    this.m01 = m01;
    this.m10 = m10;
    this.m11 = m11;
  }

  public Matrix2f(Matrix2f matrix) {
    this.m00 = matrix.m00;
    this.m01 = matrix.m01;
    this.m10 = matrix.m10;
    this.m11 = matrix.m11;
  }

  @Override
  public void setIdentity() {
    this.m00 = 0;
    this.m01 = 0;
    this.m10 = 0;
    this.m11 = 0;
  }

  @Override
  public Matrix2f add(Matrix2f other) {
    return new Matrix2f(
        this.m00 + other.m00,
        this.m01 + other.m01,
        this.m10 + other.m10,
        this.m11 + other.m11
    );
  }

  @Override
  public Matrix2f negate() {
    return multiply(-1);
  }

  @Override
  public Matrix2f subtract(Matrix2f other) {
    return add(other.negate());
  }

  @Override
  public Matrix2f multiply(float scalar) {
    return new Matrix2f(
        this.m00 * scalar,
        this.m01 * scalar,
        this.m10 * scalar,
        this.m11 * scalar
    );
  }

  @Override
  public Vector2f multiply(Vector2f vector) {
    return new Vector2f(
        this.m00 * vector.x() + this.m01 * vector.y(),
        this.m10 * vector.x() + this.m11 * vector.y()
    );
  }

  @Override
  public Matrix2f multiply(Matrix2f other) {
    return new Matrix2f(
        this.m00 * other.m00 + this.m01 * other.m10,
        this.m10 * other.m00 + this.m11 * other.m10,
        this.m00 * other.m01 + this.m01 * other.m11,
        this.m10 * other.m01 + this.m11 * other.m11
    );
  }

  @Override
  public Matrix2f transpose() {
    return new Matrix2f(this);
  }

  @Override
  public void buffer(FloatBuffer buffer) {
    buffer.put(m00).put(m10);
    buffer.put(m01).put(m11);
    buffer.flip();
  }
}
