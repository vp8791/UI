package com.cust.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cust.model.Note;

/**
 * NoteRepository which provides utility to do CRUD operations on Notes table.
 * 
 * @author murali
 *
 */
public interface NoteRepository extends JpaRepository<Note, Integer> {

}
