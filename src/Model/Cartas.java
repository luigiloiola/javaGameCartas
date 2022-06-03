package Model;

import Controller.KeyHandler;

import java.util.HashMap;

public class Cartas {
    public String conteudo;
    public static Cartas head;
    public Cartas par;
    public Cartas next;
    public int pos;
    public boolean aberto;
    public char tecla;
    public KeyHandler jogador;
    public boolean out = false;


    public Cartas(String conteudo) {
        this.conteudo = conteudo;
    }

    @SuppressWarnings("unused")
    public void print(Cartas head) {
        Cartas curr = head;
        while (curr.next != null) {
            curr = curr.next;
            System.out.println(curr.conteudo);
            System.out.println(curr.par.conteudo);
            System.out.println(curr.par.par.conteudo);
            System.out.println("\n");
        }
    }

    private static void append(Cartas head, Cartas carta) {
        Cartas curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = carta;
    }

    public int getSize(){
        int i = 0;

        Cartas curr = head;
        while (curr.next !=null) {
            curr = curr.next;
            i++;
        }
        return i;
    }

    public static Cartas gerarCartas() {
        head = new Cartas(null);
        HashMap<String, String> cartasPR = new HashMap<>();
        cartasPR.put("pergunta 1", "resposta 1");
        cartasPR.put("pergunta 2", "resposta 2");
        cartasPR.put("pergunta 3", "resposta 3");
        cartasPR.put("pergunta 4", "resposta 4");
        cartasPR.put("pergunta 5", "resposta 5");
        cartasPR.put("pergunta 6", "resposta 6");

        cartasPR.forEach((key, value) -> {
            Cartas pergunta = new Cartas(key);
            pergunta.par = new Cartas(value);
            pergunta.par.par = pergunta;
            append(head, pergunta);
        });

        return head;
    }
}
