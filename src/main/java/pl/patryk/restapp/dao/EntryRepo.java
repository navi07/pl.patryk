package pl.patryk.restapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.patryk.restapp.dao.entity.Entry;

@Repository
public interface EntryRepo extends CrudRepository<Entry, Long> {
}
