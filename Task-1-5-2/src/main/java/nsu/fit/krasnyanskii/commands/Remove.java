package nsu.fit.krasnyanskii.commands;

import picocli.CommandLine.*;

import nsu.fit.krasnyanskii.Notebook;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "remove", description = "delete a note")
public class Remove implements Callable<Integer> {
    @Parameters(index = "0")
    String noteName;

    @Override
    public Integer call() throws IOException {
        Notebook notebook = new Notebook(0);
        notebook.removeNote(noteName);
        return null;
    }
}