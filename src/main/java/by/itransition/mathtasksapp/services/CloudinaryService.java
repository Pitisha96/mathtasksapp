package by.itransition.mathtasksapp.services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public interface CloudinaryService {
    String upload(File file) throws IOException;
}
