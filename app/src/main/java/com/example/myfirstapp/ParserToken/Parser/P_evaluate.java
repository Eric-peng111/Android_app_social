package com.example.myfirstapp.ParserToken.Parser;

import com.example.myfirstapp.ParserToken.Tonkenizer.Token;
import com.example.myfirstapp.ParserToken.Tonkenizer.Tokenizer;

public class P_evaluate {
    Tokenizer tokenizer;
    private int count_at=0;
    private int count_dot=0;
    private String s="";


    public static class IllegalProductionException extends IllegalArgumentException {
        public IllegalProductionException(String errorMessage) {
            super(errorMessage);
        }
    }


    public P_evaluate(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    private void password(){
        if(this.tokenizer.hasNext()){
            s=s+this.tokenizer.current().getToken();
            this.tokenizer.next();
            this.password();
        }else
        {
            if(Character.isUpperCase(s.charAt(0)))
                throw new IllegalProductionException("No capital letter in the beginning.");
            if(s.length()<8)
                throw new IllegalProductionException("password need to be at least 8 digits");
            if(s.length()>12)
                throw new IllegalProductionException("password need to be at most 12 digits");

        }

    }





}
