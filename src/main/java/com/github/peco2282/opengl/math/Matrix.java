package com.github.peco2282.opengl.math;

import com.github.peco2282.opengl.annotation.ReturnNotNullByDefault;

import java.nio.FloatBuffer;

@ReturnNotNullByDefault
public interface Matrix<M extends Matrix<M, V>, V extends Vector<V, M>> {
  Matrix<Matrix2f, Vector2f> INSTANCE_2F = new Matrix2f();
  Matrix<Matrix3f, Vector3f> INSTANCE_3F = new Matrix3f();
  void setIdentity();

  M add(M other);

  M negate();

  M subtract(M other);

  M multiply(float scalar);

  V multiply(V vector);

  M multiply(M other);

  M transpose();

  void buffer(FloatBuffer buffer);
}
