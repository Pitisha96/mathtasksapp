package by.itransition.mathtasksapp.services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public interface CloudinaryService {
    Map upload(File file) throws IOException;
    Boolean destroy(String publicId);
}
