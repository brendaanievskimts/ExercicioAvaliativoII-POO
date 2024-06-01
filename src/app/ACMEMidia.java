package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;

public class ACMEMidia {
    private Scanner entrada = new Scanner(System.in);
    private PrintStream saidaPadrao = System.out;
    private final String nomeArquivoEntrada = "dadosin.txt";
    private final String nomeArquivoSaida = "dadosout.txt";

    public ACMEMidia(){
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

    }

    //1
    private void cadastrarVideos(){

    }

    //2
    private void cadastrarMusicas(){

    }

    //3
    private void dadosMidia(){

    }

    //4
    private void dadosMidiaCategoria(){

    }


    //5
    private void dadosMidiaQualidade(){

    }


    //6
    private void dadosMusicaDuracao(){

    }


    //7
    private void removerMidia(){

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
