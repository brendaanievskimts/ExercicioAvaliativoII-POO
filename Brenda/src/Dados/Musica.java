package Dados;

public class Musica extends Midia {
    private double duracao;

    public Musica(int codigo, String titulo, int ano, Categoria categoria, double duracao) {
        super(codigo, titulo, ano, categoria);
        this.duracao = duracao;
    }

    public double getDuracao() {
        return duracao;
    }

    @Override
    public double calculaLocacao() {
        double valor = 0;

        if (getCategoria().getNome().equals("Acao")) {
            valor = duracao * 0.90;
        }
        if (getCategoria().getNome().equals("Drama")) {
            valor = duracao * 0.70;
        }
        if (getCategoria().getNome().equals("Ficcao")) {
            valor = duracao * 0.50;
        }
        if (getCategoria().getNome().equals("Romance")) {
            valor = duracao * 0.30;
        }
        return valor;
    }
}
