package com.alura.literatura.model;

public enum Languages {
    LANGUAGES("languages");

    private String languagesGutendex;

    Languages(String languagesG){
        this.languagesGutendex = languagesG;
    }

    public static Languages fromString(String text){
        for(Languages language : Languages.values()){
            if(language.languagesGutendex.equalsIgnoreCase(text)){
                return language;
            }
        }
        throw new IllegalArgumentException("Ningun idiom no encontrado");
    }

}
