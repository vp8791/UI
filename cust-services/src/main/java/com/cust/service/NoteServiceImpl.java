package com.cust.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cust.exception.NoteNotFound;
import com.cust.model.Note;
import com.cust.repository.NoteRepository;
/**
 * 
 * Implements NoteService
 * 
 * @author murali
 *
 */
@Service
public class NoteServiceImpl implements NoteService {

	@Resource
	private NoteRepository noteRepository;

	@Override
	@Transactional
	public Note create(Note noteMessage) {
		return noteRepository.save(noteMessage);
	}

	@Override
	@Transactional
	public Note findById(int id) {
		return noteRepository.findOne(id);
	}

	@Override
	@Transactional(rollbackFor = NoteNotFound.class)
	public Note delete(int id) throws NoteNotFound {
		Note deletedNote = noteRepository.findOne(id);

		if (deletedNote == null)
			throw new NoteNotFound();

		noteRepository.delete(deletedNote);
		return deletedNote;
	}

	@Override
	@Transactional
	public List<Note> findAll() {
		return noteRepository.findAll();
	}

	@Override
	@Transactional(rollbackFor = NoteNotFound.class)
	public Note update(Note note) throws NoteNotFound {
		Note updatedNote = noteRepository.findOne(note.getId());

		if (updatedNote == null)
			throw new NoteNotFound();

		updatedNote.setMessage(note.getMessage());

		return updatedNote;
	}

}
