package com.tdl.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;
import com.tdl.model.ToDoNote;

@Component
public class ToDoNoteServiceImpl implements ToDoNoteService{
	
	private static List<ToDoNote> todos = new ArrayList<>();
	
	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//Calendar c = Calendar.getInstance();
		//Date d= new Date();
		//c.add(Calendar.DATE, 3);
		ToDoNote workout1 = new ToDoNote(1, "Monday workout",  new Date(), "Leg Day", 1, false);
		ToDoNote workout2 = new ToDoNote(2, "Just workout",  new Date()   , "Full Body Day", 2, false);
		ToDoNote workout3 = new ToDoNote(3, "An workout",  new Date()  , "Sleep Day", 2, false);
		
		try {
			 workout1 = new ToDoNote(1, "Monday workout",  (Date)dateFormat.parse("2019-03-22")   , "Leg Day", 1, false);
			 workout2 = new ToDoNote(2, "Just workout",  (Date)dateFormat.parse("2019-03-20")   , "Full Body Day", 2, false);
			 workout3 = new ToDoNote(3, "An workout",  (Date)dateFormat.parse("2019-03-19")  , "Sleep Day", 2, false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ToDoNote workout2 = new ToDoNote(2, "Just workout",  new Date()   , "Full Body Day", 2, false);
		//ToDoNote workout3 = new ToDoNote(3, "An workout",  new Date()  , "Sleep Day", 2, false);
		
		todos.add(workout1);
		todos.add(workout2);
		todos.add(workout3);
	}
	
	@Override
	public List<ToDoNote> getAllToDoNote() {
		return todos;
	}

	@Override
	public ToDoNote getToDoNoteById(int id) {
		for(ToDoNote toDoNote : todos) {
			if(toDoNote.getId() == id) {
				return toDoNote;
			}
		}
		return null;
	}

	@Override
	public ToDoNote addToDoNote(ToDoNote toDoNote) {
		
		
		ToDoNote tempNote = todos.get(todos.size()-1);
		int id = tempNote.getId()+1;
		toDoNote.setId(id);
		todos.add(toDoNote);
		return toDoNote;
		
	}

	@Override
	public void updateToDoNote(ToDoNote toDoNote) {
		for(ToDoNote oldToDoNote : todos) {
			if(oldToDoNote.getId() == toDoNote.getId()) {
				oldToDoNote.setName(toDoNote.getName());
				oldToDoNote.setDateToComplete(toDoNote.getDateToComplete());
				oldToDoNote.setDescription(toDoNote.getDescription());
				oldToDoNote.setPriority(toDoNote.getPriority());
				oldToDoNote.setCompleted(toDoNote.isCompleted());
			}
		}
	}

	@Override
	public void deleteToDoNote(int id) {
		for(Iterator<ToDoNote> it= todos.iterator(); it.hasNext();) {
			ToDoNote toDoNote = it.next();
			if(toDoNote.getId() == id) {
				it.remove();
				break;
			}
		}
		
	}

}
