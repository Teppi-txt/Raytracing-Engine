class Sphere extends Hittable {
    Vector3 center;
    double radius;

    public Sphere (Material material, Vector3 center, double radius) {
        super(material);
        this.center = center;
        this.radius = radius;
    }

    public HitEvent detect_hit(Ray ray) {
        double a = ray.direction.dot(ray.direction);
        double b = ray.direction.scaled(-2).dot(center.subtracted(ray.origin));
        double c = center.subtracted(ray.origin).dot(center.subtracted(ray.origin)) - radius * radius;
        double discriminant = b * b - 4 * a * c;

        if (discriminant < 0) {
            // no hit
            return null;
        } else {

            double t = (-b - Math.sqrt(discriminant))/(2 * a);
            Vector3 normal = ray.at(t).subtracted(this.center).normalised();

            if (t < 0) {
                return null;
            }
            return new HitEvent(ray.at(t), normal, this.material, t);

        }
        
    }
}