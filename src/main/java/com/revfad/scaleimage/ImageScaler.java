package com.revfad.scaleimage;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class ImageScaler {

    private final int maxWidth;
    private final int maxHeight;

    public ImageScaler(int maxWidth, int maxHeight) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    public BufferedImage scale(BufferedImage image) {
        final int width = image.getWidth();
        final int height = image.getHeight();
        if (width == 0 || height == 0) {
            return image;
        }
        int newWidth = width;
        int newHeight = height;
        if (width > maxWidth) {
            newWidth = maxWidth;
            newHeight = Math.max(1, (int) Math.floor((double) maxWidth / width * height));
            if (newHeight > maxHeight) {
                newHeight = maxHeight;
                newWidth = Math.max(1, (int) Math.floor((double) maxHeight / newHeight * newWidth));
            }
        }
        if (newWidth == width && newHeight == height) {
            return image;
        }
        BufferedImage scaled = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2d = scaled.createGraphics();
        // TODO: if the following returns false, block until ready or error.
        graphics2d.drawImage(image, 0, 0, newWidth, newHeight, null);
        return scaled;
    }
}
