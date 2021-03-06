package by.itransition.mathtasksapp.services;

import by.itransition.mathtasksapp.models.Image;
import by.itransition.mathtasksapp.models.Task;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ImageService {
    Image save(Image image);
    List<Image> saveImagesByMultipartFileArray(MultipartFile[] fileArray, Task task);
    List<Image> getAllByTaskId(Long id);
    List<Image> updateImagesByMultipartFileArray(MultipartFile[] fileArray, Task task);
    boolean deleteImage(Image image);
}
