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
        cadastrarVideos(); //1 - ok
        cadastrarMusicas(); //2 - ok
        dadosMidia(); //3 - ok
        dadosMidiaCategoria(); //4 - ok
        dadosMidiaQualidade(); //5 - ok
        dadosMusicaDuracao(); //6 - ok
        removerMidia(); //7 -
        //somatorioTotal(); //8

    }

    //1 - ok
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

            Video v = (Video) midiateca.consultaPorCodigo(codigo);

            if(v != null){
                System.out.println(STR."1:Erro-video com codigo repetido: \{codigo}");
            } else {
                Video video = new Video(codigo, titulo, ano, categoria, qualidade);
                midiateca.cadastraMidia(video);
                System.out.println(STR."1:\{codigo},\{titulo},\{ano},\{categoria},\{qualidade}");
            }
            codigo = Integer.parseInt(entrada.nextLine());
        }
    }

    //2 - ok
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

            Musica m = (Musica) midiateca.consultaPorCodigo(codigo);

            if(m != null){
                System.out.println("2:Erro-musica com codigo repetido: " + codigo + ".");

            } else {
                Musica musica = new Musica(codigo, titulo, ano, categoria, duracao);
                midiateca.cadastraMidia(musica);
                System.out.println(STR."2:\{codigo},\{titulo},\{ano},\{categoria},\{duracao}");
            }
            codigo = Integer.parseInt(entrada.nextLine());
        }
    }

    //3 - ok
    private void dadosMidia(){
        int codigo = Integer.parseInt(entrada.nextLine());

        Midia m = midiateca.consultaPorCodigo(codigo);

        if(m == null){
            System.out.println("3:Codigo inexistente. ");
        } else {
            System.out.println("3:" + midiateca.toString(m));
        }
    }

    //4 - ok
    private void dadosMidiaCategoria(){
        Categoria categoria = Categoria.valueOf(entrada.nextLine());
        List<Midia> midia = midiateca.consultaPorCategoria(categoria);

        if(midia.isEmpty()){
            System.out.println("4:Nenhuma midia encontrada.");
        } else{
            for(Midia m : midia){
                System.out.println("4:" + midiateca.toString((Midia) m));
            }
        }
    }


    //5 - ok
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


    //6 - ok
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
            System.out.println("6:" + musicaAux.getTitulo() + "," + musicaAux.getDuracao());
        }
    }


    //7 - ok
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


    //8
    private void somatorioTotal(){

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
