package Dados;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Midiateca implements Interador {

    private int contador;

    ArrayList<Midia> midias = new ArrayList<>();
    private Musica musica;
    private Video video;

    public Midiateca() {
    }

    public boolean cadastraMidia(Midia midia) {
        for (Midia m : midias) {
            if (m.getCodigo() == midia.getCodigo()) {
                return false;
            }
        }
        midias.add(midia);
        return true;
    }

    public Midia consultaPorCodigo(int codigo) {
        for (Midia m : midias) {
            if (m.getCodigo() == codigo) {
                return m;
            }
        }
        return null;
    }

    public ArrayList<Midia> consultaPorCategoria(Categoria categoria) {
        ArrayList<Midia> array = new ArrayList<>();
        for (Midia m : midias) {
            if (m.getCategoria().getNome().equals(categoria)) {
                array.add(m);
            }
        }
        return array;
    }

    public double somatorio(){
        double somatorio=0.00;
        for (Midia m: midias){
            somatorio+= m.calculaLocacao();
        }
        return somatorio;
    }

    public Video consultaPorQualidade(int qualidade) {
        for (Midia m : midias) {
            if (m instanceof Video) {
                Video v = (Video) m;
                if (video.getQualidade() == qualidade) {
                    return v;
                }
            }
        }
        return null;
    }


    public boolean removeMidia(int codigo) {
        for (Midia m : midias) {
            if (m.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Musica> musicas(){
        ArrayList<Musica>musicas=new ArrayList<>();
        for (Midia midia: midias){
            if (midia instanceof Musica){
                Musica m = (Musica) midia;
                musicas.add(m);
            }
        }
        return musicas;
    }


    @Override
    public void reset() {
        contador = 0;
    }

    @Override
    public boolean hasNext() {
        if (contador >= midias.size() || midias.get(contador) == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Object next() {
        Midia next = midias.get(contador);
        contador++;
        return next;
    }

    public double Double(double valor) {
        DecimalFormat decimal = new DecimalFormat("0.00");
        return Double.parseDouble(decimal.format(valor));
    }


    public String toString(Midia midia) {
        if (midia instanceof Video) {
            Video video = (Video) midia;
            return video.getCodigo() + ","
                    + video.getTitulo() + ","
                    + video.getAno() + ","
                    + video.getQualidade() + ","
                    + "R$ " + Double(video.calculaLocacao());
        }
        if (midia instanceof Musica) {
            Musica musica = (Musica) midia;
            return musica.getCodigo() + ","
                    + musica.getTitulo() + ","
                    + musica.getAno() + ","
                    + musica.getDuracao() + ","
                    + "R$ " + Double(musica.calculaLocacao());
        }
        return "Objeto nao existe";
    }


}
