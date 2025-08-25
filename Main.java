import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


class Main {
    public static void main(String[] args) {
        // world
        ArrayList<Hittable> scene = new ArrayList<>();
        // Base (yours)
        scene.add(new Sphere(new Material(Color.BLUE), new Vector3(0, 0, -1), 0.5));
        scene.add(new Sphere(new Material(Color.GRAY), new Vector3(0, -100.5, -1), 100));

        // Two buddies left/right
        scene.add(new Sphere(new Material(new Color(255, 255, 255)),  new Vector3(1.0, 0.0, -1.2), 0.5));  // red
        scene.add(new Sphere(new Material(new Color(30, 220, 120)), new Vector3( -1.0, 0.0, -1.2), 0.5));  // green

        // A little “hover” sphere
        //scene.add(new Sphere(new Material(new Color(240, 200, 40)), new Vector3(0.0, 0.5, -1.5), 0.35)); // yellow

        // Arc of tiny marbles sitting just above the ground
        // for (int i = -3; i <= 3; i++) {
        //     double r = 0.12;
        //     double x = i * 0.35;
        //     double z = -1.8 - 0.15 * i * i;      // gentle curve
        //     double y = r - 0.48;                 // ~touching ground (ground plane is ~y = -0.5)
        //     Color c = new Color(
        //         Math.min(255, 60 + (i + 3) * 30),
        //         Math.min(255, 100 + (3 - Math.abs(i)) * 20),
        //         Math.max(0,   200 - (i + 3) * 25)
        //     );
        //     scene.add(new Sphere(new Material(c), new Vector3(x, y, z), r));
        // }

        BufferedImage bi = new Camera().render(scene);
        save_image(bi, new File("image.png"));
    }

    static public void save_image(BufferedImage image, File filepath) {
        try {
            ImageIO.write(image, "png", filepath);
        } catch (IOException e) {
            System.err.println("Failed to save bufferedimage to file:" + filepath.toString());
        }
    }
}