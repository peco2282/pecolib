package com.github.peco2282.opengl.math;


import com.github.peco2282.opengl.annotation.FieldNotNullByDefault;
import com.github.peco2282.opengl.annotation.ParametersNotNullByDefault;
import com.github.peco2282.opengl.annotation.ReturnNotNullByDefault;

import java.nio.FloatBuffer;

@ReturnNotNullByDefault
@FieldNotNullByDefault
@ParametersNotNullByDefault
public interface Vector<V extends Vector<V, M>, M extends Matrix<M, V>> {
  float lengthSquared();
  float length();

  V normalize();

  V add(V other);

  V negate();

  V subtract(V other);

  V scale(float scalar);

  V divide(float scalar);

  float dot(V other);

  V lerp(V other, float alpha);

  void buffer(FloatBuffer buffer);
}
