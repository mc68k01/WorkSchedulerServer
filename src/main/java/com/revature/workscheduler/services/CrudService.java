package com.revature.workscheduler.services;

import com.revature.workscheduler.models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface CrudService<T, KEY, REPO extends CrudRepository<T, KEY>>
{
	/**
	 * @return The repository instance used by this service
	 */
	public REPO getRepo();

	/**
	 * @param value A record to get the value of. Must not be null.
	 * @return The ID of the value
	 */
	public KEY getIDFor(T value);

	/**
	 * Adds the given record to the repository
	 * @param value The unadded record to add. Must not be null.
	 * @return The record with an updated ID. Will not be null.
	 * @throws IllegalArgumentException if value is null
	 */
	default T add(T value)
	{
		return this.getRepo().save(value);
	}


	/**
	 * Retrieves the record with the given ID, if any
	 * @param id The id of the record to get
	 * @return The record if it exists. Returns null if no record exists or id is null
	 */
	default T get(KEY id)
	{
		return this.getRepo().findById(id).orElse(null);
	}

	/**
	 * @return All records
	 */
	default List<T> getAll()
	{
		List<T> list = new ArrayList<>();
		this.getRepo().findAll().forEach(list::add);
		return list;
	}

	/**
	 * Updates a record in the backend
	 * @param newData The record to update. Must have an ID matching an existing record in the backend.
	 * @return The updated data if update was successfull, returns null if newData is null
	 * or if no data with a matching ID exists
	 */
	default T update(T newData)
	{
		if (newData == null)
			return null;
		KEY id = this.getIDFor(newData);
		T existingData = this.get(id);
		if (existingData == null)
			return null;
		return this.getRepo().save(newData);
	}

	/**
	 * Deletes the data with the given id
	 * @param id The ID of the data to delete
	 * @return True if the data exists and was successfully deleted,
	 * false if the data does not exists or could not be deleted
	 */
	default boolean delete(KEY id)
	{
		T data = this.get(id);
		if (data == null)
		{
			return false;
		}
		try
		{
			this.getRepo().deleteById(id);
			return true;
		}
		catch(IllegalArgumentException e)
		{
			return false;
		}
	}
}
