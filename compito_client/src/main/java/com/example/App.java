package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try{
            Socket socket = new Socket("localhost", 3000);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Connessione effettuata con il Server.");
            Scanner input = new Scanner(System.in);
            String num = "";
            int var;
            int cont=0;
            do{
                
                System.out.println("> Digita D per visualizzare la disponibilità o A per acquistare un biglietto: ");
                num = input.nextLine();
                input.nextLine();
                out.writeBytes(num +"\n");
                var = Integer.parseInt(in.readLine());
                if(var == 1){
                    cont = Integer.parseInt(in.readLine());
                    System.out.println(">Disponibili " + cont + " biglietti\n");
                }
                if(var == 3){
                    System.out.println(">Biglietti acquistato\n");
                }
                if(var == 5){
                    System.out.println(">Richiesta invalida\n");
                }
            }while(var != 2 || var != 4);

            if(var == 2){
                System.out.println(">Biglietti esauriti\n");
            }
            if(var == 4){
                System.out.println(">Esci\n");
            }

            socket.close();

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
