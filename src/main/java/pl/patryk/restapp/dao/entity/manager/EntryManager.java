package pl.patryk.restapp.dao.entity.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.patryk.restapp.dao.EntryRepo;
import pl.patryk.restapp.dao.entity.Entry;
import pl.patryk.restapp.utils.XMLUtils;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EntryManager {

    private static final String NEWSPAPER_NAME_ATTRIBUTE = "newspaperName";
    private static final String SCREEN_INFO_ATTRIBUTE = "screenInfo";
    private static final String WIDTH_ATTRIBUTE = "width";
    private static final String HEIGHT_ATTRIBUTE = "height";
    private static final String DPI_ATTRIBUTE = "dpi";

    private final EntryRepo entryRepo;

    @Autowired
    public EntryManager(EntryRepo entryRepo) {
        this.entryRepo = entryRepo;
    }

    public Optional<Entry> findById(Long id) {
        return entryRepo.findById(id);
    }

    public Iterable<Entry> findAll() {
        return entryRepo.findAll();
    }

    public void save(MultipartFile file, MultipartFile xsd) {
        boolean validation = XMLUtils.validateXMLSchema(file, xsd);
        if (validation) {
            entryRepo.save(mapFileToEntry(file));
        }
    }

    private Entry mapFileToEntry(MultipartFile file) {
        String newspaperName = XMLUtils.readSimpleAttribute(NEWSPAPER_NAME_ATTRIBUTE, file);
        int width = Integer.parseInt(XMLUtils.readNestedAttribute(SCREEN_INFO_ATTRIBUTE, WIDTH_ATTRIBUTE, file));
        int height = Integer.parseInt(XMLUtils.readNestedAttribute(SCREEN_INFO_ATTRIBUTE, HEIGHT_ATTRIBUTE, file));
        int dpi = Integer.parseInt(XMLUtils.readNestedAttribute(SCREEN_INFO_ATTRIBUTE, DPI_ATTRIBUTE, file));
        String fileName = file.getOriginalFilename();
        LocalDateTime uploadTime = LocalDateTime.now();

        return new Entry(fileName, uploadTime, newspaperName, width, height, dpi);
    }

    public void deleteById(Long id) {
        entryRepo.deleteById(id);
    }
}
