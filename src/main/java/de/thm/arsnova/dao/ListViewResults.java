/*
 * This file is part of ARSnova Backend.
 * Copyright (C) 2012-2015 The ARSnova Team
 *
 * ARSnova Backend is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ARSnova Backend is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.thm.arsnova.dao;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fourspaces.couchdb.Document;
import com.fourspaces.couchdb.ViewResults;

public class ListViewResults<T> {
	private final Class<T> type;
	private final ViewResults viewResults;
	private final DocumentKeyMapper<T> keyMapper;

	public ListViewResults(Class<T> type, ViewResults viewResults, DocumentKeyMapper<T> keyMapper) {
		this.type = type;
		this.viewResults = viewResults;
		this.keyMapper = keyMapper;
	}

	@SuppressWarnings("unchecked")
	public List<T> getResultList() {
		final List<T> list = new ArrayList<>();
		for (final Document d : viewResults.getResults()) {
			final T docObj = (T) JSONObject.toBean(
				d.getJSONObject().getJSONObject("value"),
				type
			);
			List<Object> keys;
			JSONArray jsonKeys = d.getJSONObject().optJSONArray("key");
			if (jsonKeys == null) {
				keys = new ArrayList<>();
				keys.add(d.getJSONObject().get("key"));
			} else {
				keys = (List<Object>) JSONArray.toCollection(jsonKeys);
			}
			keyMapper.mapKeys(docObj, d.getId(), d.getRev(), keys);
			list.add(docObj);
		}

		return list;
	}
}
