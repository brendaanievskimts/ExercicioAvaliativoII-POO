package dados;

import java.util.ArrayList;

public class Midiateca implements Iterador {
    private int contador;
    private ArrayList<Midia> midias;

    public Midiateca(){
        midias = new ArrayList<>();
    }

    public boolean cadastraMidia(Midia midia){
        return midias.add(midia);
    }

    public Midia consultaPorCodigo(int codigo){
        if(!midias.isEmpty()){
            for(Midia m : midias){
                if(m.getCodigo() == codigo){
                    return m;
                }
            }
        }
        return null;
    }

    public ArrayList<Midia> consultaPorCategoria(Categoria categoria){
        ArrayList<Midia> array = new ArrayList<>();

        if(!midias.isEmpty()){
            for(Midia m : midias){
                if(m.getCategoria().equals(categoria)){
                    array.add(m);
                }
            }
        }
        return array;
    }

    public boolean removeMidia(int codigo){
        if(!midias.isEmpty()){
            midias.removeIf(m -> m.getCodigo() == codigo);
        }
        return false;
    }

    @Override
    public void reset() {
        contador = 0;
    }

    @Override
    public boolean hasNext() {
        if(contador >= midias.size()) return false;
        return true;
    }

    @Override
    public Object next() {
        if(contador < midias.size()){
            Object retorno = midias.get(contador);
            contador++;
            return retorno;
        }
        return null;
    }
}