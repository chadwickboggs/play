package com.tiffanytimbric.play.pp;

public final class Individual {

    private static Individual instance;

    public static Individual getInstance() {
        if (instance == null) {
            newInstance();
        }

        return instance;
    }

    private static synchronized void newInstance() {
        if (instance != null) {
            return;
        }

        instance = new Individual();
    }

    private Individual() {
    }
}
