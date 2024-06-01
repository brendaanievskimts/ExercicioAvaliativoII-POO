package App;

import Dados.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;

public class ACMEMidia {
    private Scanner entrada = null;
    private PrintStream saidaPadrao = System.out;
    private Midiateca midiateca = new Midiateca();

    public ACMEMidia() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader("dadosin.txt"));
            entrada = new Scanner(streamEntrada);
            PrintStream streamSaida = new PrintStream(new File("dadosout.txt"), Charset.forName("UTF-8"));
            System.setOut(streamSaida);
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);
        entrada.useLocale(Locale.ENGLISH);
    }

    public void executar() {
        cadastraVideo();
        cadastraMusica();
        dadosMidia();
        mostrarCategorias();
        mostrarQualidade();
        maiorDuracao();
        removeMidia();
        //PontoExtra
        maisProximo();

    }

    // 1111111111111111111111111111111111111
    public void cadastraVideo() {
        while (!entrada.nextLine().equals("-1")) {
            String[] conteudo = entrada.nextLine().split(";");
            if (conteudo.length >= 4) {
                int codigo = Integer.parseInt(conteudo[0]);
                String titulo = conteudo[1];
                int ano = Integer.parseInt(conteudo[2]);
                String categoria = conteudo[3];
                int qualidade = Integer.parseInt(conteudo[4]);
                if (midiateca.cadastraMidia(new Video(codigo, titulo, ano, Categoria.valueOf(categoria), qualidade))) {
                    System.out.println("1 :" + codigo + " ," + titulo + " ," + ano + " ," + categoria + " ," + qualidade);
                } else {
                    System.out.println("1: Erro-video com codigo repitido: " + codigo);
                }

            }
        }
    }

    //2222222222222222222222222222222222222
    public void cadastraMusica() {
        while (!entrada.nextLine().equals("-1")) {
            String[] conteudo = entrada.nextLine().split(";");
            if (conteudo.length >= 4) {
                int codigo = Integer.parseInt(conteudo[0]);
                String titulo = conteudo[1];
                int ano = Integer.parseInt(conteudo[2]);
                String categoria = conteudo[3];
                double duracao = Double.parseDouble(conteudo[4]);
                if (midiateca.cadastraMidia(new Musica(codigo, titulo, ano, Categoria.valueOf(categoria), duracao))) {
                    System.out.println("2 :" + codigo + " ," + titulo + " ," + ano + " ," + categoria + " ," + duracao);
                } else {
                    System.out.println("2: Erro-musica com codigo repitido: " + codigo);
                }

            }
        }
    }

    //3333333333333333333333333333333333333
    public void dadosMidia() {
        if (midiateca.consultaPorCodigo(Integer.parseInt(entrada.nextLine())) == null) {
            System.out.println("3: Codigo inexistente.");
        } else {
            Midia m = midiateca.consultaPorCodigo(Integer.parseInt(entrada.nextLine()));
            System.out.println("3: " + midiateca.toString(midiateca.consultaPorCodigo(Integer.parseInt(entrada.nextLine()))));
        }
    }

    //44444444444444444444444444444444444444
    public void mostrarCategorias() {
        if (midiateca.consultaPorCategoria(Categoria.valueOf(entrada.nextLine())).isEmpty()) {
            System.out.println("4: Nenhuma midia encontrada.");
        } else {
            for (Midia m : midiateca.consultaPorCategoria(Categoria.valueOf(entrada.nextLine()))) {
                System.out.println("4 : " + midiateca.toString(midiateca.consultaPorCodigo(Integer.parseInt(entrada.nextLine()))));
            }
        }
    }

    //55555555555555555555555555555555555555
    public void mostrarQualidade() {
        if (midiateca.consultaPorQualidade(Integer.parseInt(entrada.nextLine())) == null) {
            System.out.println("5: Qualidade inexistete.");
        } else {
            System.out.println(midiateca.toString(midiateca.consultaPorQualidade(Integer.parseInt(entrada.nextLine()))));
        }
    }

    //666666666666666666666666666666
    public void maiorDuracao() {
        if (midiateca.musicas().isEmpty()) {
            System.out.println("6: Nenhuma música encontrada.");
        } else {
            for (Musica musica : midiateca.musicas()) {
                System.out.println("6: " + musica.getTitulo() + "," + musica.getDuracao());
            }
        }
    }

    //7777777777777777777777777777777
    public void removeMidia() {
        if (midiateca.removeMidia(Integer.parseInt(entrada.nextLine()))) {
            System.out.println("7: " + midiateca.toString(midiateca.consultaPorCodigo(Integer.parseInt(entrada.nextLine()))));
        } else {
            System.out.println("7: Codigo inexistente");
        }
    }

    //888888888888888888888888888888888
    public void somaMidia() {
        if (midiateca.somatorio() == 0.00) {
            System.out.println("8 :Nenhuma midia encontrada.");
        } else {
            System.out.println("8: " + midiateca.somatorio());
        }
    }

    // PONTO EXTRAAAAAAAAAAAAAAAAAAAAAAAAAAAA
    
    public void maisProximo() {
        if (midiateca.musicaProxima() == null) {
            System.out.println("9: Nenhuma musica encontrada.");
        } else {
            System.out.println("9: " + midiateca.mediaLocacao() + "," + midiateca.toString(midiateca.musicaProxima()));
        }
    }


}
