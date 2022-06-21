package cn.lxr.example.gaussian_blur;

import cn.lxr.example.gaussian_blur.common.GaussianBlur;
import cn.lxr.example.gaussian_blur.common.GaussianBlur2;
import cn.lxr.example.gaussian_blur.util.FileUtils;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GaussianBlurTest {


    @Test
    public void blurUtil() {
        List<File> fileList = FileUtils.extractFolderFile("upload/");
        fileList.forEach(file -> {
            BufferedImage image = FileUtils.getBufferedImage(file);
            new GaussianBlur(20, image).getBluredImg("download/" + file.getName(), "png");
        });
    }

    @Test
    public void baseBlur() {
        List<File> fileList = FileUtils.extractFolderFile("upload/");
        fileList.forEach(file -> {
            BufferedImage image = FileUtils.getBufferedImage(file);
            int rad = 100;
            double[][] weights = GaussianBlur2.getInstance().generateWeightMatrix(rad, Math.sqrt(rad));
            GaussianBlur2.getInstance().printWeightedMatrixToFile(weights, "download1/matrix.png");
            image = GaussianBlur2.getInstance().createGaussianImage(image, weights, rad);
            try {
                ImageIO.write(image, "PNG", new File("download1/" + file.getName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
