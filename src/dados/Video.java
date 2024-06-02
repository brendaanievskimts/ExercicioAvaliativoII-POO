package dados;

public class Video extends Midia{
    private int qualidade;

    public Video(int codigo, String titulo, int ano, Categoria categoria, int qualidade) {
        super(codigo, titulo, ano, categoria);
        this.qualidade = qualidade;
    }

    public int getQualidade() {
        return qualidade;
    }

    @Override
    public double calculaLocacao() {
        double valor = 0.0;

        if(getAno() == 2024){
            valor = 20.0;
        } else if(getAno() >= 2000 && getAno() <= 2023){
            valor = 15.0;
        } else if(getAno() < 2000){
            valor = 10.0;
        }
        return valor;
    }
}