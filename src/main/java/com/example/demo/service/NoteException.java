package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoteException extends Exception{
    private static final Logger log = LoggerFactory.getLogger(NoteException.class);
    private static final String NOTE_NOT_FOUND_MESSAGE = "!!! NOTE WITH ID = {} NOT FOUND !!!";
    private static final String NOTE_IS_EMPTY = "!!! NOTE IS EMPTY !!!";
    private static final String NOTE_BY_TITLE_NOT_FOUND = "!!! NOTE WITH TITLE = {} NOT FOUND !!!";
    public NoteException(Long Id) { log.error(NOTE_NOT_FOUND_MESSAGE, Id); }
    public NoteException() { log.error(NOTE_IS_EMPTY); }
    public NoteException(String title) { log.error(NOTE_BY_TITLE_NOT_FOUND, title); }

}
