package Dados;

public class Video extends Midia {
    private int qualidade;

    public int getQualidade() {
        return qualidade;
    }

    public Video(int codigo, String titulo, int ano, Categoria categoria, int qualidade) {
        super(codigo, titulo, ano, categoria);
        this.qualidade = qualidade;
    }

    @Override
    public double calculaLocacao() {
        double valor = 0;
        if (getAno() == 2024) {
            valor = 20.00;
        }
        if (getAno()<= 2023 || getAno() >= 2000){
            valor = 15.00;
        }
        if (getAno() < 2000){
            valor = 10.00;
        }
        return valor;
    }
}
