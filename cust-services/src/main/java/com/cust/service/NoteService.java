package com.cust.service;

import java.util.List;

import com.cust.exception.NoteNotFound;
import com.cust.model.Note;

/**
 * Interface to support operations to create, delete, update and find all Notes
 * 
 * @author murali
 *
 */
public interface NoteService {

	/**
	 * Creates Note
	 * 
	 * @param note
	 * @return persisted note in database.
	 */
	public Note create(Note note);

	/**
	 * 
	 * Deletes Note
	 * 
	 * @param id
	 *            of the note to be deleted.
	 * @return Note deleted
	 * @throws NoteNotFound
	 */
	public Note delete(int id) throws NoteNotFound;

	/**
	 * 
	 * Returns all the Notes in database
	 * 
	 * @return All the Notes in Database
	 */
	public List<Note> findAll();

	/**
	 * Updates a Note
	 * 
	 * @param note
	 * @return updated Note
	 * @throws NoteNotFound
	 */
	public Note update(Note note) throws NoteNotFound;

	/**
	 * Returns Note for a id searched.
	 * 
	 * @param id
	 * @return Note of the searched Id
	 */
	public Note findById(int id);

}
