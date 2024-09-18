package app;

import dados.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class ACMEMidia {
    private Scanner entrada = new Scanner(System.in);
    private PrintStream saidaPadrao = System.out;
    private final String nomeArquivoEntrada = "entrada.txt";
    private final String nomeArquivoSaida = "saida.txt";
    private Midiateca midiateca;

    public ACMEMidia(){
        midiateca = new Midiateca();
        redirecionaES();

    }

    public void executar(){
        cadastrarVideos();
        cadastrarMusicas();
        dadosMidia();
        dadosMidiaCategoria();
        dadosMidiaQualidade();
        dadosMusicaDuracao();
        removerMidia();
        somatorioTotal();
        musicaMaisProxima();
        midiaMaisRecente();

    }

    private void cadastrarVideos(){
        int codigo;
        String titulo;
        int ano;
        Categoria categoria;
        int qualidade;
        codigo = Integer.parseInt(entrada.nextLine());

        while(codigo != -1){
            titulo = entrada.nextLine();
            ano = Integer.parseInt(entrada.nextLine());
            categoria = Categoria.valueOf(entrada.nextLine());
            qualidade = Integer.parseInt(entrada.nextLine());

            Video video = new Video(codigo, titulo, ano, categoria, qualidade);

            if(midiateca.cadastraMidia(video)) {
                System.out.println("1:" + codigo+ ","+ titulo+ "," + ano + "," + categoria.getNome() + "," + qualidade);
            } else {
                System.out.println("1:Erro-video com codigo repetido:" + codigo);
            }
            codigo = Integer.parseInt(entrada.nextLine());
        }
    }

    private void cadastrarMusicas(){
        int codigo;
        String titulo;
        int ano;
        Categoria categoria;
        double duracao;
        codigo = Integer.parseInt(entrada.nextLine());

        while(codigo != -1){
            titulo = entrada.nextLine();
            ano = Integer.parseInt(entrada.nextLine());
            categoria = Categoria.valueOf(entrada.nextLine());
            duracao = Double.parseDouble(entrada.nextLine());

            Musica musica = new Musica(codigo, titulo, ano, categoria, duracao);

            if(midiateca.cadastraMidia(musica)){
                System.out.println("2:" + codigo + "," + titulo + "," + ano + "," + categoria.getNome() + "," + duracao);
            } else{
                System.out.println("2:Erro-musica com codigo repetido: " + codigo + ".");
            }
            codigo = Integer.parseInt(entrada.nextLine());
        }
    }

    private void dadosMidia(){
        int codigo = Integer.parseInt(entrada.nextLine());

        Midia m = midiateca.consultaPorCodigo(codigo);

        if(m == null){
            System.out.println("3:Codigo inexistente. ");
        } else {
            System.out.println("3:" + midiateca.toString(m));
        }
    }

    private void dadosMidiaCategoria(){
        try {
            Categoria categoria = Categoria.valueOf(entrada.nextLine());
            List<Midia> midia = midiateca.consultaPorCategoria(categoria);

            if (midia.isEmpty()) {
                System.out.println("4:Nenhuma midia encontrada.");
            } else {
                for (Midia m : midia) {
                    System.out.println("4:" + midiateca.toString((Midia) m));
                }
            }
        } catch (IllegalArgumentException e){
            System.out.println("4:Nenhuma midia encontrada.");
        }
    }

    private void dadosMidiaQualidade(){
        int qualidade = Integer.parseInt(entrada.nextLine());

        List<Midia> midia = midiateca.dadosVideoQualidade(qualidade);

        if(midia.isEmpty()){
            System.out.println("5:Qualidade inexistente.");
        } else{
            for(Midia m : midia){
                System.out.println("5:" + midiateca.toString(m));
            }
        }
    }

    private void dadosMusicaDuracao(){
        Musica musicaAux = null;

        if(midiateca.dadosMusicaDuracao().equals(null)){
            System.out.println("6:Nenhuma música encontrada.");
        } else {
            for (Musica musica : midiateca.dadosMusicaDuracao()) {
                if(musicaAux == null || musica.getDuracao() >= musicaAux.getDuracao()){
                    musicaAux = musica;
                }
            }
            assert musicaAux != null;
            System.out.println("6:" + musicaAux.getTitulo() + "," + musicaAux.getDuracao());
        }
    }

    private void removerMidia(){
        int codigo = Integer.parseInt(entrada.nextLine());

        Midia m = midiateca.consultaPorCodigo(codigo);

        if(m == null){
            System.out.println("7:Codigo inexistente.");
        } else {
            System.out.println("7:" + midiateca.toString(m));
            midiateca.removeMidia(codigo);
        }
    }


    private void somatorioTotal(){
        double somatorio = midiateca.somatorioTotal();

        if(somatorio == 0){
            System.out.println("8:Nenhuma midia encontrada");
        } else{
            System.out.println("8:" + somatorio);
        }
    }

    private void musicaMaisProxima(){
        Midia midia = midiateca.musicaProxima();
        double media = midiateca.mediaLocacao();

        if(midia == null){
            System.out.println("9:Nenhuma musica encontrada.");
        } else {
            System.out.println("9:" + media + "," + midiateca.toString(midia));
        }
    }

    public void midiaMaisRecente() {
        Midia midiaRecente = midiateca.midiaRecente();

        if (midiaRecente == null) {
            System.out.println("10:Nenhuma midia encontrada.");
        } else {
            System.out.println("10:" + midiaRecente.getCodigo() + "," + midiaRecente.getTitulo()+ "," + midiaRecente.getAno());
        }
    }

    private void redirecionaES() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader(nomeArquivoEntrada));
            entrada = new Scanner(streamEntrada);
            PrintStream streamSaida = new PrintStream(new File(nomeArquivoSaida), Charset.forName("UTF-8"));
            System.setOut(streamSaida);
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);
        entrada.useLocale(Locale.ENGLISH);
    }

    private void restauraES() {
        System.setOut(saidaPadrao);
        entrada = new Scanner(System.in);
    }

}