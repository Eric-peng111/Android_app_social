package com.example.myfirstapp.ParserToken.Parser;

import com.example.myfirstapp.ParserToken.Tonkenizer.Token;
import com.example.myfirstapp.ParserToken.Tonkenizer.Tokenizer;

import java.util.ArrayList;
import java.util.Scanner;

public class E_evaluate {
    Tokenizer tokenizer;
    private int count_at=0;
    private int count_dot=0;
    private String front_s="";
    private String middle_s="";
    private String back_s="";

    public static class IllegalProductionException extends IllegalArgumentException {
        public IllegalProductionException(String errorMessage) {
            super(errorMessage);
        }
    }


    public E_evaluate(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }


    public boolean atdot(){
        if(this.tokenizer.hasNext()){
            if (this.tokenizer.current().getType() == Token.Type.AT){
                count_at++;
                if (count_at>1){
                    throw new IllegalProductionException("More than two '@' in email");
                }
                this.tokenizer.next();
            }
            else if(this.tokenizer.current().getType()==Token.Type.DOT){
                count_dot++;
                if (count_dot>1){
                    throw new IllegalProductionException("More than two '.' in email");
                }
                this.tokenizer.next();
            }
            else
                this.front();

            atdot();

        }
        else{
            if(count_at==0)
                throw new IllegalProductionException("should use @ in email");
            if(count_dot==0)
                throw new IllegalProductionException("should use '.' in email");
            if(!back_s.equals("com"))
            {
                System.out.println("back_s="+back_s);
                throw new IllegalProductionException("should use 'com' in email ");
            }
//            if(!Character.isUpperCase(front_s.charAt(0)))
//                throw new IllegalProductionException("No capital letter in the beginning.");
        }
        return true;
    }

    private void front(){
        if(this.tokenizer.hasNext() ){
            if(this.tokenizer.current().getType() != Token.Type.AT&&this.tokenizer.current().getType() != Token.Type.DOT){
                if (this.tokenizer.current().getType() == Token.Type.LETTER || this.tokenizer.current().getType() == Token.Type.CAPLETTER){
                    if(count_at==0&&count_dot==0)
                        front_s=front_s+this.tokenizer.current().getToken();
                    else if(count_at==1&&count_dot==0)
                        middle_s=middle_s+this.tokenizer.current().getToken();
                    else if(count_at==1&&count_dot==1)
                        back_s=back_s+this.tokenizer.current().getToken();
                    else if(count_at==0&&count_dot==1)
                        throw new IllegalProductionException("dot can't be front of @ in email");
                    this.tokenizer.next();
                    this.front();
                }

            }

        }
        else{

            if(count_at==0)
                throw new IllegalProductionException("should use @ in email");
            if(count_dot==0)
                throw new IllegalProductionException("should use '.' in email");
            if(!back_s.equals("com"))
            {
                System.out.println("back_s="+back_s);
                throw new IllegalProductionException("should use 'com' in email");
            }
        }
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
            E_evaluate test = new E_evaluate(tokenizer);
            try{
               test.atdot();
            }catch (IllegalProductionException e){
                System.out.println(e.getMessage());
            }


        }
    }



}
