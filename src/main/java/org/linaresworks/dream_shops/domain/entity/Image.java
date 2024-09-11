package org.linaresworks.dream_shops.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;

    @Lob
    private Blob image;
    private String downloadUrl;

    @ManyToOne(targetEntity = Product.class)
    private Product product;

    //TODO: revisar constructor
    public Image(Long imageId, String imageName, String downloadUrl) {
    }
}
