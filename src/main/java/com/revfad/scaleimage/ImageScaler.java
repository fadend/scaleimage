package com.revfad.scaleimage;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class ImageScaler {

    private final int maxWidth;
    private final int maxHeight;

    public ImageScaler(int maxWidth, int maxHeight) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    public BufferedImage scale(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        if (width == 0 || height == 0) {
            return image;
        }
        if (width <= maxWidth && height <= maxHeight) {
            return image;
        }
        BufferedImage scaled = null;
        do {
            double widthScale = (double) maxWidth / width;
            double heightScale = (double) maxHeight / height;
            double scale = Math.max(0.5, Math.min(widthScale, heightScale));
            width = Math.max(1, (int) (scale * width));
            height = Math.max(1, (int) (scale * height));
            scaled = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2d = scaled.createGraphics();
            graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // TODO: if the following returns false, block until ready or error.
            graphics2d.drawImage(image, 0, 0, width, height, null);
            image = scaled;
        } while (width > maxWidth || height > maxHeight);
        return scaled;
    }
}
