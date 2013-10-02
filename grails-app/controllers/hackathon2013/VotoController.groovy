package hackathon2013

import org.springframework.dao.DataIntegrityViolationException

class VotoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [votoInstanceList: Voto.list(params), votoInstanceTotal: Voto.count()]
    }

    def create() {
        [votoInstance: new Voto(params)]
    }

    def save() {
        def votoInstance = new Voto(params)
        if (!votoInstance.save(flush: true)) {
            render(view: "create", model: [votoInstance: votoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'voto.label', default: 'Voto'), votoInstance.id])
        redirect(action: "show", id: votoInstance.id)
    }

    def show(Long id) {
        def votoInstance = Voto.get(id)
        if (!votoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'voto.label', default: 'Voto'), id])
            redirect(action: "list")
            return
        }

        [votoInstance: votoInstance]
    }

    def edit(Long id) {
        def votoInstance = Voto.get(id)
        if (!votoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'voto.label', default: 'Voto'), id])
            redirect(action: "list")
            return
        }

        [votoInstance: votoInstance]
    }

    def update(Long id, Long version) {
        def votoInstance = Voto.get(id)
        if (!votoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'voto.label', default: 'Voto'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (votoInstance.version > version) {
                votoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'voto.label', default: 'Voto')] as Object[],
                          "Another user has updated this Voto while you were editing")
                render(view: "edit", model: [votoInstance: votoInstance])
                return
            }
        }

        votoInstance.properties = params

        if (!votoInstance.save(flush: true)) {
            render(view: "edit", model: [votoInstance: votoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'voto.label', default: 'Voto'), votoInstance.id])
        redirect(action: "show", id: votoInstance.id)
    }

    def delete(Long id) {
        def votoInstance = Voto.get(id)
        if (!votoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'voto.label', default: 'Voto'), id])
            redirect(action: "list")
            return
        }

        try {
            votoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'voto.label', default: 'Voto'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'voto.label', default: 'Voto'), id])
            redirect(action: "show", id: id)
        }
    }
}
