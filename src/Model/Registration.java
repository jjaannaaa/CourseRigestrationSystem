package Model;

public class Registration {
    private int id;
    private int st_id;
    private int c_id;

    public Registration(int c_id, int id, int st_id) {
        this.c_id = c_id;
        this.id = id;
        this.st_id = st_id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSt_id() {
        return st_id;
    }

    public void setSt_id(int st_id) {
        this.st_id = st_id;
    }
}
