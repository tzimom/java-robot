package de.tzimom.javarobot.graphics.rendering.dynamic;

public final class Vector2f {
    private final float x;
    private final float y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f(float side) {
        this(side, side);
    }

    public Vector2f add(Vector2f other) {
        return new Vector2f(x + other.x, y + other.y);
    }

    public Vector2f subtract(Vector2f other) {
        return new Vector2f(x - other.x, y - other.y);
    }

    public Vector2f multiply(Vector2f other) {
        return new Vector2f(x * other.x, y * other.y);
    }

    public Vector2f divide(Vector2f other) {
        return new Vector2f(x / other.x, y / other.y);
    }

    public Vector2f multiply(float value) {
        return new Vector2f(x * value, y * value);
    }

    public Vector2f divide(float value) {
        return new Vector2f(x / value, y / value);
    }

    public float x() {
        return x;
    }

    public float y() {
        return y;
    }
}
