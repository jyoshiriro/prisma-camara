package hackathon2013

import org.springframework.dao.DataIntegrityViolationException

class VotacaoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [votacaoInstanceList: Votacao.list(params), votacaoInstanceTotal: Votacao.count()]
    }

    def create() {
        [votacaoInstance: new Votacao(params)]
    }

    def save() {
        def votacaoInstance = new Votacao(params)
        if (!votacaoInstance.save(flush: true)) {
            render(view: "create", model: [votacaoInstance: votacaoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'votacao.label', default: 'Votacao'), votacaoInstance.id])
        redirect(action: "show", id: votacaoInstance.id)
    }

    def show(Long id) {
        def votacaoInstance = Votacao.get(id)
        if (!votacaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'votacao.label', default: 'Votacao'), id])
            redirect(action: "list")
            return
        }

        [votacaoInstance: votacaoInstance]
    }

    def edit(Long id) {
        def votacaoInstance = Votacao.get(id)
        if (!votacaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'votacao.label', default: 'Votacao'), id])
            redirect(action: "list")
            return
        }

        [votacaoInstance: votacaoInstance]
    }

    def update(Long id, Long version) {
        def votacaoInstance = Votacao.get(id)
        if (!votacaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'votacao.label', default: 'Votacao'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (votacaoInstance.version > version) {
                votacaoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'votacao.label', default: 'Votacao')] as Object[],
                          "Another user has updated this Votacao while you were editing")
                render(view: "edit", model: [votacaoInstance: votacaoInstance])
                return
            }
        }

        votacaoInstance.properties = params

        if (!votacaoInstance.save(flush: true)) {
            render(view: "edit", model: [votacaoInstance: votacaoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'votacao.label', default: 'Votacao'), votacaoInstance.id])
        redirect(action: "show", id: votacaoInstance.id)
    }

    def delete(Long id) {
        def votacaoInstance = Votacao.get(id)
        if (!votacaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'votacao.label', default: 'Votacao'), id])
            redirect(action: "list")
            return
        }

        try {
            votacaoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'votacao.label', default: 'Votacao'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'votacao.label', default: 'Votacao'), id])
            redirect(action: "show", id: id)
        }
    }
}
