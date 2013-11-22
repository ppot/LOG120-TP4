package application.action;

public abstract class Action {
    public abstract void execute();

    public abstract void invert();

    public abstract String getActionName();
}
