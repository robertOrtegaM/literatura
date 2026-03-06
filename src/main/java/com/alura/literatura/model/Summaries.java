package com.alura.literatura.model;

public enum Summaries {
    SUMMARIES("summaries"),;

    private String summariesGutendex;

    Summaries(String summariesG){
        this.summariesGutendex = summariesG;
    }

    public static Summaries fromString(String text){
        for(Summaries summaries : Summaries.values()){
            if(summaries.summariesGutendex.equalsIgnoreCase(text)){
                return summaries;
            }
        }
        throw new IllegalArgumentException("Ningun idiom no encontrado");
    }
}
