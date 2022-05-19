package com.example.myfirstapp.ParserToken.Parser;

import com.example.myfirstapp.ParserToken.Tonkenizer.Token;
import com.example.myfirstapp.ParserToken.Tonkenizer.Tokenizer;

import java.util.Scanner;

public class TopicCheck {

    Tokenizer tokenizer;
    private int state=0;
    private int count_dot=0;
    private String Hex_s="";
    private String Star_s="";
    private String back_s="";

    public static class IllegalProductionException extends IllegalArgumentException {
        public IllegalProductionException(String errorMessage) {
            super(errorMessage);
        }
    }


    public TopicCheck(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    /**
     * get tokens hex and star then do the splitting
     * @return boolean
     */
    public boolean check(){
        if(this.tokenizer.hasNext()){
            if (this.tokenizer.current().getType() == Token.Type.HEX){
                state=1;
                this.tokenizer.next();
            }
            else if(this.tokenizer.current().getType()==Token.Type.STAR){
                state=-1;

                this.tokenizer.next();
            }
            else
                this.front();

            check();

        }
        else {
        }
        return true;
    }

    /**
     * if not hex and star, iterate until it is
     */
    private void front(){
        if(this.tokenizer.hasNext() ){
            if(this.tokenizer.current().getType() != Token.Type.HEX&&this.tokenizer.current().getType() != Token.Type.STAR){
                if(state==1){
                    Hex_s=Hex_s+this.tokenizer.current().getToken();
                }else if(state==-1){
                    Star_s=Star_s+this.tokenizer.current().getToken();
                }

                this.tokenizer.next();
                this.front();
            }

        }
        else{

        }
    }

    public String getHex(){
        return Hex_s;
    }
    public String getStar(){
        return Star_s;
    }

    public static void main(String[] args) {
        // Create a scanner to get the user's input.
        Scanner scanner = new Scanner(System.in);


        System.out.println("Provide a mathematical string to be parsed:");
        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            // Check if 'quit' is provided.
            if (input.equals("q"))
                break;

            // Create an instance of the tokenizer.
            Tokenizer tokenizer = new Tokenizer(input);

            // Print out the expression from the parser.
            TopicCheck test = new TopicCheck(tokenizer);
            try{
                test.check();
                System.out.println(test.getHex());
                System.out.println(test.getStar());
            }catch (E_evaluate.IllegalProductionException e){
                System.out.println(e.getMessage());
            }


        }
    }
}
