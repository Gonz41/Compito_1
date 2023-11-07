package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread extends Thread{
    private Socket s;
    private Integer cont;

    public MioThread(Socket s, Integer i){
        this.s = s;
        this.cont = i;
    }

    public void run(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            String risposta= "";
            boolean controllo = true;
            do{
                risposta = in.readLine();
                System.out.println(Thread.currentThread().getName() + " ha digitato " + risposta);
                if(risposta.equals("D")){
                    out.writeBytes(1 + "\n");
                    out.writeBytes(cont + "\n");
                } 
                if(risposta.equals("A")){
                    if(cont <= 0){
                        out.writeBytes(2+"\n");
                        controllo = false;
                    }
                    out.writeBytes(3 + "\n");
                    cont--;
                }
                if(risposta.equals("Q")) {
                    out.writeBytes(4 + "\n");
                    controllo = false;
                }
            }while(controllo != false);
            s.close();
            System.out.println("\nConnessione chiusa con il Client.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
