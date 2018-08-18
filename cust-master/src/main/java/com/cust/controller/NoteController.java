package com.cust.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cust.exception.NoteNotFound;
import com.cust.model.Note;
import com.cust.service.NoteService;
import com.cust.validation.NoteValidator;

/**
 * Controller which routes to approprate page depending on which of the
 * operations are invoked(Create a Post or Delete a Post or Update a Post)
 * 
 * @author murali
 *
 */
@Controller
@RequestMapping(value = "/note")
public class NoteController {

	/**
	 * Wire the service to create new post or update a post or delete a post.
	 */
	@Autowired
	private NoteService noteService;

	/**
	 * Wire Validator to validate message to be posted.
	 */
	@Autowired
	private NoteValidator noteValidator;

	/**
	 * Validates the Posted data.
	 * 
	 * @param binder
	 */
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(noteValidator);
	}

	/**
	 * Invoked when new note is selected on the home page(index.jsp)
	 * 
	 * @return ModelAndView with note-new as view page
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView newNotePage() {
		ModelAndView modelAndView = new ModelAndView("note-new", "note", new Note());
		return modelAndView;
	}

	/**
	 * 
	 * Invoked when new note is Posted.
	 * 
	 * @param note
	 * @param result
	 * @param redirectAttributes
	 * @return ModelAndView with view as note-new or index
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createNewNote(@ModelAttribute @Valid Note note, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors())
			return new ModelAndView("note-new");

		ModelAndView modelAndView = new ModelAndView();
		String postedMessage = "New Note(" + note.getMessage() + ") was successfully posted.";

		noteService.create(note);
		modelAndView.setViewName("redirect:/index.html");

		redirectAttributes.addFlashAttribute("message", postedMessage);
		return modelAndView;
	}

	/**
	 * Invoked when view to all notes link is invoked.
	 * 
	 * @return ModelAndView with view as note-list page
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView noteListPage() {
		ModelAndView modelAndView = new ModelAndView("note-list");
		List<Note> noteList = noteService.findAll();
		modelAndView.addObject("noteList", noteList);
		return modelAndView;
	}

	/**
	 * Invoked when you try to edit a note.
	 * 
	 * @param id
	 * @return ModelAndView with view a note-edit page
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editNotePage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("note-edit");
		Note note = noteService.findById(id);
		modelAndView.addObject("note", note);
		return modelAndView;
	}

	/**
	 * Invoked when post is edited and submitted.
	 * 
	 * @param note
	 * @param result
	 * @param id
	 * @param redirectAttributes
	 * @return ModelAndView with view as note-edit or index
	 * @throws NoteNotFound
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editNote(@ModelAttribute @Valid Note note, BindingResult result, @PathVariable Integer id,
			final RedirectAttributes redirectAttributes) throws NoteNotFound {

		if (result.hasErrors())
			return new ModelAndView("note-edit");

		ModelAndView modelAndView = new ModelAndView("redirect:/index.html");
		String message = "Note was successfully updated.";

		noteService.update(note);

		redirectAttributes.addFlashAttribute("message", message);
		return modelAndView;
	}

	/**
	 * Invoked when delete note is invoked
	 * 
	 * @param id
	 * @param redirectAttributes
	 * @return ModelAndView with index page as view.
	 * @throws NoteNotFound
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteNote(@PathVariable Integer id, final RedirectAttributes redirectAttributes)
			throws NoteNotFound {

		ModelAndView modelAndView = new ModelAndView("redirect:/index.html");

		Note note = noteService.delete(id);
		String message = "The Note(" + note.getMessage() + ") was successfully deleted.";

		redirectAttributes.addFlashAttribute("message", message);
		return modelAndView;
	}

}
