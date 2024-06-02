package dados;

public class Musica extends Midia{
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
        double valor = 0.0;

        if(getCategoria().equals(Categoria.ACA)) {
            valor = duracao * 0.90;
        } else if(getCategoria().equals(Categoria.DRA)) {
            valor = duracao * 0.70;
        } else if(getCategoria().equals(Categoria.FIC)){
            valor = duracao * 0.50;
        } else if(getCategoria().equals(Categoria.ROM)){
            valor = duracao * 0.30;
        }
        return valor;
    }
}
