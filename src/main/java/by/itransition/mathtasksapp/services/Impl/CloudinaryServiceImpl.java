package by.itransition.mathtasksapp.services.Impl;

import by.itransition.mathtasksapp.services.CloudinaryService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public Map upload(File file) throws IOException {
        return cloudinary.uploader().upload(file, ObjectUtils.asMap(
                "folder","mathTasks/"
        ));
    }

    @SneakyThrows
    @Override
    public Boolean destroy(String publicId) {
        return cloudinary.uploader().
                destroy(publicId,ObjectUtils.emptyMap()).get("result").equals("ok");
    }
}
