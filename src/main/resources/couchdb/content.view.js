{
	"_id": "_design/content",
	"language": "javascript",
	"views": {
		"doc_by_sessionid_variant_active": {
			"map": "function(doc) { if (doc.type == 'skill_question') { emit([doc.sessionId, doc.questionVariant, doc.active, doc.subject, doc.text.substr(0, 16)], doc); }}",
			"reduce": "_count"
		},
		"by_sessionid": {
			"map": "/* Redundant but kept for now to allow simpler queries. */\nfunction(doc) { if (doc.type == 'skill_question') { emit(doc.sessionId, null); }}",
			"reduce": "_count"
		},
		"by_sessionid_variant_active": {
			"map": "function(doc) { if (doc.type == 'skill_question') { emit([doc.sessionId, doc.questionVariant, doc.active, doc.subject, doc.text.substr(0, 16)], null); }}",
			"reduce": "_count"
		}
	}
}
