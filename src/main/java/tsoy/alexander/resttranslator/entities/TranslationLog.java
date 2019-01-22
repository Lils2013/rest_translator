package tsoy.alexander.resttranslator.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Entity
public class TranslationLog {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private ZonedDateTime dateTime;
    private String inputText;
    private String translatedText;
    private String lang;
    private TranslationStatus status;

    protected TranslationLog() {}

    public TranslationLog(String inputText, String translatedText, String lang) {
        this.dateTime = ZonedDateTime.now();
        this.inputText = inputText;
        this.translatedText = translatedText;
        this.lang = lang;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public void setStatus(TranslationStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public TranslationStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "TranslationLog{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", inputText='" + inputText + '\'' +
                ", translatedText='" + translatedText + '\'' +
                ", lang='" + lang + '\'' +
                ", status=" + status +
                '}';
    }
}
