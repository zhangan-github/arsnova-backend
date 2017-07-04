{
	"_id": "_design/answer",
	"language": "javascript",
	"views": {
		"doc_by_questionid_user_piround": {
			"map": (function (doc) {
				if (doc.type == "skill_question_answer") {
					emit([doc.questionId, doc.user, doc.piRound], doc);
				}
			}).toString().replace(/[\t]{3}(\t*)/g, "$1")
		},
		"doc_by_questionid_timestamp": {
			"map": (function (doc) {
				if (doc.type == "skill_question_answer") {
					emit([doc.questionId, doc.timestamp], doc);
				}
			}).toString()
		},
		"doc_by_user_sessionid": {
			"map": (function (doc) {
				if (doc.type == "skill_question_answer") { emit([doc.user, doc.sessionId], doc); }
			}).toString()
		},
		"by_questionid": {
			"map": (function (doc) {
				if (doc.type == "skill_question_answer") {
					emit(doc.questionId, null);
				}
			}).toString()
		},
		"by_questionid_piround_text_subject": {
			"map": (function (doc) {
				if (doc.type == "skill_question_answer") {
					emit([doc.questionId, doc.piRound, doc.abstention, doc.answerText, doc.answerSubject, doc.successfulFreeTextAnswer], null);
				}
			}).toString(),
			"reduce": "_count"
		},
		"by_sessionid": {
			"map": (function (doc) {
				/* Redundant view but kept for now to allow simpler queries. */
				if (doc.type == "skill_question_answer") {
					emit(doc.sessionId, null);
				}
			}).toString(),
			"reduce": "_count"
		},
		"by_sessionid_variant": {
			"map": (function (doc) {
				if (doc.type == "skill_question_answer") {
					emit([doc.sessionId, doc.questionVariant], null);
				}
			}).toString(),
			"reduce": "_count"
		},
		"questionid_by_user_sessionid_variant": {
			"map": (function (doc) {
				if (doc.type == "skill_question_answer") {
					emit([doc.user, doc.sessionId, doc.questionVariant], doc.questionId);
				}
			}).toString()
		},
		"questionid_piround_by_user_sessionid_variant": {
			"map": (function (doc) {
				if (doc.type == "skill_question_answer") {
					emit([doc.user, doc.sessionId, doc.questionVariant], [doc.questionId, doc.piRound]);
				}
			}).toString()
		}
	}
}
