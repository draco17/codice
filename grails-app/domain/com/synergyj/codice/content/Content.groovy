package com.synergyj.codice.content

import com.synergyj.auth.User
import com.synergyj.codice.content.*
import com.synergyj.codice.Cms
import org.grails.taggable.*

class Content implements Taggable{
	
	User user
	String title
	String body
	Date created = new Date()
	Date lastUpdated =  new Date()
	Boolean allowComments = true
	Boolean publish = true
	Boolean showInMainPage = true
	String contentType
	
	static hasMany = [comments:Comment]
	static belongsTo = [cms:Cms]
	
	static constraints = {
		title nullable:false,size:5..128
		body nullable:false,maxSize:10000
	}
}
