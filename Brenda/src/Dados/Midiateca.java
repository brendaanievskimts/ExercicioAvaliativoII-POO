package Dados;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Midiateca implements Interador {

    private int contador;
    ArrayList<Midia> midias = new ArrayList<>();

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

    public double somatorio() {
        double somatorio = 0.00;
        for (Midia m : midias) {
            somatorio += m.calculaLocacao();
        }
        return somatorio;
    }

    public Video consultaPorQualidade(int qualidade) {
        for (Midia m : midias) {
            if (m instanceof Video) {
                Video v = (Video) m;
                if (v.getQualidade() == qualidade) {
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

    public ArrayList<Musica> musicas() {
        ArrayList<Musica> musicas = new ArrayList<>();
        for (Midia midia : midias) {
            if (midia instanceof Musica) {
                Musica m = (Musica) midia;
                musicas.add(m);
            }
        }
        return musicas;
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

   public double mediaLocacao(){
       double soma = 0.0;
       double media= 0.0;
       for (Musica musica : musicas()) {
           soma += musica.calculaLocacao();
       }
        media= soma / musicas().size();


       return  media;
   }

    public Midia musicaProxima() {

        if (musicas().isEmpty()) {
            return null;
        } else {

            Musica mProxima = null;
            double soma = 0.0;
            for (Musica musica : musicas()) {
                soma += musica.calculaLocacao();
            }

            double media = soma / musicas().size();
            double menorDif = Double.MAX_VALUE;
            for (Musica musica : musicas()) {
                double diferenca = Math.abs(musica.calculaLocacao() - media);
                if (diferenca < menorDif) {
                    menorDif = diferenca;
                    mProxima = musica;
                }
            }
            return mProxima;
        }
    }


    public Midia midiaRecente() {
        if (midias.isEmpty()) {
            return null;
        }

        Midia recente = midias.get(0);
        for (Midia m : midias) {
            if (m.getAno() > recente.getAno()) {
                recente = m;
            }
        }
        return recente;
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


}
