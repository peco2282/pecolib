package com.github.peco2282.opengl.math;

import com.github.peco2282.opengl.annotation.ParametersNotNullByDefault;
import com.github.peco2282.opengl.annotation.ReturnNotNullByDefault;
import lombok.Getter;

import java.nio.FloatBuffer;

@ReturnNotNullByDefault
@ParametersNotNullByDefault
@Getter
public class Vector3f implements Vector<Vector3f, Matrix3f> {
  private float x, y, z;
  public Vector3f(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  @Override
  public float lengthSquared() {
    return 0;
  }

  @Override
  public float length() {
    return 0;
  }

  @Override
  public Vector3f normalize() {
    return null;
  }

  @Override
  public Vector3f add(Vector3f other) {
    return null;
  }

  @Override
  public Vector3f negate() {
    return null;
  }

  @Override
  public Vector3f subtract(Vector3f other) {
    return null;
  }

  @Override
  public Vector3f scale(float scalar) {
    return null;
  }

  @Override
  public Vector3f divide(float scalar) {
    return null;
  }

  @Override
  public float dot(Vector3f other) {
    return 0;
  }

  @Override
  public Vector3f lerp(Vector3f other, float alpha) {
    return null;
  }

  @Override
  public void buffer(FloatBuffer buffer) {

  }
}
