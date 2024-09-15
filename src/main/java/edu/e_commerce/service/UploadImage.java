package edu.e_commerce.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Classe responsavel pela regra de negocio de ao fazer upload de uma imagem
 */

@Service
public class UploadImage {


    @Value("${product.image.directory}")
    private String imageDirectory;

    /**
     * Meotodo responsavel por salvar uma imagem
     *
     * @param image
     * @return
     * @throws IOException
     */

    public String saveImage(MultipartFile image) throws IOException {
        String imageName = UUID.randomUUID().toString() + "-" + image.getOriginalFilename();
        Path imagePath = Paths.get(imageDirectory, imageName);
        Files.copy(image.getInputStream(), imagePath);
        return imagePath.toString();
    }
}