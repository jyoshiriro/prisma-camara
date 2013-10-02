package hackathon2013

import org.springframework.dao.DataIntegrityViolationException

class ComissaoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [comissaoInstanceList: Comissao.list(params), comissaoInstanceTotal: Comissao.count()]
    }

    def create() {
        [comissaoInstance: new Comissao(params)]
    }

    def save() {
        def comissaoInstance = new Comissao(params)
        if (!comissaoInstance.save(flush: true)) {
            render(view: "create", model: [comissaoInstance: comissaoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'comissao.label', default: 'Comissao'), comissaoInstance.id])
        redirect(action: "show", id: comissaoInstance.id)
    }

    def show(Long id) {
        def comissaoInstance = Comissao.get(id)
        if (!comissaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'comissao.label', default: 'Comissao'), id])
            redirect(action: "list")
            return
        }

        [comissaoInstance: comissaoInstance]
    }

    def edit(Long id) {
        def comissaoInstance = Comissao.get(id)
        if (!comissaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'comissao.label', default: 'Comissao'), id])
            redirect(action: "list")
            return
        }

        [comissaoInstance: comissaoInstance]
    }

    def update(Long id, Long version) {
        def comissaoInstance = Comissao.get(id)
        if (!comissaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'comissao.label', default: 'Comissao'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (comissaoInstance.version > version) {
                comissaoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'comissao.label', default: 'Comissao')] as Object[],
                          "Another user has updated this Comissao while you were editing")
                render(view: "edit", model: [comissaoInstance: comissaoInstance])
                return
            }
        }

        comissaoInstance.properties = params

        if (!comissaoInstance.save(flush: true)) {
            render(view: "edit", model: [comissaoInstance: comissaoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'comissao.label', default: 'Comissao'), comissaoInstance.id])
        redirect(action: "show", id: comissaoInstance.id)
    }

    def delete(Long id) {
        def comissaoInstance = Comissao.get(id)
        if (!comissaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'comissao.label', default: 'Comissao'), id])
            redirect(action: "list")
            return
        }

        try {
            comissaoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'comissao.label', default: 'Comissao'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'comissao.label', default: 'Comissao'), id])
            redirect(action: "show", id: id)
        }
    }
}
