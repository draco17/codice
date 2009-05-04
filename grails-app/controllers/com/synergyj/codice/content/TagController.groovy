package com.synergyj.codice.content

class TagController {

    def index = {
    		println "Default action TagController"
    		redirect(controller: cms,action: list, params:params )
    }
    
	def list = { 
    		def contentInstanceList = Content.findAllByTag( params.id )
    		if( !contentInstanceList ) {
    			flash.message = "No hay contenidos para la tag ${params.id} !!!"
    			redirect(controller:'cms')
    		}
    		else {
    			flash.tag = params.id
    			return [ contentInstanceList: contentInstanceList, contentInstanceTotal: contentInstanceList.size() ]
    			 
    		}
    	}
    
}
