
class Hittable {
    Material material;
    
    public Hittable (Material material) {
        this.material = material;
    }

    public HitEvent detect_hit(Ray ray) {
        throw new UnsupportedOperationException();
    }
}