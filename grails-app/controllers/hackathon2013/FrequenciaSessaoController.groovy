package hackathon2013

import org.springframework.dao.DataIntegrityViolationException

class FrequenciaSessaoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [frequenciaSessaoInstanceList: FrequenciaSessao.list(params), frequenciaSessaoInstanceTotal: FrequenciaSessao.count()]
    }

    def create() {
        [frequenciaSessaoInstance: new FrequenciaSessao(params)]
    }

    def save() {
        def frequenciaSessaoInstance = new FrequenciaSessao(params)
        if (!frequenciaSessaoInstance.save(flush: true)) {
            render(view: "create", model: [frequenciaSessaoInstance: frequenciaSessaoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'frequenciaSessao.label', default: 'FrequenciaSessao'), frequenciaSessaoInstance.id])
        redirect(action: "show", id: frequenciaSessaoInstance.id)
    }

    def show(Long id) {
        def frequenciaSessaoInstance = FrequenciaSessao.get(id)
        if (!frequenciaSessaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'frequenciaSessao.label', default: 'FrequenciaSessao'), id])
            redirect(action: "list")
            return
        }

        [frequenciaSessaoInstance: frequenciaSessaoInstance]
    }

    def edit(Long id) {
        def frequenciaSessaoInstance = FrequenciaSessao.get(id)
        if (!frequenciaSessaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'frequenciaSessao.label', default: 'FrequenciaSessao'), id])
            redirect(action: "list")
            return
        }

        [frequenciaSessaoInstance: frequenciaSessaoInstance]
    }

    def update(Long id, Long version) {
        def frequenciaSessaoInstance = FrequenciaSessao.get(id)
        if (!frequenciaSessaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'frequenciaSessao.label', default: 'FrequenciaSessao'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (frequenciaSessaoInstance.version > version) {
                frequenciaSessaoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'frequenciaSessao.label', default: 'FrequenciaSessao')] as Object[],
                          "Another user has updated this FrequenciaSessao while you were editing")
                render(view: "edit", model: [frequenciaSessaoInstance: frequenciaSessaoInstance])
                return
            }
        }

        frequenciaSessaoInstance.properties = params

        if (!frequenciaSessaoInstance.save(flush: true)) {
            render(view: "edit", model: [frequenciaSessaoInstance: frequenciaSessaoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'frequenciaSessao.label', default: 'FrequenciaSessao'), frequenciaSessaoInstance.id])
        redirect(action: "show", id: frequenciaSessaoInstance.id)
    }

    def delete(Long id) {
        def frequenciaSessaoInstance = FrequenciaSessao.get(id)
        if (!frequenciaSessaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'frequenciaSessao.label', default: 'FrequenciaSessao'), id])
            redirect(action: "list")
            return
        }

        try {
            frequenciaSessaoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'frequenciaSessao.label', default: 'FrequenciaSessao'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'frequenciaSessao.label', default: 'FrequenciaSessao'), id])
            redirect(action: "show", id: id)
        }
    }
}
