package org.linaresworks.dream_shops.application.business;

import org.linaresworks.dream_shops.application.service.IImageService;
import org.linaresworks.dream_shops.application.service.IProductService;
import org.linaresworks.dream_shops.domain.entity.Image;
import org.linaresworks.dream_shops.domain.repository.ImageRepository;
import org.linaresworks.dream_shops.infrastructure.exception.ResourceNotFoundException;
import org.linaresworks.dream_shops.infrastructure.model.mapper.ProductMapper;
import org.linaresworks.dream_shops.infrastructure.model.response.ImageResponse;
import org.linaresworks.dream_shops.infrastructure.model.response.ProductResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageBusiness implements IImageService {
    private final ImageRepository imageRepository;
    private final IProductService productService;
    private final ProductMapper productMapper;

    public ImageBusiness(ImageRepository imageRepository, IProductService productService, ProductMapper productMapper) {
        this.imageRepository = imageRepository;
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no image found with id: " + id));
    }

    @Override
    @Transactional
    public void deleteImageById(Long id) {
        imageRepository.findById(id)
                .ifPresentOrElse(imageRepository::delete, () -> {
                   throw new ResourceNotFoundException("no image found with id: " + id);
                });
    }

    // TODO: Cambiar esta lógica.
    @Override
    @Transactional
    public List<ImageResponse> savesImage(List<MultipartFile> files, Long productId) {
        ProductResponse product = productService.getProductById(productId);
        List<ImageResponse> savedImageResponse = new ArrayList<>();
        for(MultipartFile file : files) {
            try {
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(productMapper.fromResponse(product));

                String buildDownloadUrl = "/api/v1/images/image/download/";
                String downloadUrl = buildDownloadUrl + image.getId();

                image.setDownloadUrl(downloadUrl);
                Image savedImage = imageRepository.save(image);

                savedImage.setDownloadUrl(buildDownloadUrl + savedImage.getId());
                imageRepository.save(savedImage);

                ImageResponse imageResponse = new ImageResponse();
                imageResponse.setImageId(savedImage.getId());
                imageResponse.setImageName(savedImage.getFileName());
                imageResponse.setDownloadUrl(savedImage.getDownloadUrl());
                savedImageResponse.add(imageResponse);

            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return savedImageResponse;
    }

    @Override
    @Transactional
    public void updateImage(MultipartFile file, Long imageId) {
        Image image = getImageById(imageId);

        try {
            image.setFileName(file.getOriginalFilename());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
