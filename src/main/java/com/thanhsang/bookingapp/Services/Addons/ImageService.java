package com.thanhsang.bookingapp.Services.Addons;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.thanhsang.bookingapp.Interfaces.Addons.ImageInterface;
import com.thanhsang.bookingapp.Models.Addons.ImageModel;
import com.thanhsang.bookingapp.Repositories.Addons.ImageRepository;
import com.thanhsang.bookingapp.Utils.GenerateId;

@Service
public class ImageService implements ImageInterface{

    @Autowired ImageRepository imageRepository;

    @Override
    public ImageModel selectById(Long id) throws Exception {
        
        ImageModel defaultImage = imageRepository.findById(1L).get();
        Optional<ImageModel> foundImage = imageRepository.findById(id);
        return foundImage.isPresent() ? foundImage.get() : defaultImage;
    }

    @Override
    public ImageModel insert(MultipartFile image) throws Exception {

        String imageName = StringUtils.cleanPath(image.getOriginalFilename());
        ImageModel imageModel = new ImageModel(GenerateId.generateId(), imageName, image.getBytes());

        return imageRepository.save(imageModel);
    }
    
}
