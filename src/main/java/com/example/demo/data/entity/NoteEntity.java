package com.example.demo.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "note")
public class NoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String title;
    @Column
    private String content;

    public NoteEntity(String title, String content) {
        super();
        this.title = title;
        this.content = content;
    }
    public String getIdToString() {
        return String.valueOf(this.id);
    }

    @Override
    public String toString() {
        return "NoteEntity{"+"id="+id+", title='"+title+'}';
    }
}
