package project.musicapp.api.files;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;


@Service
public class FileService {
    private String getStringPath(String folder, String fileName) {
        return String.format("%s/%s/%s", "static", folder, fileName);
    }

    private ClassPathResource getResource(String folder, String fileName) {
        String path = getStringPath(folder, fileName);
        return new ClassPathResource(path);
    }

    private byte[] getBytesFromResource(ClassPathResource resource) throws IOException {
        try (InputStream inputStream = resource.getInputStream()) {
            return FileCopyUtils.copyToByteArray(inputStream);
        }
    }

    public ResponseEntity<byte[]> getFile(String folder, String fileName) throws IOException {
        ClassPathResource resource = getResource(folder, fileName);

        if (!resource.exists())
            return ResponseEntity.notFound().build();

        byte[] data = getBytesFromResource(resource);
        return ResponseEntity.ok().body(data);
    }
}
