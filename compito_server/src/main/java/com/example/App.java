package com.example;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            ServerSocket server = new ServerSocket(3000);
            while(true){
                Socket s = server.accept();
                System.out.println("Connessione effettuata con un client.");
                Integer cont = 5;

                MioThread t1 = new MioThread(s,cont);
                t1.start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'instanza.");
            System.exit(1);
        }
    }
}
