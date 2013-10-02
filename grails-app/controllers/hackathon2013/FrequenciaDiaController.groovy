package hackathon2013

import org.springframework.dao.DataIntegrityViolationException

class FrequenciaDiaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [frequenciaDiaInstanceList: FrequenciaDia.list(params), frequenciaDiaInstanceTotal: FrequenciaDia.count()]
    }

    def create() {
        [frequenciaDiaInstance: new FrequenciaDia(params)]
    }

    def save() {
        def frequenciaDiaInstance = new FrequenciaDia(params)
        if (!frequenciaDiaInstance.save(flush: true)) {
            render(view: "create", model: [frequenciaDiaInstance: frequenciaDiaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'frequenciaDia.label', default: 'FrequenciaDia'), frequenciaDiaInstance.id])
        redirect(action: "show", id: frequenciaDiaInstance.id)
    }

    def show(Long id) {
        def frequenciaDiaInstance = FrequenciaDia.get(id)
        if (!frequenciaDiaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'frequenciaDia.label', default: 'FrequenciaDia'), id])
            redirect(action: "list")
            return
        }

        [frequenciaDiaInstance: frequenciaDiaInstance]
    }

    def edit(Long id) {
        def frequenciaDiaInstance = FrequenciaDia.get(id)
        if (!frequenciaDiaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'frequenciaDia.label', default: 'FrequenciaDia'), id])
            redirect(action: "list")
            return
        }

        [frequenciaDiaInstance: frequenciaDiaInstance]
    }

    def update(Long id, Long version) {
        def frequenciaDiaInstance = FrequenciaDia.get(id)
        if (!frequenciaDiaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'frequenciaDia.label', default: 'FrequenciaDia'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (frequenciaDiaInstance.version > version) {
                frequenciaDiaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'frequenciaDia.label', default: 'FrequenciaDia')] as Object[],
                          "Another user has updated this FrequenciaDia while you were editing")
                render(view: "edit", model: [frequenciaDiaInstance: frequenciaDiaInstance])
                return
            }
        }

        frequenciaDiaInstance.properties = params

        if (!frequenciaDiaInstance.save(flush: true)) {
            render(view: "edit", model: [frequenciaDiaInstance: frequenciaDiaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'frequenciaDia.label', default: 'FrequenciaDia'), frequenciaDiaInstance.id])
        redirect(action: "show", id: frequenciaDiaInstance.id)
    }

    def delete(Long id) {
        def frequenciaDiaInstance = FrequenciaDia.get(id)
        if (!frequenciaDiaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'frequenciaDia.label', default: 'FrequenciaDia'), id])
            redirect(action: "list")
            return
        }

        try {
            frequenciaDiaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'frequenciaDia.label', default: 'FrequenciaDia'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'frequenciaDia.label', default: 'FrequenciaDia'), id])
            redirect(action: "show", id: id)
        }
    }
}
