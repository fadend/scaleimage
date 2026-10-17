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
        final int width = image.getWidth();
        final int height = image.getHeight();
        if (width == 0 || height == 0) {
            return image;
        }
        if (width > maxWidth || height > maxHeight) {
            double widthScale = (double) maxWidth / width;
            double heightScale = (double) maxHeight / height;
            double scale = Math.min(widthScale, heightScale);
            int newWidth = Math.max(1, Math.min(maxWidth, (int) (scale * width)));
            int newHeight = Math.max(1, Math.min(maxHeight, (int) (scale * height)));
            BufferedImage scaled = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2d = scaled.createGraphics();
            graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // TODO: if the following returns false, block until ready or error.
            graphics2d.drawImage(image, 0, 0, newWidth, newHeight, null);
            return scaled;
        } else {
            return image;
        }
    }
}
