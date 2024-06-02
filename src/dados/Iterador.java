package dados;

public interface Iterador {
    public void reset();

    public boolean hasNext();

    public Object next();

    String toString(Midia midia);
}
