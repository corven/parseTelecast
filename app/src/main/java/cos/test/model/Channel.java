package cos.test.model;

public class Channel {
    private String name;
    private int numb;

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

    public Channel(String name, int numb) {
        this.name = name;
        this.numb = numb;
    }
}
