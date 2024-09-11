package org.linaresworks.dream_shops.infrastructure.model.mapper;

import org.linaresworks.dream_shops.domain.entity.Image;
import org.linaresworks.dream_shops.infrastructure.model.response.ImageResponse;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ImageMapper implements Function<Image, ImageResponse>{
    @Override
    public ImageResponse apply(Image image) {
        return new ImageResponse(
                image.getId(),
                image.getFileName(),
                image.getDownloadUrl()
        );
    }

    public Image fromResponse(ImageResponse response) {
        return new Image(
                response.getImageId(),
                response.getImageName(),
                response.getDownloadUrl()
        );
    }
}
