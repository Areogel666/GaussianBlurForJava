package cn.lxr.example.gaussian_blur.util;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class FileUtils {

    /**
     * 生成图片流
     *
     * @param file
     * @return
     */
    public static BufferedImage getBufferedImage(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            log.error("read image error", e);
        }
        return null;
    }

    /**
     * 获取目录下所有文件
     *
     * @param dirPath
     * @return
     */
    public static List<File> extractFolderFile(String dirPath) {
        File dir = new File(dirPath);
        if (dir == null || !dir.exists()) {
            return new ArrayList<>();
        }
        List<File> fileList = Arrays.stream(dir.listFiles()).flatMap(file -> {
            if (file.isDirectory()) {
                return extractFolderFile(file.getPath()).stream();
            }
            List<File> singleFileList = new ArrayList<>();
            singleFileList.add(file);
            return singleFileList.stream();
        }).collect(Collectors.toList());
        return fileList;
    }
}
