package de.thm.arsnova.persistance;

import de.thm.arsnova.entities.Entity;

public interface Repository<T extends Entity> {
	T get(String id);
	T create(T entity);
	T update(T entity);
	boolean delete(T entity);
}
