import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
class MetaUploadController04 {

    // TODO: @PostMapping(value = "/with-meta", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadWithMeta(
            /* @RequestPart("file") */ MultipartFile file,
            /* @RequestPart("meta") */ AttachmentMeta04 meta) {
        // TODO: верните "Файл " + file.getOriginalFilename() + " '" + meta.title()
        //       + "' от " + meta.author() + ", размер " + file.getSize()
        return null;
    }
}
