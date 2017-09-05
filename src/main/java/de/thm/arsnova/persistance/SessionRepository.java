/*
 * This file is part of ARSnova Backend.
 * Copyright (C) 2012-2017 The ARSnova Team
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
package de.thm.arsnova.persistance;

import de.thm.arsnova.connector.model.Course;
import de.thm.arsnova.entities.migration.v2.LoggedIn;
import de.thm.arsnova.entities.UserAuthentication;
import de.thm.arsnova.entities.migration.v2.Session;
import de.thm.arsnova.entities.migration.v2.SessionInfo;
import de.thm.arsnova.entities.transport.ImportExportSession;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SessionRepository extends CrudRepository<Session, String> {
	Session findByKeyword(String keyword);
	List<Session> findInactiveGuestSessionsMetadata(long lastActivityBefore);
	List<Session> findByUser(UserAuthentication user, int start, int limit);
	List<Session> findByUsername(String username, int start, int limit);
	List<Session> findAllForPublicPool();
	List<Session> findForPublicPoolByUser(UserAuthentication user);
	List<Session> findVisitedByUsername(String username, int start, int limit);
	List<SessionInfo> getMySessionsInfo(UserAuthentication user, int start, int limit);
	List<SessionInfo> findInfosForPublicPool();
	List<SessionInfo> findInfosForPublicPoolByUser(UserAuthentication user);
	List<SessionInfo> findInfoForVisitedByUser(UserAuthentication currentUser, int start, int limit);
	List<Session> findSessionsByCourses(List<Course> courses);
	SessionInfo importSession(UserAuthentication user, ImportExportSession importSession);
	ImportExportSession exportSession(String sessionkey, Boolean withAnswer, Boolean withFeedbackQuestions);
	LoggedIn registerAsOnlineUser(UserAuthentication user, Session session);
}
