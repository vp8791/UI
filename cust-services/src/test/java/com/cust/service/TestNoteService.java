package com.cust.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.cust.exception.NoteNotFound;
import com.cust.model.Note;
import com.cust.repository.NoteRepository;
import com.cust.service.NoteServiceImpl;

public class TestNoteService {

	private NoteServiceImpl noteService = new NoteServiceImpl();

	private String TEST_NOTE_MESSAGE = "Test Note Message:";

	/**
	 * Tests create Note
	 */
	@Test
	public void testCreateNote() {
		NoteRepository notesRepository = mock(NoteRepository.class);
		Note postNote = getATestNote();
		Note persistedNote = getATestNote(1);
		when(notesRepository.save(postNote)).thenReturn(persistedNote);
		// Write noteRepository private variable of noteService using Spring
		// test utils
		ReflectionTestUtils.setField(noteService, "noteRepository", notesRepository);
		Note createdNote = noteService.create(postNote);
		assertTrue((createdNote.getId() == 1));
		assertTrue((createdNote.getMessage().equals(TEST_NOTE_MESSAGE + 1)));
	}

	/**
	 * Tests find Note by Id
	 */
	@Test
	public void testfindById() {
		NoteRepository notesRepository = mock(NoteRepository.class);
		when(notesRepository.findOne(10)).thenReturn(getATestNote(10));
		// Write noteRepository private variable of noteService using Spring
		// test utils
		ReflectionTestUtils.setField(noteService, "noteRepository", notesRepository);
		Note foundNote = noteService.findById(10);
		assertTrue((foundNote.getId() == 10));
		assertTrue((foundNote.getMessage().equals(TEST_NOTE_MESSAGE + 10)));
	}

	/**
	 * Tests find Note by Id
	 */
	@Test
	public void testdeleteById() {
		NoteRepository notesRepository = mock(NoteRepository.class);
		Note deletedNote = getATestNote(10);
		when(notesRepository.findOne(10)).thenReturn(deletedNote);
		// Write noteRepository private variable of noteService using Spring
		// test utils
		ReflectionTestUtils.setField(noteService, "noteRepository", notesRepository);
		try {
			noteService.delete(10);
		} catch (NoteNotFound e) {
			fail("Note Not found");
		}
		assertTrue((deletedNote.getId() == 10));
		assertTrue((deletedNote.getMessage().equals(TEST_NOTE_MESSAGE + 10)));
	}

	/**
	 * Tests finding of all notes
	 */
	@Test
	public void testfindAll() {
		NoteRepository notesRepository = mock(NoteRepository.class);
		Note note1 = getATestNote(1);
		Note note2 = getATestNote(2);
		List<Note> allNotes = new ArrayList<Note>();
		allNotes.add(note1);
		allNotes.add(note2);
		when(notesRepository.findAll()).thenReturn(allNotes);
		// Write noteRepository private variable of noteService using Spring
		// test utils
		ReflectionTestUtils.setField(noteService, "noteRepository", notesRepository);
		List<Note> foundNotes = noteService.findAll();
		assertTrue((foundNotes.size() == 2));
	}

	/**
	 * Tests find Note by Id
	 */
	@Test
	public void testUpdateNoteFound() {
		NoteRepository notesRepository = mock(NoteRepository.class);
		Note initialDPersistedNote = getATestNote(10);
		Note noteToBeUpdated = getATestNote(10);
		when(notesRepository.findOne(10)).thenReturn(initialDPersistedNote);
		// Write noteRepository private variable of noteService using Spring
		// test utils
		ReflectionTestUtils.setField(noteService, "noteRepository", notesRepository);
		try {
			Note finalNote = noteService.update(initialDPersistedNote);
			assertTrue((finalNote.getId() == 10));
			assertTrue((finalNote.getMessage().equals(TEST_NOTE_MESSAGE + 10)));
		} catch (NoteNotFound e) {
			fail("Failed to update the Note");
		}
	}

	/**
	 * Tests find Note by Id which is not present
	 * 
	 * @throws NoteNotFound
	 */
	@Test(expected = NoteNotFound.class)
	public void testUpdateNoteNotFound() throws NoteNotFound {
		NoteRepository notesRepository = mock(NoteRepository.class);
		Note noteToBeUpdated = getATestNote(10);
		when(notesRepository.findOne(10)).thenReturn(null);
		// Write noteRepository private variable of noteService using Spring
		// test utils
		ReflectionTestUtils.setField(noteService, "noteRepository", notesRepository);
		Note finalNote = noteService.update(noteToBeUpdated);
	}

	/**
	 * Returns a test Note with id and message appended with Id
	 * 
	 * @param testId
	 * @return
	 */
	private Note getATestNote(int testId) {
		Note note = new Note();
		note.setId(testId);
		note.setMessage(TEST_NOTE_MESSAGE + testId);
		return note;
	}

	/**
	 * Returns a test Note without Id
	 * 
	 * @return
	 */
	private Note getATestNote() {
		Note note = new Note();
		note.setMessage(TEST_NOTE_MESSAGE);
		return note;
	}

}
