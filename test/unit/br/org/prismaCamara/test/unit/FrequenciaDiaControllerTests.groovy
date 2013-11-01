/*
 * Copyright 2013 de José Yoshiriro (jyoshiriro@gmail.com) e Raimundo Norberto (raimundonorberto@gmail.com)
 * Este arquivo é parte do programa Olho na Câmara.
 * 
 * O Olho na Câmara é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro
 * dos termos da GNU General Public License como publicada pela Fundação do Software Livre
 * (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser
 * útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter
 * recebido uma cópia da GNU General Public License, sob o título 'LICENCA.txt', junto com
 * este programa, se não, acesse http://www.gnu.org/licenses/
 */
package br.org.prismaCamara.test.unit



import org.junit.*
import grails.test.mixin.*

@TestFor(FrequenciaDiaController)
@Mock(FrequenciaDia)
class FrequenciaDiaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/frequenciaDia/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.frequenciaDiaInstanceList.size() == 0
        assert model.frequenciaDiaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.frequenciaDiaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.frequenciaDiaInstance != null
        assert view == '/frequenciaDia/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/frequenciaDia/show/1'
        assert controller.flash.message != null
        assert FrequenciaDia.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/frequenciaDia/list'

        populateValidParams(params)
        def frequenciaDia = new FrequenciaDia(params)

        assert frequenciaDia.save() != null

        params.id = frequenciaDia.id

        def model = controller.show()

        assert model.frequenciaDiaInstance == frequenciaDia
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/frequenciaDia/list'

        populateValidParams(params)
        def frequenciaDia = new FrequenciaDia(params)

        assert frequenciaDia.save() != null

        params.id = frequenciaDia.id

        def model = controller.edit()

        assert model.frequenciaDiaInstance == frequenciaDia
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/frequenciaDia/list'

        response.reset()

        populateValidParams(params)
        def frequenciaDia = new FrequenciaDia(params)

        assert frequenciaDia.save() != null

        // test invalid parameters in update
        params.id = frequenciaDia.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/frequenciaDia/edit"
        assert model.frequenciaDiaInstance != null

        frequenciaDia.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/frequenciaDia/show/$frequenciaDia.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        frequenciaDia.clearErrors()

        populateValidParams(params)
        params.id = frequenciaDia.id
        params.version = -1
        controller.update()

        assert view == "/frequenciaDia/edit"
        assert model.frequenciaDiaInstance != null
        assert model.frequenciaDiaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/frequenciaDia/list'

        response.reset()

        populateValidParams(params)
        def frequenciaDia = new FrequenciaDia(params)

        assert frequenciaDia.save() != null
        assert FrequenciaDia.count() == 1

        params.id = frequenciaDia.id

        controller.delete()

        assert FrequenciaDia.count() == 0
        assert FrequenciaDia.get(frequenciaDia.id) == null
        assert response.redirectedUrl == '/frequenciaDia/list'
    }
}
