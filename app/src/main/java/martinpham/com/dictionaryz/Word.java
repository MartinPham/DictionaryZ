package martinpham.com.dictionaryz;

import java.io.Serializable;

public class Word  implements Serializable {
    static final long serialVersionUID = 727566175075960653L;
    private String name;
    private String description;
    private String content;
    private String backgroundImageUrl;
    private String cardImageUrl;
    private String videoUrl;


    public Word() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getBackgroundImageUrl() {
        return backgroundImageUrl;
    }

    public void setBackgroundImageUrl(String backgroundImageUrl) {
        this.backgroundImageUrl = backgroundImageUrl;
    }

    public String getCardImageUrl() {
        return cardImageUrl;
    }

    public void setCardImageUrl(String cardImageUrl) {
        this.cardImageUrl = cardImageUrl;
    }

    @Override
    public String toString() {
        return "Word{" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}