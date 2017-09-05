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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.	 See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.	 If not, see <http://www.gnu.org/licenses/>.
 */
package de.thm.arsnova.services;

import de.thm.arsnova.entities.migration.v2.Answer;
import de.thm.arsnova.entities.migration.v2.Content;
import de.thm.arsnova.entities.User;

import java.util.List;
import java.util.Map;

/**
 * The functionality the question service should provide.
 */
public interface ContentService {
	Content save(Content content);

	Content get(String id);

	List<Content> getBySessionKey(String sessionkey);

	int countBySessionKey(String sessionkey);

	void delete(String questionId);

	void startNewPiRound(String questionId, User user);

	void startNewPiRoundDelayed(String questionId, int time);

	void cancelPiRoundChange(String questionId);

	void cancelDelayedPiRoundChange(String questionId);

	void resetPiRoundState(String questionId);

	List<String> getUnAnsweredQuestionIds(String sessionKey);

	Answer getMyAnswer(String questionId);

	void getFreetextAnswerAndMarkRead(String answerId, User user);

	List<Answer> getAnswers(String questionId, int piRound, int offset, int limit);

	List<Answer> getAnswers(String questionId, int offset, int limit);

	List<Answer> getAllAnswers(String questionId, int offset, int limit);

	int countAnswersByQuestionIdAndRound(String questionId);

	int countAnswersByQuestionIdAndRound(String questionId, int piRound);

	List<Answer> getFreetextAnswersByQuestionId(String questionId, int offset, int limit);

	List<Answer> getMyAnswersBySessionKey(String sessionKey);

	int countTotalAnswersBySessionKey(String sessionKey);

	int countTotalAnswersByQuestionId(String questionId);

	Content save(final String sessionId, final Content content);

	Content update(Content content);

	void deleteAnswers(String questionId);

	Answer saveAnswer(String questionId, Answer answer);

	Answer updateAnswer(Answer answer);

	void deleteAnswer(String questionId, String answerId);

	List<Content> getLectureQuestions(String sessionkey);

	List<Content> getFlashcards(String sessionkey);

	List<Content> getPreparationQuestions(String sessionkey);

	int countLectureQuestions(String sessionkey);

	int countFlashcards(String sessionkey);

	int countPreparationQuestions(String sessionkey);

	Map<String, Object> countAnswersAndAbstentionsInternal(String questionid);

	int countLectureQuestionAnswers(String sessionkey);

	int countLectureQuestionAnswersInternal(String sessionkey);

	int countPreparationQuestionAnswers(String sessionkey);

	int countPreparationQuestionAnswersInternal(String sessionkey);

	int countFlashcardsForUserInternal(String sessionkey);

	void deleteAllContent(String sessionkey);

	void deleteLectureQuestions(String sessionkey);

	void deletePreparationQuestions(String sessionkey);

	void deleteFlashcards(String sessionkey);

	List<String> getUnAnsweredLectureQuestionIds(String sessionkey);

	List<String> getUnAnsweredLectureQuestionIds(String sessionKey, User user);

	List<String> getUnAnsweredPreparationQuestionIds(String sessionkey);

	List<String> getUnAnsweredPreparationQuestionIds(String sessionKey, User user);

	void publishAll(String sessionkey, boolean publish);

	void publishQuestions(String sessionkey, boolean publish, List<Content> contents);

	void deleteAllQuestionsAnswers(String sessionkey);

	void deleteAllPreparationAnswers(String sessionkey);

	void deleteAllLectureAnswers(String sessionkey);

	int countTotalAbstentionsByQuestionId(String questionId);

	String getImage(String questionId, String answerId);

	void setVotingAdmission(String questionId, boolean disableVoting);

	void setVotingAdmissions(String sessionkey, boolean disableVoting, List<Content> contents);

	void setVotingAdmissionForAllQuestions(String sessionkey, boolean disableVoting);

	String getQuestionImage(String questionId);

	String getQuestionFcImage(String questionId);

	List<Content> replaceImageData(List<Content> contents);
}
