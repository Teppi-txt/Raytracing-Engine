import java.awt.Color;
import java.util.ArrayList;


class Ray {
    Vector3 origin;
    Vector3 direction;

    public Ray(Vector3 o, Vector3 dir) {
        this.origin = o;
        this.direction = dir;
    }

    public Vector3 at(double t) {
        return Vector3.sum(this.origin, this.direction.scaled(t));
    }

    public Color get_color(ArrayList<Hittable> objects, int depth) {
        Vector3 normalised = direction.normalised();
        double lerp_t = 0.5 * (normalised.y + 1.0);

        HitEvent hit = null;


        if (depth <= 0) { return new Color(0, 0, 0); }

        for (Hittable hittable : objects) {
            HitEvent detected_hit = hittable.detect_hit(this);
            if (detected_hit != null && detected_hit.t > 0.0001) {
                if (hit == null || detected_hit.point.distance_to(origin) < hit.point.distance_to(origin)) {
                    hit = detected_hit;
                }
            }
        }

        if (hit != null) { // hit detected
            Ray diffuse_ray = Ray.generate_random_ray();
            diffuse_ray.origin = hit.point;
            diffuse_ray.reorient_to(hit.normal);

            Color diffuse_color = lerp_color(diffuse_ray.get_color(objects, depth - 1), Color.BLACK, 0.5);
            return lerp_color(hit.material.color, diffuse_color, 0.8);
            //return new Color((int) (255/2 * (hit.normal.x + 1)), (int) (255/2 * (hit.normal.y + 1)), (int) (255/2 * (hit.normal.z + 1)));
        }

        return lerp_color(new Color(255, 255, 255), new Color(160, 217, 239), lerp_t);
    }

    // research how this works
    static public Ray generate_random_ray() {
        double u = Math.random();       // in [0,1)
        double v = Math.random();       // in [0,1)
        double z = 2.0*v - 1.0;         // in [-1,1]
        double a = 2.0*Math.PI*u;
        double r = Math.sqrt(1.0 - z*z);
        Vector3 dir = new Vector3(r*Math.cos(a), r*Math.sin(a), z);

        return new Ray(Vector3.ZERO, dir); // origin overwritten later anyway
    }

    public void reorient_to(Vector3 vector) {
        if (vector.dot(this.direction) <= 0) {
            this.direction.scale(-1);
        }
    }

    static public Color lerp_color(Color c1, Color c2, double i) {

        double r = c1.getRed() + ((c2.getRed() - c1.getRed()) * i);
        double b = c1.getBlue() + ((c2.getBlue() - c1.getBlue()) * i);
        double g = c1.getGreen() + ((c2.getGreen() - c1.getGreen()) * i);
        return new Color((int) r, (int) g, (int) b);
        //return Color.BLUE;
    }
}