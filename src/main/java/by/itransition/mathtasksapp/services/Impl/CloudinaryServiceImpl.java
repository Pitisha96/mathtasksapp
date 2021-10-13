package by.itransition.mathtasksapp.services.Impl;

import by.itransition.mathtasksapp.services.CloudinaryService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String upload(File file) throws IOException {
        return (String) cloudinary.uploader().upload(file, ObjectUtils.asMap(
                "folder","mathTasks/"
        )).get("url");
    }
}
