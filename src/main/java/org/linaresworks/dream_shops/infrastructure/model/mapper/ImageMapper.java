package org.linaresworks.dream_shops.infrastructure.model.mapper;

import org.linaresworks.dream_shops.domain.entity.Image;
import org.linaresworks.dream_shops.infrastructure.model.dto.ImageDTO;
import org.linaresworks.dream_shops.infrastructure.model.response.ImageResponse;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ImageMapper implements Function<Image, ImageResponse>{
    @Override
    public ImageResponse apply(Image image) {
        if(image == null) return null;

        return new ImageResponse(
                image.getId(),
                image.getFileName(),
                image.getDownloadUrl()
        );
    }

    public Image fromResponse(ImageResponse response) {
        if(response == null) return null;

        return new Image(
                response.getImageId(),
                response.getImageName(),
                response.getDownloadUrl()
        );
    }

    public ImageDTO toImageDTO(Image image) {
        if(image == null) return null;

        return new ImageDTO(
                image.getId(),
                image.getFileName(),
                image.getFileType(),
                image.getImage(),
                image.getDownloadUrl()
        );
    }

    public Image fromImageDTO(ImageDTO imageDTO) {
        if(imageDTO == null) return null;

        return new Image(
                imageDTO.getId(),
                imageDTO.getFileName(),
                imageDTO.getFileType(),
                imageDTO.getImage(),
                imageDTO.getDownloadUrl()
        );
    }
}
