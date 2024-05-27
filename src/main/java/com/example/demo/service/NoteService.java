package com.example.demo.service;

import com.example.demo.data.dto.NoteDto;

import java.util.List;

public interface NoteService {
    List<NoteDto> listAll();
    NoteDto add(NoteDto note) throws NoteException;
    void deleteById(Long id) throws NoteException;
    void deleteLast() throws NoteException;
    NoteDto getLastNote() throws NoteException;
    void update(NoteDto note) throws NoteException;
    NoteDto getById(Long id) throws NoteException;
    NoteDto getByTitle(String title) throws NoteException;
}
