package tec.monster.Observers;

/**
 * Clase abstracta para crea observadores para la clase Subject
 *
 * su unico método se encarga de ser notificado de los cambios en el Subject
 */
public abstract class Observer {

    protected Subject subject;
    public abstract void update();
}
