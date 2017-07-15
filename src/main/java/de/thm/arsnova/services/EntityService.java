package de.thm.arsnova.services;

import de.thm.arsnova.entities.Entity;
import de.thm.arsnova.persistance.Repository;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Map;

public abstract class EntityService<T extends Entity> {
	private Class<T> type;
	private Repository<T> repository;

	@PreAuthorize("isAuthenticated && hasPermission(type, 'read', #id)")
	public T get(final String id) {
		return repository.get(id);
	}

	@PreAuthorize("isAuthenticated && hasPermission(type, 'create', #entity)")
	public T create(final T entity) {
		return repository.create(entity);
	}

	@PreAuthorize("isAuthenticated && hasPermission(type, 'update', #oldEntity)")
	public T update(final T oldEntity, final T newEntity) {
		repository.update(newEntity);
		return newEntity;
	}

	@PreAuthorize("isAuthenticated && hasPermission(type, 'update', #entity)")
	public T patch(final T entity, final Map<String, Object> changes) {
		return entity;
	}

	@PreAuthorize("isAuthenticated && hasPermission(type, 'delete', #entity)")
	public boolean delete(final T entity) {
		return true;
	}
}
