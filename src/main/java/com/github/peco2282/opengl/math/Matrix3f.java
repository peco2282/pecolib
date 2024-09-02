package com.github.peco2282.opengl.math;

import com.github.peco2282.opengl.annotation.ParametersNotNullByDefault;
import com.github.peco2282.opengl.annotation.ReturnNotNullByDefault;

import java.nio.FloatBuffer;
import lombok.Builder;

@ReturnNotNullByDefault
@ParametersNotNullByDefault
public class Matrix3f implements Matrix<Matrix3f, Vector3f> {

  private float m00, m01, m02;
  private float m10, m11, m12;
  private float m20, m21, m22;
  public Matrix3f() {
    setIdentity();
  }
  public Matrix3f(Vector3f col1, Vector3f col2, Vector3f col3) {}

  @Override
  public void setIdentity() {

  }

  @Override
  public Matrix3f add(Matrix3f other) {
    return new Matrix3f(
        new Vector3f(this.m00 + other.m00, this.m01 + other.m01, this.m02 + other.m02),
        new Vector3f(this.m10 + other.m10, this.m11 + other.m11, this.m12 + other.m12),
        new Vector3f(this.m20 + other.m20, this.m21 + other.m21, this.m22 + other.m22)
    );
  }

  @Override
  public Matrix3f negate() {
    return multiply(-1);
  }

  @Override
  public Matrix3f subtract(Matrix3f other) {
    return add(other.negate());
  }

  @Override
  public Matrix3f multiply(float scalar) {
    return new Matrix3f();
  }

  @Override
  public Vector3f multiply(Vector3f vector) {
    return null;
  }

  @Override
  public Matrix3f multiply(Matrix3f other) {
    return null;
  }

  @Override
  public Matrix3f transpose() {
    return null;
  }

  @Override
  public void buffer(FloatBuffer buffer) {

  }
}
