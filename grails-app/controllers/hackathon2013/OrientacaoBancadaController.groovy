package hackathon2013

import org.springframework.dao.DataIntegrityViolationException

class OrientacaoBancadaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [orientacaoBancadaInstanceList: OrientacaoBancada.list(params), orientacaoBancadaInstanceTotal: OrientacaoBancada.count()]
    }

    def create() {
        [orientacaoBancadaInstance: new OrientacaoBancada(params)]
    }

    def save() {
        def orientacaoBancadaInstance = new OrientacaoBancada(params)
        if (!orientacaoBancadaInstance.save(flush: true)) {
            render(view: "create", model: [orientacaoBancadaInstance: orientacaoBancadaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'orientacaoBancada.label', default: 'OrientacaoBancada'), orientacaoBancadaInstance.id])
        redirect(action: "show", id: orientacaoBancadaInstance.id)
    }

    def show(Long id) {
        def orientacaoBancadaInstance = OrientacaoBancada.get(id)
        if (!orientacaoBancadaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'orientacaoBancada.label', default: 'OrientacaoBancada'), id])
            redirect(action: "list")
            return
        }

        [orientacaoBancadaInstance: orientacaoBancadaInstance]
    }

    def edit(Long id) {
        def orientacaoBancadaInstance = OrientacaoBancada.get(id)
        if (!orientacaoBancadaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'orientacaoBancada.label', default: 'OrientacaoBancada'), id])
            redirect(action: "list")
            return
        }

        [orientacaoBancadaInstance: orientacaoBancadaInstance]
    }

    def update(Long id, Long version) {
        def orientacaoBancadaInstance = OrientacaoBancada.get(id)
        if (!orientacaoBancadaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'orientacaoBancada.label', default: 'OrientacaoBancada'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (orientacaoBancadaInstance.version > version) {
                orientacaoBancadaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'orientacaoBancada.label', default: 'OrientacaoBancada')] as Object[],
                          "Another user has updated this OrientacaoBancada while you were editing")
                render(view: "edit", model: [orientacaoBancadaInstance: orientacaoBancadaInstance])
                return
            }
        }

        orientacaoBancadaInstance.properties = params

        if (!orientacaoBancadaInstance.save(flush: true)) {
            render(view: "edit", model: [orientacaoBancadaInstance: orientacaoBancadaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'orientacaoBancada.label', default: 'OrientacaoBancada'), orientacaoBancadaInstance.id])
        redirect(action: "show", id: orientacaoBancadaInstance.id)
    }

    def delete(Long id) {
        def orientacaoBancadaInstance = OrientacaoBancada.get(id)
        if (!orientacaoBancadaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'orientacaoBancada.label', default: 'OrientacaoBancada'), id])
            redirect(action: "list")
            return
        }

        try {
            orientacaoBancadaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'orientacaoBancada.label', default: 'OrientacaoBancada'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'orientacaoBancada.label', default: 'OrientacaoBancada'), id])
            redirect(action: "show", id: id)
        }
    }
}
