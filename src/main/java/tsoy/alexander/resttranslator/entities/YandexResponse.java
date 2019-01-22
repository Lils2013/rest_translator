package tsoy.alexander.resttranslator.entities;

import java.util.List;

public class YandexResponse {

    private Integer code;

    private String lang;

    private List<String> text;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "YandexResponse{" +
                "code=" + code +
                ", lang='" + lang + '\'' +
                ", text=" + text +
                '}';
    }
}
