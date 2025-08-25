
import java.util.Random;

public class Vector3 {

    public static final Vector3 ZERO = new Vector3();   

    double x;
    double y;
    double z;

    // zero vector
    public Vector3() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3 sum (Vector3... vectors) {
        double sum_x = 0.0; double sum_y = 0.0; double sum_z = 0.0;

        for (Vector3 vector : vectors) {
            sum_x += vector.x;
            sum_y += vector.y;
            sum_z += vector.z;
        }

        return new Vector3(sum_x, sum_y, sum_z );
    }

    public Vector3 copy() {
        return new Vector3(this.x, this.y, this.z);
    }

    public void add (Vector3 v2) {
        this.x += v2.x;
        this.y += v2.y;
        this.z += v2.z;
    }

    public double distance_to(Vector3 v2) {
        return this.subtracted(v2).length();
    }

    public void subtract (Vector3 v2) {
        this.x -= v2.x;
        this.y -= v2.y;
        this.z -= v2.z;
    }

    @Override
    public String toString () {
        return String.format("(%f, %f, %f)", this.x, this.y, this.z);
    }

    public Vector3 subtracted (Vector3 v2) {
        return new Vector3(this.x - v2.x, this.y - v2.y, this.z - v2.z);
    }

    public double length_squared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    public double length() {
        return Math.sqrt(this.length_squared());
    }

    public void scale(double a) {
        this.x *= a;
        this.y *= a;
        this.z *= a;
    }

    public Vector3 scaled(double a) {
        return new Vector3(this.x * a, this.y * a, this.z * a);
    }

    public void divide(double a) {
        this.x /= a;
        this.y /= a;
        this.z /= a;
    }

    public void normalise() {
        this.divide(this.length());
    }

    public Vector3 normalised() {
        Vector3 copy = this.copy();
        copy.normalise();
        return copy;
    }

    public double dot(Vector3 v2) {
        return this.x * v2.x + this.y * v2.y + this.z * v2.z;
    }

    public Vector3 cross(Vector3 v2) {
        return new Vector3(
            this.y * v2.z - this.z * v2.y,
            this.z * v2.x - this.x * v2.z,
            this.x * v2.y - this.y * v2.x
        );
    }

    static Vector3 random() {
        Random r = new Random();
        return new Vector3(r.nextDouble(), r.nextDouble(), r.nextDouble());
    }
}