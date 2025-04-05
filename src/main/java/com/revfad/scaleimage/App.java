package com.revfad.scaleimage;

import com.revfad.scaleimage.ImageScaler;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command()
public class App implements Runnable {

    @Option(names = { "--max-width" }, required = true, description = "Max width in pixels.")
    private int maxWidth = -1;

    @Option(names = { "--max-height" }, required = true, description = "Max height in pixels.")
    private int maxHeight = -1;

    @Parameters()
    private List<String> imageFiles = new ArrayList<>();

    @Override
    public void run() {
        ImageScaler scaler = new ImageScaler(maxWidth, maxHeight);
        for (String file : imageFiles) {
            File imageFile = new File(file);
            try {
                BufferedImage image = ImageIO.read(imageFile);
                BufferedImage scaled = scaler.scale(image);
                if (scaled != image) {
                    ImageIO.write(scaled, "jpg", imageFile);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
