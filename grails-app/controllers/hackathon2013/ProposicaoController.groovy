package hackathon2013

import org.springframework.dao.DataIntegrityViolationException

class ProposicaoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [proposicaoInstanceList: Proposicao.list(params), proposicaoInstanceTotal: Proposicao.count()]
    }

    def create() {
        [proposicaoInstance: new Proposicao(params)]
    }

    def save() {
        def proposicaoInstance = new Proposicao(params)
        if (!proposicaoInstance.save(flush: true)) {
            render(view: "create", model: [proposicaoInstance: proposicaoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'proposicao.label', default: 'Proposicao'), proposicaoInstance.id])
        redirect(action: "show", id: proposicaoInstance.id)
    }

    def show(Long id) {
        def proposicaoInstance = Proposicao.get(id)
        if (!proposicaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proposicao.label', default: 'Proposicao'), id])
            redirect(action: "list")
            return
        }

        [proposicaoInstance: proposicaoInstance]
    }

    def edit(Long id) {
        def proposicaoInstance = Proposicao.get(id)
        if (!proposicaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proposicao.label', default: 'Proposicao'), id])
            redirect(action: "list")
            return
        }

        [proposicaoInstance: proposicaoInstance]
    }

    def update(Long id, Long version) {
        def proposicaoInstance = Proposicao.get(id)
        if (!proposicaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proposicao.label', default: 'Proposicao'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (proposicaoInstance.version > version) {
                proposicaoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'proposicao.label', default: 'Proposicao')] as Object[],
                          "Another user has updated this Proposicao while you were editing")
                render(view: "edit", model: [proposicaoInstance: proposicaoInstance])
                return
            }
        }

        proposicaoInstance.properties = params

        if (!proposicaoInstance.save(flush: true)) {
            render(view: "edit", model: [proposicaoInstance: proposicaoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'proposicao.label', default: 'Proposicao'), proposicaoInstance.id])
        redirect(action: "show", id: proposicaoInstance.id)
    }

    def delete(Long id) {
        def proposicaoInstance = Proposicao.get(id)
        if (!proposicaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proposicao.label', default: 'Proposicao'), id])
            redirect(action: "list")
            return
        }

        try {
            proposicaoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'proposicao.label', default: 'Proposicao'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'proposicao.label', default: 'Proposicao'), id])
            redirect(action: "show", id: id)
        }
    }
}
