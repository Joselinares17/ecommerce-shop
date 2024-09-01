package org.linaresworks.dream_shops.infrastructure.api;

import org.linaresworks.dream_shops.application.service.IImageService;
import org.linaresworks.dream_shops.infrastructure.model.dto.ImageDto;
import org.linaresworks.dream_shops.infrastructure.model.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("${api.prefix}/images")
public class ImageController {
    private final IImageService imageService;

    public ImageController(IImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> saveImages(@RequestParam Long productId, @RequestParam List<MultipartFile> files) {
        try {
            List<ImageDto> imageDtos = imageService.savesImage(files, productId);
            return ResponseEntity.ok(new ApiResponse("upload success", imageDtos));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("upload failed", e.getMessage()));
        }
    }
}
