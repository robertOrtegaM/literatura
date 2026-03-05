package com.alura.literatura.model;

public enum Languages {
    LANGUAGES("languages");

    private String languagesgutendex;

    Languages(String authorgutendex){
        this.languagesgutendex = languagesgutendex;
    }

    public static Languages fromString(String text){
        for(Languages language : Languages.values()){
            if(language.languagesgutendex.equalsIgnoreCase(text)){
                return language;
            }
        }
        throw new IllegalArgumentException("Ningun idiom no encontrado");
    }

}
