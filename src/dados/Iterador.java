package dados;

public interface Iterador {
    public abstract void reset();

    public abstract boolean hasNext();

    public abstract Object next();

    String toString(Midia midia);
}