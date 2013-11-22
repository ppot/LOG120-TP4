package application.tools;

import application.model.Thumbnail;

public interface Subject {
    public void register(Observer obj);

    public void unregister(Observer obj);

    public void notifyObservers();

    public Thumbnail getUpdate(Observer obj);
}
