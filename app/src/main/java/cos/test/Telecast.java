package cos.test;

public class Telecast {
    private String name;
    private String time;
    private boolean isPainted;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isPainted() {
        return isPainted;
    }

    public void setIsPainted(boolean isPainted) {
        this.isPainted = isPainted;
    }

    public Telecast(String name, String time, boolean isPainted) {
        this.name = name;
        this.time = time;
        this.isPainted = isPainted;
    }

    public Telecast(String name, String time) {
        this.name = name;
        this.time = time;
        this.isPainted = false;
    }
}
