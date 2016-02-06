package cos.test.model;

public class Channel {
    private String name;
    private int numb;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumb() {
        return numb;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Channel(String name, int numb, String url) {
        this.name = name;
        this.numb = numb;
        this.url = url;
    }
}
