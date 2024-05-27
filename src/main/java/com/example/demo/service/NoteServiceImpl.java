package com.example.demo.service;

import com.example.demo.data.dto.NoteDto;
import com.example.demo.data.entity.NoteEntity;
import com.example.demo.data.repository.NoteRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService{
    private static final Logger log = LoggerFactory.getLogger(NoteServiceImpl.class);
    @Autowired
    private NoteRepository noteListRepository;
    @Autowired
    private NoteConvertor noteConvertor;
    @Override
    public List<NoteDto> listAll() {
        return noteConvertor.entitiesToDtos(noteListRepository.findAll());
    }
    @Override
    public NoteDto add(NoteDto note) throws NoteException {
        NoteEntity entity = noteConvertor.dtoToEntity(note);
        long nextId = noteListRepository.count();
        if (nextId==0) {
            nextId = 1;
        } else {
            nextId = noteListRepository.LastNoteId()+1L;
        }
        entity.setId((int) nextId);
        log.info("New record {} were successfully inserted", entity.toString());
        return noteConvertor.entityToDto(noteListRepository.save(entity));
    }
    @Override
    public void deleteById(Long id) throws NoteException {
        if (Objects.isNull(getById(id))) {
            throw new NoteException(id);
        }
        noteListRepository.deleteById(Math.toIntExact(id));
        log.info("Record {} were successfully deleted",id);
    }
    @Override
    public void deleteLast()  throws NoteException {
        int maxId = noteListRepository.LastNoteId();
        if (Objects.isNull(maxId)) {
            throw new NoteException();
        }
        noteListRepository.deleteById(maxId);
    }
    @Override
    public NoteDto getLastNote() throws NoteException {
        Optional<NoteEntity> note = noteListRepository.findTopByOrderByIdDesc();
        if (!note.isPresent()) {
            throw new NoteException();
        }
        return noteConvertor.entityToDto(note.get());
    }
    @Override
    public void update(NoteDto note) throws NoteException {
        if (Objects.isNull(note.getId())) {
            throw new NoteException();
        }
        NoteEntity entity = noteConvertor.dtoToEntity(note);
        noteListRepository.save(entity);
    }
    @Override
    public NoteDto getById(Long id) throws NoteException {
        Optional<NoteEntity> note = noteListRepository.findById(Math.toIntExact(id));
        if (!note.isPresent()) {
            throw new NoteException(id);
        }
        return noteConvertor.entityToDto(note.get());
    }
    @Override
    public NoteDto getByTitle(String title) throws NoteException {
        Optional<NoteEntity> note = noteListRepository.findByTitle(title);
        if (!note.isPresent()) {
            throw new NoteException(title);
        }
        return noteConvertor.entityToDto(note.get());
    }
    @PostConstruct
    public void init() throws NoteException {
        long countNoteRec = noteListRepository.count();
        if (countNoteRec == 0) {
            NoteDto note = new NoteDto();
            for (int i=1; i<4; i++) {
                note = new NoteDto("Title note " + i, "Content note " + i);
                note = add(note);
            }
            note.setTitle("updated title note 3");
            update(note);
            listAll().forEach(NoteDto::toString);
        }
    }
}
