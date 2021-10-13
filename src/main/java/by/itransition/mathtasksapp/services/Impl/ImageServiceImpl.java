package by.itransition.mathtasksapp.services.Impl;

import by.itransition.mathtasksapp.models.Image;
import by.itransition.mathtasksapp.models.Task;
import by.itransition.mathtasksapp.repositories.ImageRepository;
import by.itransition.mathtasksapp.services.CloudinaryService;
import by.itransition.mathtasksapp.services.ImageService;
import by.itransition.mathtasksapp.utils.TmpFilesCreator;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, CloudinaryService cloudinaryService) {
        this.imageRepository = imageRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @SneakyThrows
    @Override
    public List<Image> saveImagesByMultipartFileArray(MultipartFile[] fileArray, Task task) {
        List<Image> images=new LinkedList<>();
        for(MultipartFile file:fileArray){
            if(!file.isEmpty()){
                images.add(
                        save(new Image(cloudinaryService
                                .upload(TmpFilesCreator.convertMultipartFileToFile(file)), task))
                );
            }
        }
        return images;
    }
}
