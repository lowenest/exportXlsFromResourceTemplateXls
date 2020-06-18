package com.zslin.utils;

import org.apache.commons.io.FileUtils;
import org.apache.poi.util.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import java.io.*;

/**
 * Created by zid
 */
public class TemplateFileUtil {

    public static InputStream getTemplates(String tempName) throws FileNotFoundException {

        File file =null;
        try {
            //获取所有匹配的文件
            InputStream inputStreams = TemplateFileUtil.class.getResourceAsStream("/excel-templates/" + tempName);
            file = File.createTempFile("xls-",".xls.tmp");
            if (inputStreams==null)throw new IllegalArgumentException("stream is null");
            FileUtils.copyInputStreamToFile(inputStreams, file);
        }catch (Exception e) {
            e.printStackTrace();
        }
        InputStream is =new FileInputStream(file);

        if (is == null) throw new IllegalArgumentException("null found file");
        return is;
    }

    /**
     * 将InputStream写入本地文件
     * @param destination 写入本地目录
     * @param input 输入流
     * @throws IOException IOException
     */
    public static void writeToLocal(String destination, InputStream input)
            throws IOException {
        int index;
        byte[] bytes = new byte[1024];
        FileOutputStream downloadFile = new FileOutputStream(destination);
        while ((index = input.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        input.close();
        downloadFile.close();

    }
}
