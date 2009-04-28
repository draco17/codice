class UrlMappings {
	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		/***
		Home and Register URL's
		***/
		"/"(controller:'cms',action:'index')
		"/home"(controller:'cms',action:'index')
		"/register"(controller:'register',action:'index')
		"/logout"(controller:'logout',action:'index')
		/***
		Content mapping
		***/
		"/createContent/$contentType?"(controller:'content',action:'create')
		"/showContent/$id"(controller:'content',action:'show')
		"/editContent/$id"(controller:'content',action:'edit')
		"/deleteContent/$id"(controller:'content',action:'delete')
		"/saveContent"(controller:'content',action:'save')
		"/administerContent"(controller:'content',action:'administer')
		/***
		Commment mapping
		***/
		"/commentSave"(controller:'comment',action:'save')
		/***
		Error mapping
		***/
		"500"(view:'/error')
	}
}
