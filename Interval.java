class Interval {
    double min;
    double max;
    
    public Interval(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public boolean contains(double a) {
        return min <= a && a <= max;
    }

    public boolean surrounds(double a) {
        return min < a && a < max;
    }

    public double clamp(double x) {
        if (x < min) return min;
        if (x > max) return max;
        return x;
    }
}