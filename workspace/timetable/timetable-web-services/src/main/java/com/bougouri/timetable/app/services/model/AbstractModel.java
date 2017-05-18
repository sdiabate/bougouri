package com.bougouri.timetable.app.services.model;

import com.bougouri.timetable.business.model.AbstractEntity;

public abstract class AbstractModel<T extends AbstractEntity> {

	public abstract T to(T entity);

	public abstract AbstractModel<T> from(T entity);
}
