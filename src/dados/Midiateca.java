package dados;

import java.text.DecimalFormat;
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
            return true;
        }
        return false;
    }

    public ArrayList<Midia> dadosVideoQualidade(int qualidade){
        ArrayList<Midia> array = new ArrayList<>();

        if(!midias.isEmpty()){
            for(Midia m : midias){
                if(m instanceof Video){
                    Video video = (Video) m;
                    if(video.getQualidade() == qualidade){
                        array.add(video);
                    }
                }
            }
        }
        return array;
    }

    public ArrayList<Musica> dadosMusicaDuracao(){
        ArrayList<Musica> array = new ArrayList<>();

        if(!midias.isEmpty()){
            for(Midia midia : midias){
                if(midia instanceof Musica) {
                    array.add((Musica) midia);
                }
            }
        }
        return array;
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

    public double Double(double valor) {
        DecimalFormat decimal = new DecimalFormat("0.00");
        return Double.parseDouble(decimal.format(valor));
    }

    @Override
    public String toString(Midia midia) {
        if (midia instanceof Video) {
            Video video = (Video) midia;
            return video.getCodigo() + ","
                    + video.getTitulo() + ","
                    + video.getAno() + ","
                    + video.getCategoria() + ","
                    + video.getQualidade() + ","
                    + Double(video.calculaLocacao());
        }
        if (midia instanceof Musica) {
            Musica musica = (Musica) midia;
            return musica.getCodigo() + ","
                    + musica.getTitulo() + ","
                    + musica.getAno() + ","
                    + musica.getCategoria() + ","
                    + musica.getDuracao() + ","
                    + Double(musica.calculaLocacao());
        }
        return "Objeto nao existe";
    }

}