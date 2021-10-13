package by.itransition.mathtasksapp.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


@Component
public class TmpFilesCreator {
    public static File convertMultipartFileToFile(MultipartFile file) throws IOException
    {
        File convertFile = File.createTempFile("data",null);
        FileOutputStream fos = new FileOutputStream(convertFile);
        fos.write(file.getBytes());
        fos.close();
        return convertFile;
    }
}
