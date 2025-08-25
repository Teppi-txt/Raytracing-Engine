class HitEvent {
    Vector3 point;
    Vector3 normal;
    Material material;
    double t;

    public HitEvent(Vector3 point, Vector3 normal, Material material, double t) {
        this.point = point;
        this.normal = normal;
        this.material = material;
        this.t = t;
    }
}