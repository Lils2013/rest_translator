package tsoy.alexander.resttranslator;

import org.springframework.data.repository.CrudRepository;
import tsoy.alexander.resttranslator.entities.TranslationLog;

public interface LogRepository extends CrudRepository<TranslationLog, Long> {
}
