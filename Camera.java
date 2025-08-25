
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;


class Camera {
    double aspect_ratio = 16.0/9.0;
    // image
    int image_width = 800;
    int image_height = (int) (image_width / aspect_ratio);


    //camera
    double focal_length = 1.0;
    double viewport_height = 2.0;
    double viewport_width = viewport_height * aspect_ratio;

    int rays_per_pixel = 10;
    int ray_depth = 4;

    Vector3 camera_center = Vector3.ZERO;
    Vector3 pixel_increment_x = new Vector3 (viewport_width / image_width, 0, 0);
    Vector3 pixel_increment_y = new Vector3 (0, -viewport_height / image_height, 0);

    Vector3 viewport_top_left = camera_center.subtracted(new Vector3(viewport_width / 2, -viewport_height / 2, focal_length));
    Vector3 pixel00 = Vector3.sum(viewport_top_left, pixel_increment_x.scaled(0.5), pixel_increment_y.scaled(0.5));

    public Camera() {

    }

    public BufferedImage render(ArrayList<Hittable> scene) {
        BufferedImage bi = new BufferedImage(image_width, image_height, BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < image_width; x++) {
            for (int y = 0; y < image_height; y++) {

                // get pixel location on viewport

                Vector3 color_sum = new Vector3();

                // average the color of 'n' randomly shifted rays (antialiasing, and later raytracing)
                for (int i = 0; i < rays_per_pixel; i++) {
                    Color color = create_ray(x, y).get_color(scene, ray_depth);

                    color_sum.x += color.getRed();
                    color_sum.y += color.getGreen();
                    color_sum.z += color.getBlue();
                }
                color_sum.divide(rays_per_pixel);


                bi.setRGB(x, y, int_from_rgb((int) color_sum.x, (int) color_sum.y, (int) color_sum.z));
            }
        }

        return bi;
    }

    public Ray create_ray(int x, int y) {
        Vector3 offset = sample_square();
        Vector3 pixel_location = Vector3.sum(pixel00, pixel_increment_x.scaled(x + offset.x), pixel_increment_y.scaled(y + offset.y));
        Vector3 ray_direction = pixel_location.subtracted(camera_center);
        return new Ray(camera_center, ray_direction);
    }

    static public int int_from_rgb(int red, int green, int blue) {
        red = (red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
        green = (green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
        blue = blue & 0x000000FF; //Mask out anything not blue.

        return 0xFF000000 | red | green | blue; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
    }

    
    static Vector3 sample_square() {
        Random r = new Random();
        return new Vector3(r.nextDouble() - 0.5, r.nextDouble() - 0.5, 0);
    }
}