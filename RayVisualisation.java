import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


class RayVisualisation {
    public static void main(String[] args) {
        // world
        ArrayList<Hittable> scene = new ArrayList<>();

        scene.add(new Sphere(new Material(Color.RED), new Vector3(0, -0.2, -1), 0.01));

        for (int i = 0; i < 1000; i++) {
            Ray diffuse_ray = Ray.generate_random_ray();
            diffuse_ray.origin = new Vector3(0, -0.2, -1);
            diffuse_ray.reorient_to(new Vector3(0, 1, 0));
            diffuse_ray.direction.normalise();
            diffuse_ray.direction.scale(0.7);

            scene.add(new Sphere(new Material(Color.BLACK), diffuse_ray.at(1), 0.01));
        }

        BufferedImage bi = new Camera().render(scene);
        save_image(bi, new File("rays.png"));
    }

    static public void save_image(BufferedImage image, File filepath) {
        try {
            ImageIO.write(image, "png", filepath);
        } catch (IOException e) {
            System.err.println("Failed to save bufferedimage to file:" + filepath.toString());
        }
    }
}