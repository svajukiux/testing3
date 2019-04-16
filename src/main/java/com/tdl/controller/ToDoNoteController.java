package com.tdl.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
//import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tdl.exception.InvalidFieldException;
import com.tdl.exception.ToDoNoteNotFoundException;
import com.tdl.model.ToDoNote;
import com.tdl.service.ToDoNoteServiceImpl;

@RestController
public class ToDoNoteController {
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private ToDoNoteServiceImpl toDoNoteService;
	
	@GetMapping("/todos")
	public List<ToDoNote> getAllToDoNote(){
		return toDoNoteService.getAllToDoNote();
	}
	
	@GetMapping("/todos/priority/{number}")
	public List<ToDoNote> getAllPriorityNotes(@PathVariable int number){
		ArrayList<ToDoNote> priorityNotes = new ArrayList<ToDoNote>();
		List<ToDoNote> allNotes = toDoNoteService.getAllToDoNote();
		
		
		for(Iterator<ToDoNote> it= allNotes.iterator(); it.hasNext();) {
			ToDoNote toDoNote = it.next();
			if(toDoNote.getPriority()==number) {
				priorityNotes.add(toDoNote);
			}
		}
		
		return priorityNotes;
	}
	
	@GetMapping("/todos/{toDoNoteId}")
	public Resource<ToDoNote> getToDoNoteById(@PathVariable int toDoNoteId) {
		ToDoNote note = toDoNoteService.getToDoNoteById(toDoNoteId);
		if(note==null) {
			throw new ToDoNoteNotFoundException("Note with id "+ toDoNoteId + " not found");
			
		}
		Resource<ToDoNote> resource = new Resource<ToDoNote>(note);
		Link linkToSelf =  linkTo(methodOn(this.getClass()).getToDoNoteById(toDoNoteId)).withSelfRel();
		Link linkToAll =  linkTo(methodOn(this.getClass()).getAllToDoNote()).withRel("allTodos");
		resource.add(linkToSelf);
		resource.add(linkToAll);
		
		
		return resource;
	}
	
	@PostMapping("/todos")
	public ResponseEntity<ToDoNote> addUser(@RequestBody ToDoNote newToDoNote, UriComponentsBuilder builder)throws HttpMessageNotReadableException, ParseException{
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		ToDoNote toDoNote = toDoNoteService.addToDoNote(newToDoNote);
		
		if(toDoNote==null) {
			return ResponseEntity.noContent().build(); // cia reikia naujo exception ten body not found or smth
		}
		
		if(toDoNote.getDateToComplete()!=null && toDoNote.getDateToComplete().before(dateFormat.parse(dateFormat.format(new Date())))) {
			throw new InvalidFieldException("Invalid Date");
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/todos/{id}").buildAndExpand(toDoNote.getId()).toUri());
		return new ResponseEntity<ToDoNote>(toDoNote,headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/todos")
	public ResponseEntity<ToDoNote> updateToDoNote(@Valid @RequestBody ToDoNote toDoNote) throws ParseException{
		ToDoNote todos = toDoNoteService.getToDoNoteById(toDoNote.getId());
		
		
		if(todos == null) {
			throw new ToDoNoteNotFoundException("Note with id "+ toDoNote.getId() + " not found");
		}
		
		if(toDoNote.getDateToComplete().before(dateFormat.parse(dateFormat.format(new Date())))) {
			throw new InvalidFieldException("Invalid Date");
		}
		
		if(toDoNote.getName()==null) {
			throw new InvalidFieldException("Name is required");
		}
		todos.setName(toDoNote.getName());
		
		
		todos.setDateToComplete(toDoNote.getDateToComplete());
		
		
		todos.setDescription(toDoNote.getDescription());
		
		
		todos.setPriority(toDoNote.getPriority());
		
		todos.setCompleted(toDoNote.isCompleted());
		
		toDoNoteService.updateToDoNote(todos);
		return new ResponseEntity<ToDoNote>(todos, HttpStatus.OK);
		
		
	}
	
	@PutMapping("/todos/{id}")
	public ResponseEntity<ToDoNote> updateToDoNote(@Valid @RequestBody ToDoNote toDoNote, @PathVariable int id) throws ParseException{
		ToDoNote oldNote = toDoNoteService.getToDoNoteById(id);
		
		if(oldNote == null) {
			throw new ToDoNoteNotFoundException("Note with id "+ id + " not found");
		}
		
		if(toDoNote.getDateToComplete()!=null && toDoNote.getDateToComplete().before(dateFormat.parse(dateFormat.format(new Date())))) {
			throw new InvalidFieldException("Invalid Date");
		}
		
		oldNote.setName(toDoNote.getName());
		oldNote.setDateToComplete(toDoNote.getDateToComplete());
		oldNote.setDescription(toDoNote.getDescription());
		oldNote.setPriority(toDoNote.getPriority());
		oldNote.setCompleted(toDoNote.isCompleted());
		
		toDoNoteService.updateToDoNote(oldNote);
		return new ResponseEntity<ToDoNote>(oldNote, HttpStatus.OK);
	}
	
	@PatchMapping("/todos/{id}")
	public ResponseEntity<ToDoNote> partlyUpdateToDoNote(@RequestBody ToDoNote toDoNote, @PathVariable int id){
		ToDoNote oldNote = toDoNoteService.getToDoNoteById(id);
		
		if(oldNote == null) {
			throw new ToDoNoteNotFoundException("Note with id "+ id + " not found");
		}
		
		if(toDoNote.getName()!= null){
			oldNote.setName(toDoNote.getName());
		}
		
		if(toDoNote.getDateToComplete()!= null){
			oldNote.setDateToComplete(toDoNote.getDateToComplete());
		}
		
		if(toDoNote.getDescription()!= null){
			oldNote.setDescription(toDoNote.getDescription());
		}
		
		if(toDoNote.getPriority()!= null){
			oldNote.setPriority(toDoNote.getPriority());
		}
		
		if(toDoNote.isCompleted()!= null){
			oldNote.setCompleted(toDoNote.isCompleted());
		}
		
		toDoNoteService.updateToDoNote(oldNote);
		return new ResponseEntity<ToDoNote>(oldNote, HttpStatus.OK);
	}	
	
	@DeleteMapping("/todos/{toDoNoteId}")
	public ResponseEntity<ToDoNote> deleteToDoNote(@PathVariable int toDoNoteId){
		ToDoNote todos = toDoNoteService.getToDoNoteById(toDoNoteId);
		
		if(todos == null) {
			throw new ToDoNoteNotFoundException("Note with id "+ toDoNoteId + " not found. Cannot delete.");
		}
		
		toDoNoteService.deleteToDoNote(toDoNoteId);
		return new ResponseEntity<ToDoNote>(HttpStatus.NO_CONTENT);
	}
}
