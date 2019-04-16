package com.tdl.service;

import java.util.List;

import com.tdl.model.*;

public interface ToDoNoteService {
	
	public List<ToDoNote> getAllToDoNote();
	
	public ToDoNote getToDoNoteById(int id);
	
	public ToDoNote addToDoNote(ToDoNote toDoNote);
	
	public void updateToDoNote(ToDoNote toDoNote);
	
	public void deleteToDoNote(int id);
}
