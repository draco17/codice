package com.synergyj.codice.content

import com.synergyj.codice.content.Content
import com.megatome.grails.RecaptchaService

class CommentController {
	def recaptchaService
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ commentInstanceList: Comment.list( params ), commentInstanceTotal: Comment.count() ]
    }

    def show = {
        def commentInstance = Comment.get( params.id )

        if(!commentInstance) {
            flash.message = "Comment not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ commentInstance : commentInstance ] }
    }

    def delete = {
        def commentInstance = Comment.get( params.id )
        if(commentInstance) {
            try {
                commentInstance.delete()
                flash.message = "Comment ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Comment ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Comment not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def commentInstance = Comment.get( params.id )

        if(!commentInstance) {
            flash.message = "Comment not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ commentInstance : commentInstance ]
        }
    }

	/*
    def update = {
        def commentInstance = Comment.get( params.id )
        if(commentInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(commentInstance.version > version) {
                    
                    commentInstance.errors.rejectValue("version", "comment.optimistic.locking.failure", "Another user has updated this Comment while you were editing.")
                    render(view:'edit',model:[commentInstance:commentInstance])
                }
            }
            commentInstance.properties = params
            if(!commentInstance.hasErrors() && commentInstance.save()) {
                flash.message = "Comment ${params.id} updated"
                redirect(action:show,id:commentInstance.id)
            }
            else {
                render(view:'edit',model:[commentInstance:commentInstance])
            }
        }
        else {
            flash.message = "Comment not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }
	*/

    def create = {
        def commentInstance = new Comment()
        commentInstance.properties = params
        return ['commentInstance':commentInstance]
    }

	def save = { CommentCommand cmd ->
		def recaptchaOK = true
		if (!recaptchaService.verifyAnswer(session, request.getRemoteAddr(), params)) {
			recaptchaOK = false
		}
		if(!cmd.hasErrors() && recaptchaOK){
			recaptchaService.cleanUp(session) 
			def commentInstance = new Comment()
			bindData(commentInstance, cmd.properties)
			commentInstance.content = Content.get(cmd.contentId)
			commentInstance.save()
			flash.message = "Your comment has been submited..."
			redirect(controller:'content',action:'show',id:cmd.contentId)
			}else{
				flash.message = "Errors has ocurred in the comment..."
				def contentInstance = Content.get( cmd.contentId )
				render view:"/content/show",model:[ contentInstance : contentInstance, commentInstance:cmd ]
			}
		}
}
