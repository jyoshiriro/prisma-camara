package hackathon2013

import org.springframework.dao.DataIntegrityViolationException

class DeputadoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [deputadoInstanceList: Deputado.list(params), deputadoInstanceTotal: Deputado.count()]
    }

    def create() {
        [deputadoInstance: new Deputado(params)]
    }

    def save() {
        def deputadoInstance = new Deputado(params)
        if (!deputadoInstance.save(flush: true)) {
            render(view: "create", model: [deputadoInstance: deputadoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'deputado.label', default: 'Deputado'), deputadoInstance.id])
        redirect(action: "show", id: deputadoInstance.id)
    }

    def show(Long id) {
        def deputadoInstance = Deputado.get(id)
        if (!deputadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'deputado.label', default: 'Deputado'), id])
            redirect(action: "list")
            return
        }

        [deputadoInstance: deputadoInstance]
    }

    def edit(Long id) {
        def deputadoInstance = Deputado.get(id)
        if (!deputadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'deputado.label', default: 'Deputado'), id])
            redirect(action: "list")
            return
        }

        [deputadoInstance: deputadoInstance]
    }

    def update(Long id, Long version) {
        def deputadoInstance = Deputado.get(id)
        if (!deputadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'deputado.label', default: 'Deputado'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (deputadoInstance.version > version) {
                deputadoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'deputado.label', default: 'Deputado')] as Object[],
                          "Another user has updated this Deputado while you were editing")
                render(view: "edit", model: [deputadoInstance: deputadoInstance])
                return
            }
        }

        deputadoInstance.properties = params

        if (!deputadoInstance.save(flush: true)) {
            render(view: "edit", model: [deputadoInstance: deputadoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'deputado.label', default: 'Deputado'), deputadoInstance.id])
        redirect(action: "show", id: deputadoInstance.id)
    }

    def delete(Long id) {
        def deputadoInstance = Deputado.get(id)
        if (!deputadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'deputado.label', default: 'Deputado'), id])
            redirect(action: "list")
            return
        }

        try {
            deputadoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'deputado.label', default: 'Deputado'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'deputado.label', default: 'Deputado'), id])
            redirect(action: "show", id: id)
        }
    }
}
