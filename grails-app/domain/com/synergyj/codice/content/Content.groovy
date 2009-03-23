package com.synergyj.codice.content

import com.synergyj.auth.User
import com.synergyj.codice.content.*
import com.synergyj.codice.Cms
import org.grails.taggable.*

class Content implements Taggable{
	
	User user
	String title
	String textBody
	Date created = new Date()
	Date lastUpdated =  new Date()
	Boolean allowComments
	Boolean publish
	Boolean showInMainPage
	String contentType
	Integer priority
	
	static hasMany = [comments:Comment]
	static belongsTo = [cms:Cms]
	
	static constraints = {
		title nullable:false,size:5..128
		textBody nullable:false,maxSize:10000
		priority min:0,max:10
	}
}
