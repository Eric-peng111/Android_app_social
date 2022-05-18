package com.example.myfirstapp.ParserToken.Tonkenizer;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Tokenizer {
    private String buffer;          // String to be transformed into tokens each time next() is called.
    private Token currentToken;// The current token. The next token is extracted when next() is called.


    public static void main(String[] args) {
        // Create a scanner to get the user's input.
        System.out.println(Character.isLetter('A')&& Character.isLowerCase('A'));
        Scanner scanner = new Scanner(System.in);

        /*
         Continue to get the user's input until they exit.
         To exit press: Control + D or providing the string 'q'
         Example input you can try: ((1 + 2) * 5)/2
         */
        System.out.println("Provide a mathematical string to be tokenized:");
        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            // Check if 'quit' is provided.
            if (input.equals("q"))
                break;

            // Create an instance of the tokenizer.
            Tokenizer tokenizer = new Tokenizer(input);

            // Print all the tokens.
            while (tokenizer.hasNext()) {
                System.out.print(tokenizer.current() + " ");
                tokenizer.next();
            }
            System.out.println();
        }
    }

    public Tokenizer(String text) {
        buffer = text;          // save input text (string)
        next();                 // extracts the first token.
    }

    public String getBuffer(){
        return buffer;
    }

    public void next() {
        buffer = buffer.trim();     // remove whitespace

        if (buffer.isEmpty()) {
            currentToken = null;    // if there's no string left, set currentToken null and return
            return;
        }

        char firstChar = buffer.charAt(0);



        if(firstChar=='@')
            currentToken=new Token("@",Token.Type.AT);

        else if(firstChar=='.')
            currentToken=new Token(".",Token.Type.DOT);

        else if(Character.isLetter(firstChar)&& Character.isLowerCase(firstChar))
        {
            String s="";
            for(int i=0;i<buffer.length();i++){
                if(Character.isLetter(buffer.charAt(i)) && Character.isLowerCase(buffer.charAt(i)))
                {
                    s=s+buffer.charAt(i);
                }
                else
                    break;
            }
            currentToken=new Token(s,Token.Type.LETTER);


        }
        else if(Character.isLetter(firstChar)&& Character.isUpperCase(firstChar)){
            String s="";
            for(int i=0;i<buffer.length();i++){
                if(Character.isLetter(buffer.charAt(i)) && Character.isUpperCase(buffer.charAt(i)))
                {
                    s=s+buffer.charAt(i);
                }
                else
                    break;
            }
            currentToken=new Token(s,Token.Type.CAPLETTER);

        }

        else if(Character.isDigit(firstChar))
        {
            String s="";
            for(int i=0;i<buffer.length();i++){
                if(Character.isDigit(buffer.charAt(i)))
                {
                    s=s+buffer.charAt(i);
                }
                else
                    break;
            }
            currentToken=new Token(s,Token.Type.INT);
        }

//        if(firstChar!='+' && firstChar!='-' && firstChar!='*'&& firstChar!='/'&& firstChar!='('&& firstChar!=')'&& firstChar!='!'&& !Character.isDigit(firstChar))
//            throw new Token.IllegalTokenException("a character which does not correlate to any token type is provided");




        // ########## YOUR CODE ENDS HERE ##########
        // Remove the extracted token from buffer
        int tokenLen = currentToken.getToken().length();
        buffer = buffer.substring(tokenLen);
    }


    public Token current() {
        return currentToken;
    }


    public boolean hasNext() {
        return currentToken != null;
    }
}





//        if(firstChar=='*')
//            currentToken=new Token("*",Token.Type.MUL);
//        if(firstChar=='/')
//            currentToken=new Token("/",Token.Type.DIV);
//
//        if(firstChar=='(')
//            currentToken=new Token("(",Token.Type.LBRA);
//        if(firstChar==')')
//            currentToken=new Token(")",Token.Type.RBRA);
//
//        if(firstChar=='!')
//            currentToken=new Token("!",Token.Type.FAC);