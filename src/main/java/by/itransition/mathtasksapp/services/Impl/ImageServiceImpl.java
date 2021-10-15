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
                Map cloudImage=cloudinaryService.upload(TmpFilesCreator.convertMultipartFileToFile(file));
                images.add(
                        save(new Image((String)cloudImage.get("url"),(String)cloudImage.get("public_id") ,task))
                );
            }
        }
        return images;
    }

    @Override
    public List<Image> getAllByTaskId(Long id) {
        return imageRepository.findAllByTask(new Task(id));
    }

    @SneakyThrows
    @Override
    public List<Image> updateImagesByMultipartFileArray(MultipartFile[] fileArray, Task task) {
        List<Image> result = new LinkedList<>();
        List<Image> images = getAllByTaskId(task.getId());
        for(int i=0;i<fileArray.length;i++){
            if(!fileArray[i].isEmpty()){
                Map cloudImage = cloudinaryService
                        .upload(TmpFilesCreator.convertMultipartFileToFile(fileArray[i]));
                result.add(
                        save(new Image((String)cloudImage.get("url"),(String)cloudImage.get("public_id"),task))
                );
                if(images.size()>=i+1)
                    deleteImage(images.get(i));
            }else if(images.size()>=i+1){
                result.add(images.get(i));
            }
        }
        return result;
    }

    @Override
    public boolean deleteImage(Image image) {
        cloudinaryService.destroy(image.getPublicId());
        imageRepository.delete(image);
        return false;
    }
}
