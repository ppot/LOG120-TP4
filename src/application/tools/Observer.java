package application.tools;

public interface Observer {
    public void update();

    public void setSubject(Subject sub);
}
