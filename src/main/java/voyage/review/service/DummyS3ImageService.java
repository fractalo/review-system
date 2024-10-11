package voyage.review.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class DummyS3ImageService {

    public String uploadImage(MultipartFile image) {
        return "/" + UUID.randomUUID() + "/" + image.getOriginalFilename();
    }

}
