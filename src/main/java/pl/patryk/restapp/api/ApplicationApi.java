package pl.patryk.restapp.api;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.patryk.restapp.dao.entity.Entry;
import pl.patryk.restapp.dao.entity.manager.EntryManager;

import java.util.Optional;

/**
 * @author Patryk Kurzeja
 */
@RestController
@RequestMapping("/api/entries")
public class ApplicationApi {

    private final EntryManager entryManager;

    public ApplicationApi(EntryManager entryManager) {
        this.entryManager = entryManager;
    }

    @GetMapping("/all")
    public Iterable<Entry> getEntries() {
        return entryManager.findAll();
    }

    @GetMapping
    public Optional<Entry> getEntry(@RequestParam Long id) {
        return entryManager.findById(id);
    }

    @PostMapping
    public void addEntry(@RequestParam("file") MultipartFile file,
                         @RequestParam("xsd") MultipartFile xsd) {
        entryManager.save(file, xsd);
    }

}
